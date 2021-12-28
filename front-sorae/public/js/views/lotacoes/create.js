
var prefix = "/funben/credenciada";

$('#unidadeFederativa').change(function()
{
    if($('#unidadeFederativa').val() == '')
    {
        $('#municipio option').remove();
        $("#municipio").prepend('<option value="">Selecione um Município</option>');
        $("#municipio").prop('disabled',true);
    }
    else
    {
        CarregaMunicipios($('#unidadeFederativa').val(),null);
    }
});

function CarregaMunicipios(idUnidadeFederativa, idMunicipio)
{
    $.ajaxSetup({headers:{'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')}});

    $.ajax(
    {
        url: "http://localhost:8080/api/v1/municipio/idUf/" + idUnidadeFederativa,
        method: 'get',
        data:{},
        headers: {
            "content-type": "application/json",
            "Authorization": $('#token').val()
        },
        beforeSend: function()
        {
			$("#municipio option").remove();
            ModalAjaxCarregando(true,$(".container-fluid"),'Carregando...');
        },
        complete: function(){ModalAjaxCarregando(false,$(".container-fluid"),null);},
        success: function(resultado)
        {
            if(!resultado.data.length){
                $('#municipio').append('<option value="">Nenhum Município</option>');
                $("#municipio").prop('disabled',false);
            }
            else
            {
                resultado.data.forEach(item =>
                {
                    $("#municipio").append('<option value="' + item.id + '">' + item.nome + '</option>');
                }); 
                $("#municipio").prop('disabled',false);
    
                if (idMunicipio != null)
                {
                    $("#municipio").val(idMunicipio);
                }
            } 
        
        },
        error:function(error)
        {
            // MensagemBox('erro','Erro',(error.responseJSON.exception != null && error.responseJSON.exception != '' ?  error.responseJSON.exception : error.responseJSON));
        }
    });
}

function ModalAjaxCarregando(ativo,componente,texto)
{
    if(ativo)
    {
        $('<div id="' + componente.attr('id') + 'ajaxModal" class="modal " style="position:absolute !important;" role="dialog">'
        +    '<div class="modal-dialog">'
        +        '<div class="modal-content">'
        +           '<div class="modal-body">'
        +               '<div class="row">'
        +                   '<div class="col-md-2">'
        +                       '<div class="lds-dual-ring"></div>'
        +                   '</div>'
        +                   '<div class="col-md-9">'
        +                       '<h2>' + texto + '</h2>'
        +                   '</div>'
        +             '</div>'
        +          '</div>'
        +      '</div>'
        +   '</div>'
        +' </div>').appendTo('#' + componente.attr('id'));

        $('#' + componente.attr('id') + 'ajaxModal').modal("show");
    }
    else
    {
        $('#' + componente.attr('id') + 'ajaxModal').modal("hide");
        $('#' + componente.attr('id') + 'ajaxModal').remove();
    }
}