$(document).ready(function () {
  CarregaMunicipios($("#unidadeFederativaId").val(), $("#municipioId").val());
});

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

function CarregaMunicipios(idUnidadeFederativa, idMunicipio) {
  $.ajaxSetup({
    headers: { "X-CSRF-TOKEN": $('meta[name="csrf-token"]').attr("content") },
  });

  $.ajax({
    url: "http://localhost:8080/api/v1/municipio/idUf/" + idUnidadeFederativa,
    method: "get",
    data: { idUf: 21 },
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
    municipioDiv.className += " d-none";
    ufDiv.className += " d-none";
    cepDiv.className += " d-none";

    passarValores();
  } else {
    municipioDiv.className = "col-sm-4";
    ufDiv.className = "col-sm-4";
    cepDiv.className = "col-sm-4";
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

$(document).ready(function ($) {
  $("#cpf").mask("999.999.999-99");
  $("#telefoneCelular").mask("(99) 9 9999-9999");
  $("#telefoneResidencial").mask("(99) 9999-9999");
});

function passarValores() {
  document.querySelector("#cepLotacao").value = cepInput.value;
  document.querySelector("#ufLotacao").value = ufOption.value;
  document.querySelector("#municipioLotacao").value = municipioOption.value;

  cepInput.addEventListener("change", (event) => {
    document.querySelector("#cepLotacao").value = cepInput.value;
  });
  ufOption.addEventListener("change", (event) => {
    document.querySelector("#ufLotacao").value = ufOption.value;
  });
  municipioOption.addEventListener("change", (event) => {
    document.querySelector("#municipioLotacao").value = municipioOption.value;
  });
}
