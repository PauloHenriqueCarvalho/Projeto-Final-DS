/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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

function showAlertRemover(event) {
    event.preventDefault();
    swal('Endereco removido com sucesso!', '', 'success').then(() => {
        event.target.closest('form').submit();
    });
}