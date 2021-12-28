var prefix = "/SORAE/credenciada";

$("#unidadeFederativa").change(function () {
  if ($("#unidadeFederativa").val() == "") {
    $("#municipio option").remove();
    $("#municipio").prepend('<option value="">Selecione um Município</option>');
    $("#municipio").prop("disabled", true);
  } else {
    CarregaMunicipios($("#unidadeFederativa").val(), null);
  }
});
$("#unidadeFederativaLotacao").change(function () {
  if ($("#unidadeFederativaLotacao").val() == "") {
    $("#municipioLotacao option").remove();
    $("#municipioLotacao").prepend(
      '<option value="">Selecione um Município</option>'
    );
    $("#municipioLotacao").prop("disabled", true);
  } else {
    CarregaMunicipiosLotacao($("#unidadeFederativaLotacao").val(), null);
  }
});

function CarregaMunicipios(idUnidadeFederativa, idMunicipio) {
  $.ajaxSetup({
    headers: { "X-CSRF-TOKEN": $('meta[name="csrf-token"]').attr("content") },
  });

  $.ajax({
    url: "http://localhost:8080/api/v1/municipio/idUf/" + idUnidadeFederativa,
    method: "get",
    data: {},
    headers: {
      "content-type": "application/json",
      Authorization: $("#token").val(),
    },
    beforeSend: function () {
      $("#municipio option").remove();
      ModalAjaxCarregando(true, $(".container-fluid"), "Carregando...");
    },
    complete: function () {
      ModalAjaxCarregando(false, $(".container-fluid"), null);
    },
    success: function (resultado) {
      if (!resultado.data.length) {
        $("#municipio").append('<option value="">Nenhum Município</option>');
        $("#municipio").prop("disabled", false);
      } else {
        resultado.data.forEach((item) => {
          $("#municipio").append(
            '<option value="' + item.id + '">' + item.nome + "</option>"
          );
        });
        $("#municipio").prop("disabled", false);

        if (idMunicipio != null) {
          $("#municipio").val(idMunicipio);
        }
      }
    },
    error: function (error) {
      // MensagemBox('erro','Erro',(error.responseJSON.exception != null && error.responseJSON.exception != '' ?  error.responseJSON.exception : error.responseJSON));
    },
  });
}
function CarregaMunicipiosLotacao(idUnidadeFederativa, idMunicipio) {
  $.ajaxSetup({
    headers: { "X-CSRF-TOKEN": $('meta[name="csrf-token"]').attr("content") },
  });

  $.ajax({
    url: "http://localhost:8080/api/v1/municipio/idUf/" + idUnidadeFederativa,
    method: "get",
    data: {},
    headers: {
      "content-type": "application/json",
      Authorization: $("#token").val(),
    },
    beforeSend: function () {
      $("#municipioLotacao option").remove();
      ModalAjaxCarregando(true, $(".container-fluid"), "Carregando...");
    },
    complete: function () {
      ModalAjaxCarregando(false, $(".container-fluid"), null);
    },
    success: function (resultado) {
      if (!resultado.data.length) {
        $("#municipioLotacao").append(
          '<option value="">Nenhum Município</option>'
        );
        $("#municipioLotacao").prop("disabled", false);
      } else {
        resultado.data.forEach((item) => {
          $("#municipioLotacao").append(
            '<option value="' + item.id + '">' + item.nome + "</option>"
          );
        });
        $("#municipioLotacao").prop("disabled", false);

        if (idMunicipio != null) {
          $("#municipioLotacao").val(idMunicipio);
        }
      }
    },
    error: function (error) {
      // MensagemBox('erro','Erro',(error.responseJSON.exception != null && error.responseJSON.exception != '' ?  error.responseJSON.exception : error.responseJSON));
    },
  });
}

