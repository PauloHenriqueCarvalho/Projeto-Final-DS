
jQuery(function ($) {
    $("#telefone").mask("(99)99999-9999");
    $("#cpf").mask("999.999.999-99");
    $("#card-number").mask("9999 9999 9999 9999");
    $("#card-cvv").mask("999");
    $("#card-expiry").mask("12/99");
    $("#add-cep").mask("99999999");
});

function showAlert(event) {
    event.preventDefault();
    swal('Opa! Calma ae...', 'Você precisa estar logado para adicionar à lista de desejos!', 'error');
}
function showAlert3(event) {
    event.preventDefault();
    swal('Opa! Calma ae...', 'Você já tem esse produto adicionado à Lista de Desejos', 'error');
}
function showAlert2(event) {
    event.preventDefault();
    swal('Produto removido da Lista de Desejos', '', 'success').then(() => {
        event.target.closest('form').submit();
    });
}

function showAlert4(event) {
    event.preventDefault();
    swal('Produto adicionado a Lista de Desejos', '', 'success').then(() => {
        event.target.closest('form').submit();
    });
}

function finalizarCompra(event) {
    event.preventDefault();
    swal('Pedido realizado com sucesso!', '', 'success').then(() => {
        event.target.closest('form').submit();
    });
}