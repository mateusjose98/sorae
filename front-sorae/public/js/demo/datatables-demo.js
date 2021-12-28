// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable({
      "order": [[ 1, "asc" ]],
      "language": {
            "lengthMenu": "Visualizar _MENU_ registros por página",
            "zeroRecords": "Nada encontrado",
            "info": "Visualizando página _PAGE_ de _PAGES_",
            "infoEmpty": "Nenhum registro disponível",
            "infoFiltered": "(filtrado de _MAX_ registros totais)",
            "search": "Pesquisar",
            "next" : "Próxima",
            "dataTable_previous": "Anterior",
            "oPaginate": {
                "sNext": "Próximo",
                "sPrevious": "Anterior",
                "sFirst": "Primeiro",
                "sLast": "Último"
            },
        }
  });
  
  $('#dataTableUser').DataTable({
      "order": [[ 0, "asc" ]],
      "language": {
            "lengthMenu": "Visualizar _MENU_ registros por página",
            "zeroRecords": "Nada encontrado",
            "info": "Visualizando página _PAGE_ de _PAGES_",
            "infoEmpty": "Nenhum registro disponível",
            "infoFiltered": "(filtrado de _MAX_ registros totais)",
            "search": "Pesquisar",
            "next" : "Próxima",
            "dataTable_previous": "Anterior",
            "oPaginate": {
                "sNext": "Próximo",
                "sPrevious": "Anterior",
                "sFirst": "Primeiro",
                "sLast": "Último"
            },
        }
  });
});
