/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


const form = document.getElementById("form-login");

form.addEventListener("submit", function (event) {
    event.preventDefault();
    
    const usuario = form.getElementsById("usuario").value.trim();
    const senha = form.getElementsById("senha").value.trim();
    
    
    
})