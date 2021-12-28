require('dotenv').config();
const request = require('request');
const cookieParser = require('cookie-parser');
const session = require('express-session');

module.exports = async function(app) {
    app.use(cookieParser());
    app.use(session({ secret: "2C44-4D44-WppQ38S" }));

    app.use(require('connect-flash')());
    app.use(function (req, res, next) {
        res.locals.messages = require('express-messages')(req, res);
        next();
    });

    app.get( process.env.SERVER_PREFIX + '/app/login/', function (req, res) {

        if (req.session.token == null) {
            res.format({
                html: function () {
                    res.render('login');
                }
            });
        } else {
            res.format({
                html: function () {
                    res.redirect( process.env.SERVER_PREFIX + '/');
                }
            });
        }
    });


    app.post( process.env.SERVER_PREFIX + '/app/authentication/', function(req, res) {

        request({
            url: process.env.API_HOST_LOGIN,
            method: "POST",
            json: true,
            headers: {
                "content-type": "application/json"
            },
            json: {
                "password": req.body.password,
                "username": req.body.email,
            },
        }, function(error, response, body) {
            if (response.statusCode != 201) {
                req.flash("danger", body.accessToken);
                res.redirect( process.env.SERVER_PREFIX + '/');
                return false;
            } else {
                req.session.token = body.accessToken;
                req.session.usuario = body.usuario
                req.session.usuario.nivel = body.usuario.niveis[0],
                req.session.userid = body.usuario.id;
                res.redirect( process.env.SERVER_PREFIX + '/');
                return true;
            }
             
        });

    });

    app.get( process.env.SERVER_PREFIX + '/app/sair', function(req, res) {
        req.session.destroy();
        res.redirect( process.env.SERVER_PREFIX + '/app/login');
    });

}
