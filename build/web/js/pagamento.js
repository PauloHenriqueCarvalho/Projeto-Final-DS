document.addEventListener('DOMContentLoaded', function () {
    const paymentMethods = document.querySelectorAll('input[name="payment"]');
    const cardInfo = document.querySelector('.card-info');
    const pixInfo = document.querySelector('.pix-info');
    const cardNumber = document.getElementById('card-number');
    const cardName = document.getElementById('card-name');
    const cardExpiry = document.getElementById('card-expiry');
    const cardCVV = document.getElementById('card-cvv');
    const form = document.getElementById('payment-form');

    const cardNumberError = document.getElementById('card-number-error');
    const cardNameError = document.getElementById('card-name-error');
    const cardExpiryError = document.getElementById('card-expiry-error');
    const cardCVVError = document.getElementById('card-cvv-error');

    paymentMethods.forEach(method => {
        method.addEventListener('change', function () {
            if (this.id === 'cartao') {
                cardInfo.style.display = 'block';
                pixInfo.style.display = 'none';
            } else if (this.id === 'pix') {
                pixInfo.style.display = 'block';
                cardInfo.style.display = 'none';
            } else {
                cardInfo.style.display = 'none';
                pixInfo.style.display = 'none';
            }
        });
    });

    if (document.getElementById('cartao').checked) {
        cardInfo.style.display = 'block';
    } else if (document.getElementById('pix').checked) {
        pixInfo.style.display = 'block';
    }


    cardName.addEventListener('input', function () {
        if (!validateCardName(cardName.value)) {
            cardNameError.textContent = 'Nome no cartão inválido.';
        } else {
            cardNameError.textContent = '';
        }
    });

    cardExpiry.addEventListener('input', function () {
        if (!validateCardExpiry(cardExpiry.value)) {
            cardExpiryError.textContent = 'Validade do cartão inválida.';
        } else {
            cardExpiryError.textContent = '';
        }
    });

    cardCVV.addEventListener('input', function () {
        if (!validateCardCVV(cardCVV.value)) {
            cardCVVError.textContent = 'CVV do cartão inválido.';
        } else {
            cardCVVError.textContent = '';
        }
    });

    form.addEventListener('submit', function (event) {
        if (document.getElementById('cartao').checked) {


            if (!validateCardName(cardName.value)) {
                swal('Opa! Calma ae...', 'Nome do cartão invalido!', 'error');
                event.preventDefault();
                return;
            }

            if (!validateCardExpiry(cardExpiry.value)) {
                swal('Opa! Calma ae...', 'Validade do cartão invalida!', 'error');
                event.preventDefault();
                return;
            }

            if (!validateCardCVV(cardCVV.value)) {
                swal('Opa! Calma ae...', 'CVV do cartão invalido!', 'error');
                event.preventDefault();
                return;
            }
        }
    });


    function validateCardName(name) {
        return name.trim() !== '';
    }

    function validateCardExpiry(expiry) {
        const regex = /^(0[1-9]|1[0-2])\/\d{2}$/;
        return regex.test(expiry);
    }

    function validateCardCVV(cvv) {
        const regex = /^[0-9]{3,4}$/;
        return regex.test(cvv);
    }

    document.getElementById('copy-button').addEventListener('click', function () {
        var pixKeyInput = document.getElementById('pix-key');

        pixKeyInput.select();
        pixKeyInput.setSelectionRange(0, 99999);
        document.execCommand('copy');

        alert('Chave Pix copiada: ' + pixKeyInput.value);
    });
});

function validateInput(event) {
    const char = String.fromCharCode(event.which);
    if (!/[a-zA-Z]/.test(char)) {
        event.preventDefault();
    }
}
function removeCarrinho(event) {
    event.preventDefault();
    swal('Removido Com Sucesso!', 'Não deixe para depois, compre agora!', 'success').then(() => {
        event.target.closest('form').submit();
    });
}