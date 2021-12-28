let parentesco = document.getElementById("grauParentesco");
let tutelado = document.querySelectorAll(".tutelado");
let filho = document.querySelectorAll(".filho");
let dependente = document.querySelectorAll(".dependente");
let conjuge = document.querySelectorAll(".conjuge");
let companheiro = document.querySelectorAll(".companheiro");
let filhoInvalido = document.querySelectorAll(".filhoInvalido");

parentesco.addEventListener("change", function () {
    if (parentesco.value == "filho") {
        [].forEach.call(filhoInvalido, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(filho, function (el) {
            el.classList.remove("d-none");
        });
        [].forEach.call(companheiro, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(conjuge, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(dependente, function (el) {
            el.classList.remove("d-none");
        });
        [].forEach.call(tutelado, function (el) {
            el.classList.add("d-none");
        });
    }
    if (parentesco.value == "tutelado") {
        [].forEach.call(filhoInvalido, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(companheiro, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(conjuge, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(dependente, function (el) {
            el.classList.remove("d-none");
        });
        [].forEach.call(tutelado, function (el) {
            el.classList.remove("d-none");
        });
        [].forEach.call(filho, function (el) {
            el.classList.add("d-none");
        });

        document.querySelector("#divTermoTutelaEntregue").classList.remove("d-none");
    }
    if (parentesco.value == "enteado") {
        [].forEach.call(filhoInvalido, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(companheiro, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(conjuge, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(dependente, function (el) {
            el.classList.remove("d-none");
        });
        [].forEach.call(tutelado, function (el) {
            el.classList.remove("d-none");
        });
        [].forEach.call(filho, function (el) {
            el.classList.add("d-none");
        });

        document.querySelector("#divTermoTutelaEntregue").classList.add("d-none");
    }
    if (parentesco.value == "conjuge") {
        [].forEach.call(filhoInvalido, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(companheiro, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(dependente, function (el) {
            el.classList.remove("d-none");
        });
        [].forEach.call(tutelado, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(filho, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(conjuge, function (el) {
            el.classList.remove("d-none");
        });
        document.querySelector("#divTermoTutelaEntregue").classList.add("d-none");
    }
    if (parentesco.value == "companheiro") {
        [].forEach.call(filhoInvalido, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(dependente, function (el) {
            el.classList.remove("d-none");
        });
        [].forEach.call(tutelado, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(filho, function (el) {
            el.classList.add("d-none");
        });

        [].forEach.call(conjuge, function (el) {
            el.classList.remove("d-none");
        });
        [].forEach.call(companheiro, function (el) {
            el.classList.remove("d-none");
        });
        document.querySelector("#divTermoTutelaEntregue").classList.add("d-none");
    }
    if (parentesco.value == "filhoInvalido") {
        [].forEach.call(companheiro, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(conjuge, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(tutelado, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(filho, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(dependente, function (el) {
            el.classList.remove("d-none");
        });
        [].forEach.call(filhoInvalido, function (el) {
            el.classList.remove("d-none");
        });
        document.querySelector("#divCuratelaSeIncapaz").classList.remove("d-none");


    }
    if (parentesco.value == "paisInvalidos") {
        [].forEach.call(companheiro, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(conjuge, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(tutelado, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(filho, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(filhoInvalido, function (el) {
            el.classList.remove("d-none");
        });
        [].forEach.call(dependente, function (el) {
            el.classList.remove("d-none");
        });
        document.querySelector("#divCertidaoNascDependenteEntregue").classList.add("d-none");
        document.querySelector("#divCuratelaSeIncapaz").classList.add("d-none");
        document.querySelector("#paiMaeDependente").classList.remove("d-none");

    }
    if (parentesco.value == "decisaoJudicial") {
        [].forEach.call(companheiro, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(conjuge, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(tutelado, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(filho, function (el) {
            el.classList.add("d-none");
        });
        [].forEach.call(filhoInvalido, function (el) {
            el.classList.remove("d-none");
        });
        [].forEach.call(dependente, function (el) {
            el.classList.remove("d-none");
        });
        document.querySelector("#divCertidaoNascDependenteEntregue").classList.add("d-none");
        document.querySelector("#divCuratelaSeIncapaz").classList.add("d-none");
        document.querySelector("#paiMaeDependente").classList.remove("d-none");

    }
});
$(document).ready(function () {
    $('#cpf').mask('999.999.999-99');
    $('#dataDeNascimento').mask('99/99/9999');
    $('#telefoneCelular').mask('(99) 9 9999-9999');
});