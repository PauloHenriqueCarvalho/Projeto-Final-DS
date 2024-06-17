/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.addEventListener('DOMContentLoaded', function() {
    const paymentMethods = document.querySelectorAll('input[name="payment"]');
    const cardInfo = document.querySelector('.card-info');
    const cardNumber = document.getElementById('card-number');
    const cardName = document.getElementById('card-name');
    const cardExpiry = document.getElementById('card-expiry');
    const cardCVV = document.getElementById('card-cvv');
    const form = document.querySelector('form');  // Assuming your form element is here

    paymentMethods.forEach(method => {
        method.addEventListener('change', function() {
            if (this.id === 'cartao') {
                cardInfo.style.display = 'block';
            } else {
                cardInfo.style.display = 'none';
            }
        });
    });

    // Ensure the correct state on page load
    if (document.getElementById('cartao').checked) {
        cardInfo.style.display = 'block';
    }
    
    

    // Add form submission event listener to validate card info
    form.addEventListener('submit', function(event) {
        if (document.getElementById('cartao').checked) {
            if (!validateCardNumber(cardNumber.value)) {
                alert('Número do cartão inválido.');
                event.preventDefault();
                return;
            }

            if (!validateCardName(cardName.value)) {
                alert('Nome no cartão inválido.');
                event.preventDefault();
                return;
            }

            if (!validateCardExpiry(cardExpiry.value)) {
                alert('Validade do cartão inválida.');
                event.preventDefault();
                return;
            }

            if (!validateCardCVV(cardCVV.value)) {
                alert('CVV do cartão inválido.');
                event.preventDefault();
                return;
            }
        }
    });

    function validateCardNumber(number) {
        // Simple validation for card number (example only, use more robust validation in production)
        const regex = /^[0-9]{16}$/;
        return regex.test(number);
    }

    function validateCardName(name) {
        // Simple validation for card name
        return name.trim() !== '';
    }

    function validateCardExpiry(expiry) {
        // Simple validation for card expiry (MM/YY)
        const regex = /^(0[1-9]|1[0-2])\/\d{2}$/;
        return regex.test(expiry);
    }

    function validateCardCVV(cvv) {
        // Simple validation for CVV (3 or 4 digits)
        const regex = /^[0-9]{3,4}$/;
        return regex.test(cvv);
    }
});

 document.getElementById('copy-button').addEventListener('click', function() {
            // Seleciona o campo de entrada da chave Pix
            var pixKeyInput = document.getElementById('pix-key');
            
            // Seleciona o texto do campo de entrada
            pixKeyInput.select();
            pixKeyInput.setSelectionRange(0, 99999); // Para dispositivos móveis

            // Copia o texto selecionado para a área de transferência
            document.execCommand('copy');

            // Alerta o usuário que a chave foi copiada
            alert('Chave Pix copiada: ' + pixKeyInput.value);
        });