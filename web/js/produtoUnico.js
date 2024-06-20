function showAlert(event) {
    event.preventDefault();
    swal('Opa! Calma ae...', 'Você precisa estar logado para adicionar ao carrinho!', 'error');
}

function updateSelectedFlavors() {
    var checkboxes = document.querySelectorAll('#sabores input[type="checkbox"]');
    var selectedFlavors = [];
    var totalAdditionalCost = 0;

    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked) {
            var flavorName = checkbox.getAttribute('data-nome');
            var flavorCost = parseFloat(checkbox.getAttribute('data-valor'));
            selectedFlavors.push(flavorName + ' (+$' + flavorCost.toFixed(2) + ')');
            totalAdditionalCost += flavorCost;
        }
    });

    var selectedFlavorsList = document.getElementById('selectedFlavorsList');
    selectedFlavorsList.innerHTML = '';

    selectedFlavors.forEach(function (flavor) {
        var li = document.createElement('li');
        li.textContent = flavor;
        selectedFlavorsList.appendChild(li);
    });

    var totalAdditionalCostElement = document.getElementById('totalAdditionalCost');
    totalAdditionalCostElement.textContent = 'Custo adicional total: $' + totalAdditionalCost.toFixed(2);
}

function validateForm() {
    var checkboxes = document.querySelectorAll('#sabores input[type="checkbox"]');
    var isChecked = false;

    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked) {
            isChecked = true;
        }
    });

    if (!isChecked) {
        document.getElementById('error-message').style.display = 'block';
    } else {
        document.getElementById('error-message').style.display = 'none';
        // Submeter o formulário aqui
        document.forms["myForm"].submit();
    }
}


 document.addEventListener('DOMContentLoaded', function () {
                var input = document.querySelector('.input-number input[type="number"]');
                var btnUp = document.querySelector('.qty-up');
                var btnDown = document.querySelector('.qty-down');
                var step = parseFloat(input.getAttribute('step'));
                var min = parseFloat(input.getAttribute('min'));

                btnUp.addEventListener('click', function () {
                    var oldValue = parseFloat(input.value);
                    var newVal = oldValue + step;
                    input.value = newVal.toFixed(1);
                });

                btnDown.addEventListener('click', function () {
                    var oldValue = parseFloat(input.value);
                    if (oldValue > min) {
                        var newVal = oldValue - step;
                        input.value = newVal.toFixed(1);
                    }
                });
            });