function ModalAjaxCarregando(ativo, componente, texto) {
  if (ativo) {
    $(
      '<div id="' +
        componente.attr("id") +
        'ajaxModal" class="modal " style="position:absolute !important;" role="dialog">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-body">' +
        '<div class="row">' +
        '<div class="col-md-2">' +
        '<div class="lds-dual-ring"></div>' +
        "</div>" +
        '<div class="col-md-9">' +
        "<h2>" +
        texto +
        "</h2>" +
        "</div>" +
        "</div>" +
        "</div>" +
        "</div>" +
        "</div>" +
        " </div>"
    ).appendTo("#" + componente.attr("id"));

    $("#" + componente.attr("id") + "ajaxModal").modal("show");
  } else {
    $("#" + componente.attr("id") + "ajaxModal").modal("hide");
    $("#" + componente.attr("id") + "ajaxModal").remove();
  }
}

let statusCheckbox = document.getElementById("checkBoxMunicipio");
let municipioDiv = document.getElementById("municipioLotacaoDiv");
let ufDiv = document.getElementById("ufLotacaoDiv");
let cepDiv = document.getElementById("cepLotacaoDiv");
let dataPosse = document.getElementById("dataPosseDiv");
let cepInput = document.getElementById("cep");
let ufOption = document.getElementById("unidadeFederativa");
let municipioOption = document.getElementById("municipio");

statusCheckbox.addEventListener("change", function () {
  if (this.checked) {
    // municipioDiv.className += " d-none";
    // ufDiv.className += " d-none";
    // cepDiv.className += " d-none";
    passarValores();
  } else {
    municipioDiv.className = "col-sm-4";
    ufDiv.className = "col-sm-4";
    cepDiv.className = "col-sm-4";

    cepInput.removeEventListener("change", cepInputParaCepLotacao);
    ufOption.removeEventListener(
      "change",
      unidadeFederativaParaUnidadeFederativaLotacao
    );
    municipioOption.removeEventListener(
      "change",
      municipioParaMunicipioLotacao
    );
  }
});
let inputs = document.querySelectorAll("input[type=radio]"),
  x = inputs.length;
while (x--)
  inputs[x].addEventListener(
    "change",
    function () {
      if (this.value == "semCarencia") {
        dataPosseDiv.className += " d-none";
      } else {
        dataPosseDiv.className = "col-sm-4";
      }
    },
    0
  );

$(document).ready(function () {
  $("#cpf").mask("999.999.999-99");
  $("#telefoneCelular").mask("(99) 9 9999-9999");
  $("#telefoneResidencial").mask("(99) 9999-9999");
});

function cepInputParaCepLotacao(event) {
  document.querySelector("#cepLotacao").value = cepInput.value;
}
function municipioParaMunicipioLotacao(event) {
  CarregaMunicipiosLotacao(ufOption.value, municipioOption.value);
  document.querySelector("#municipioLotacao").selectedIndex =
    municipioOption.selectedIndex;
}
function unidadeFederativaParaUnidadeFederativaLotacao(event) {
  CarregaMunicipiosLotacao(ufOption.value, municipioOption.value);
  document.querySelector("#unidadeFederativaLotacao").selectedIndex =
    ufOption.selectedIndex;
}

function passarValores() {
  CarregaMunicipiosLotacao(ufOption.value, municipioOption.value);
  document.querySelector("#cepLotacao").value = cepInput.value;
  document.querySelector("#unidadeFederativaLotacao").value = ufOption.value;
  document.querySelector("#municipioLotacao").value = municipioOption.value;

  cepInput.addEventListener("change", cepInputParaCepLotacao);
  ufOption.addEventListener(
    "change",
    unidadeFederativaParaUnidadeFederativaLotacao
  );
  municipioOption.addEventListener("change", municipioParaMunicipioLotacao);
}

// pegar a data atual, formatar e atribuir ao max do data de nascimento
var hoje = new Date();
var dia = String(hoje.getDate()).padStart(2, "0");
var mes = String(hoje.getMonth() + 1).padStart(2, "0");
var ano = hoje.getFullYear();
hoje = ano + "-" + mes + "-" + dia;
document.querySelector("#dataDeNascimento").setAttribute("max", hoje);
