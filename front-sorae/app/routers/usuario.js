require('dotenv').config();
const request = require('request');
const base64Img = require('base64-img');
const cookieParser = require('cookie-parser');
const session = require('express-session');
const rota = require('path').basename(__filename, '.js'); // usuario
const fs = require('fs');
var multer = require('multer');
var upload = multer();
var moment = require('moment');

let nivel;
let lista = [];
let username;
let imagem;
let finallista = {};
let json = {};
let teste;
//const Array = require('array');
//export const list2 = "teste";


module.exports = async function (app) {

    app.use(cookieParser());
    app.use(session({ 
        resave: true,
        saveUninitialized: true,
        secret: "2C44-4D44-WppQ38S" }));

    app.use(require('connect-flash')());
    app.use(function (req, res, next) {
        res.locals.messages = require('express-messages')(req, res);
        next();
    });
    
    app.get(process.env.SERVER_PREFIX + '/alunos/:idAluno/disciplinas', function(req, res) {
        if (!req.session.token) {
            res.redirect( process.env.SERVER_PREFIX + '/app/login');
        } else if (req.session.usuario.nivel != 'ADMIN') {
            req.flash('danger', 'Acesso restrito! Somente usuários de nível ADMINISTRADOR podem acessar esta página');
            res.redirect( process.env.SERVER_PREFIX + '/');
        } else {

            teste = request({
                url: process.env.API_HOST + req.params.idAluno ,
                method: "GET",
                json: true,
                headers: {
                    "content-type": "application/json",
                    "Authorization": req.session.token
                },
            }, function (error, response, body) {
               
                if (validaRequisicao(response.statusCode, req, res)) {
                    console.log(body)
                    lista = [];
                    for (var i = 0; i < Object.keys(body).length; i++) {
                        const usuario = {
                            // nome: body.data[i].id,
                       
                        };
                        lista.push(usuario);
                    }
                    res.format({
                        html: function () {
                            res.render(rota + '/List', { itens: lista, page: rota, informacoes: req.session.usuario });

                        }
                    });
                    return lista;
                }


            });

        }
    })

    // Rota para exibição da View Listar
    app.get( process.env.SERVER_PREFIX + '/app/' + rota + '/list', function (req, res) {

        if (!req.session.token) {
            res.redirect( process.env.SERVER_PREFIX + '/app/login');
        } else if (req.session.usuario.nivel != 'ADMIN') {
            req.flash('danger', 'Acesso restrito! Somente usuários de nível ADMINISTRADOR podem acessar esta página');
            res.redirect( process.env.SERVER_PREFIX + '/');
        } else {
            teste = request({
                url: process.env.API_HOST + rota,
                method: "GET",
                json: true,
                headers: {
                    "content-type": "application/json",
                    "Authorization": req.session.token
                },
            }, function (error, response, body) {
                console.log(process.env.API_HOST + rota);
                if (validaRequisicao(response.statusCode, req, res)) {
                    lista = [];
                    for (var i = 0; i < Object.keys(body.data).length; i++) {
                        const usuario = {
                            id: body.data[i].id,
                            nome: body.data[i].nome,
                            username: body.data[i].username,
                            niveis: body.data[i].niveis,
                            ativo: true || body.data[i].ativo,
                            telefone: body.data[i].telefone,
                            matricula: body.data[i].matricula 
                          
           
                        };
                        lista.push(usuario);
                    }
                    res.format({
                        html: function () {
                            res.render(rota + '/List', { itens: lista, page: rota, informacoes: req.session.usuario });

                        }
                    });
                    return lista;
                }


            });

        }
    });

    // Rota para exibição da View Criar
    app.get( process.env.SERVER_PREFIX + '/app/' + rota + '/create', function (req, res) {
        if (!req.session.token) {
            res.redirect( process.env.SERVER_PREFIX + '/app/login');
        } else if (req.session.usuario.nivel != 'ADMIN') {
            req.flash("danger", "Acesso restrito! Somente usuários de nível ADMINISTRADOR podem acessar esta página");
            res.redirect( process.env.SERVER_PREFIX + '/');
        } else {
            res.format({
                html: function () {
                    res.render(rota + '/Create', { page: rota, informacoes: req.session.usuario });
                }
            });
        }
    });

    // Rota para receber parametros via post criar item
    app.post( process.env.SERVER_PREFIX + '/app/' + rota + '/create/submit', upload.single('photo'), function (req, res) {
        if (req.file && req.file.size > 220220) {
            req.flash("danger", "Item não salvo. Sua imagem deve ter até 200kb.");
            res.redirect( process.env.SERVER_PREFIX + '/app/' + rota + '/list');
        } else {
            const file = req.file;
            let foto;
            if (file) {
                const buf = Buffer.from(req.file.buffer);
                foto = buf.toString('base64');
            } else {
                foto = process.env.PROFILE_IMG
            }
            var niveis = []
            niveis.push(req.body.nivel)
            request({
                url: process.env.API_HOST + rota,
                method: "POST",
                json: true,
                headers: {
                    "content-type": "application/json",
                    "Authorization": req.session.token
                },
                json: {
                    "nome": req.body.nome,
                    "username": req.body.username,
                    "password": req.body.password,
                    "niveis": niveis,
                    "ativo": req.body.ativo,
                    "habilitado": true,
                    "expirado": false,
                    "bloqueado": false,
                    "telefone": req.body.fone,
                    "email": req.body.email,
                    "nomeResponsavel":req.body.nomeresponsavel,
                    "serie": req.body.serie,
                    "areaDeAtuacao": req.body.area
                   
                   
                },
            }, function (error, response, body) {

                if (response.statusCode != 200) {
                    req.flash("danger", "Item não salvo. " + body.errors);
                } else {
                    postLog(req.session.token, "POST", rota, req.session.userid);
                    req.flash("success", "Item salvo com sucesso.");
                }

                res.redirect( process.env.SERVER_PREFIX + '/app/' + rota + '/list');
                return true;
            });
        }
    });

    // Rota para exibição da View Editar
    app.get( process.env.SERVER_PREFIX + '/app/' + rota + '/edit/:id', function (req, res) {
        if (!req.session.token) {
            res.redirect( process.env.SERVER_PREFIX + '/app/login');
        } else if (req.session.usuario.nivel != 'ADMIN') {
            req.flash("danger", "Acesso restrito! Somente usuários de nível ADMINISTRADOR podem acessar esta página");
            res.redirect( process.env.SERVER_PREFIX + '/');
        } else {
            request({
                url: process.env.API_HOST + rota + "/" + req.params.id,
                method: "GET",
                json: true,
                headers: {
                    "content-type": "application/json",
                    "Authorization": req.session.token
                },
            }, function (error, response, body) {
                if (validaRequisicao(response.statusCode, req, res)) {
                    res.format({
                        html: function () {
                            res.render(rota + '/Edit', {
                                id: body.data.id,
                                nome: body.data.nome,
                                username: body.data.username,
                                password: body.data.password,
                                nivel: body.data.niveis[0],
                                page: rota,
                                ativo: body.data.ativo,
                                habilitado: body.data.habilitado,
                                expirado: body.data.expirado,
                                bloqueado: body.data.bloqueado,
                                fone: body.data.telefone,
                                informacoes: req.session.usuario,
                                email: body.data.email,
                                nomeresponsavel: body.data.nomeResponsavel,
                                matricula: body.data.matricula,
                                areaDeAtuacao: body.data.areaDeAtuacao,
                                serie: body.data.serie
                               
                                
                            });
                        }
                    });
                    nivel = body.data.niveis;
                    username = body.data.username;
                    imagem = body.data.imgCapa;
                }
            });
        }
    });
    app.get( process.env.SERVER_PREFIX + '/app/' + rota + '/perfil', function (req, res) {
        if (!req.session.token) {
            res.redirect( process.env.SERVER_PREFIX + '/app/login');
        } else {
            request({
                url: process.env.API_HOST + rota + "/" + req.session.usuario.id,
                method: "GET",
                json: true,
                headers: {
                    "content-type": "application/json",
                    "Authorization": req.session.token
                },
            }, function (error, response, body) {
                if (validaRequisicao(response.statusCode, req, res)) {
                    res.format({
                        html: function () {
                            res.render(rota + '/Perfil', {
                                id: body.data.id,
                                nome: body.data.nome,
                                username: body.data.username,
                                password: body.data.password,
                                nivel: body.data.niveis[0],
                                foto: body.data.imgCapa,
                                page: rota,
                                ativo: body.data.ativo,
                                habilitado: body.data.habilitado,
                                expirado: body.data.expirado,
                                bloqueado: body.data.bloqueado,
                                fone: body.data.telefone,
                                informacoes: req.session.usuario,
                                email: body.data.email,
                                empresa: body.data.empresa,
                                unidade: body.data.unidade
                            });
                        }
                    });
                    nivel = body.data.niveis;
                    username = body.data.username;
                    imagem = body.data.imgCapa;
                }
            });
        }
    });

    // Rota para receber parametros via post editar item
    app.post( process.env.SERVER_PREFIX + '/app/' + rota + '/edit/submit', upload.single('photo'), function (req, res) {
        if (req.file && req.file.size > 220220) {
            req.flash("danger", "Item não salvo. Sua imagem deve ter até 200kb.");
            res.redirect( process.env.SERVER_PREFIX + '/app/' + rota + '/list');
        } else {
            const file = req.file;
            let foto;
            if (file) {
                const buf = Buffer.from(req.file.buffer);
                foto = buf.toString('base64');
            } else {
                foto = null;
            }
            var niveis = []
            niveis.push(req.body.nivel)
            request({
                url: process.env.API_HOST + rota,
                method: "PUT",
                json: true,
                headers: {
                    "content-type": "application/json",
                    "Authorization": req.session.token
                },
                json: {
                    "id": req.body.id,
                    "nome": req.body.nome,
                    "username": username,
                    "niveis": niveis,
                    "imgCapa": foto,
                    "telefone": req.body.fone,
                    "email": req.body.email,
                    "ativo": req.body.ativo,
                    "password": req.body.password != '' ? req.body.password : null,
                    "habilitado": true,
                    "expirado": false,
                    "bloqueado": false,
                    "empresa": req.body.empresa,
                    "unidade": req.body.unidade,
                    "nomeresponsavel": req.body.nomeResponsavel,
                    "matricula": req.body.matricula,
                    "areaDeAtuacao": req.body.areaDeAtuacao,
                    "serie": req.body.serie
                },
            }, function (error, response, body) {

                if (response.statusCode != 200) {
                    req.flash("danger", "Item não salvo. " + body.errors);
                } else {
                    postLog(req.session.token, "PUT", rota, req.session.userid);
                    req.flash("success", "Item salvo com sucesso.");
                }

                res.redirect( process.env.SERVER_PREFIX + '/app/' + rota + '/list');
                return true;
            });
        }
    });

    // Rota para receber parametros via post editar item
    app.post( process.env.SERVER_PREFIX + '/app/' + rota + '/perfil/edit/submit', upload.single('photo'), function (req, res) {
        if (req.file && req.file.size > 220220) {
            req.flash("danger", "Item não salvo. Sua imagem deve ter até 200kb.");
            res.redirect( process.env.SERVER_PREFIX + '/app/' + rota + '/list');
        } else {
            const file = req.file;
            let foto;
            if (file) {
                const buf = Buffer.from(req.file.buffer);
                foto = buf.toString('base64');
            } else {
                foto = null;
            }
            var niveis = []
            niveis.push(req.session.usuario.nivel)
            request({
                url: process.env.API_HOST + rota,
                method: "PUT",
                json: true,
                headers: {
                    "content-type": "application/json",
                    "Authorization": req.session.token
                },
                json: {
                    "id": req.session.usuario.id,
                    "nome": req.body.nome,
                    "username": req.session.usuario.username,
                    "niveis": niveis,
                    "imgCapa": foto,
                    "telefone": req.body.fone,
                    "email": req.body.email,
                    "ativo": true,
                    "password": null,
                    "habilitado": true,
                    "expirado": false,
                    "bloqueado": false,
                    "empresa": req.body.empresa,
                    "unidade": req.body.unidade
                },
            }, function (error, response, body) {

                if (response.statusCode != 200) {
                    req.flash("danger", "Item não salvo. " + body.errors);
                } else {
                    postLog(req.session.token, "PUT", rota, req.session.userid);
                    req.flash("success", "Dados alterados com sucesso");
                }

                res.redirect( process.env.SERVER_PREFIX + '/app/' + rota + '/Perfil');
                return true;
            });
        }
    });

    app.post( process.env.SERVER_PREFIX + '/app/' + rota + '/perfil/alterar-senha/submit', upload.single('photo'), function (req, res) {
        if (!req.session.token) {
            res.redirect( process.env.SERVER_PREFIX + '/app/login');
        } else {
            request({
                url: process.env.API_HOST + rota + '/alterar-senha',
                method: "PUT",
                json: true,
                headers: {
                    "content-type": "application/json",
                    "Authorization": req.session.token,
                    'teste': 'TESTE'
                },
                form: {
                    senhaAtual: req.body.senhaAtual,
                    novaSenha: req.body.novaSenha,
                    usuarioId: req.session.usuario.id
                },
            }, function (error, response, body) {
                if (response.statusCode == 401) {
                    req.flash("danger", "Sessão Expirada. Faça o login novamente!");
                    req.session.token = null
                    res.redirect( process.env.SERVER_PREFIX + '/app/login');
                }
                if (response.statusCode != 200) {
                    req.flash("danger", "Item não atualizado. " + body.errors);
                } else {
                    req.flash("success", "Senha atualizada com sucesso.");
                }

                res.redirect( process.env.SERVER_PREFIX + '/app/' + rota + '/Perfil');
                return true;
            });
        }
    })


    // Rota para exclusão de um item
    app.post( process.env.SERVER_PREFIX + '/app/' + rota + '/delete/', function (req, res) {
        if (!req.session.token) {
            res.redirect( process.env.SERVER_PREFIX + '/app/login');
        } else if (req.session.usuario.nivel != 'ADMIN') {
            req.flash("danger", "Acesso restrito! Somente usuários de nível ADMINISTRADOR podem acessar esta página");
            res.redirect( process.env.SERVER_PREFIX + '/');
        } else {
            request({
                url: process.env.API_HOST + rota + "/" + req.body.id,
                method: "DELETE",
                json: true,
                headers: {
                    "content-type": "application/json",
                    "Authorization": req.session.token
                },
            }, function (error, response, body) {
                console.log(response)

                if (response.statusCode != 200) {
                    console.log('status' + reponse.statusCode)
                    req.flash("danger", "Item não excluído. " + body.errors);
                } else {
                    // postLog(req.session.token, "DELETE", rota, req.session.userid);
                    req.flash("success", "Item excluído com sucesso.");
                }

                res.redirect( process.env.SERVER_PREFIX + '/app/' + rota + '/list');
                return true;
            });

        }
    });

}


function validaRequisicao(statusCode, req, res) {
    if (statusCode != 401) {
        return true
    } else {
        req.flash("danger", "Sessão Expirada! Faça o login novamente");
        req.session.token = null;
        res.redirect( process.env.SERVER_PREFIX + '/app/login');
    }
}

function postLog(token, acao, item, userid) {

    var datetime = moment().format();

    request({
        url: process.env.API_HOST + "log",
        method: "POST",
        json: true,
        headers: {
            "content-type": "application/json",
            "Authorization": token
        },
        json: {
            "token": token,
            "dateTime": datetime,
            "acao": acao,
            "item": item,
            "usuario": { "id": userid }
        },
    }, function (error, response, body) {

        if (response.statusCode != 200) {
            console.log(body.errors);
        }
        return true;
    });
}