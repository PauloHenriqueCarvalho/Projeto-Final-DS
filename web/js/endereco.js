document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("modal");
    const addModal = document.getElementById("add-modal");
    const closeModal = document.querySelector(".close");
    const closeAddModal = document.querySelector(".close-add");
    const editButtons = document.querySelectorAll(".edit-btn");
    const addButton = document.getElementById("add-btn");
    const form = document.getElementById("edit-form");
    const addForm = document.getElementById("add-form");
    const cardsEndereco = document.querySelector(".cards-endereco");

    editButtons.forEach(button => {
        button.addEventListener("click", () => {
            modal.style.display = "block";
            const enderecoInfo = button.closest(".informacoes-endereco");
            form.nome.value = enderecoInfo.querySelector("p").textContent;
            form.rua.value = enderecoInfo.querySelector("h3:nth-child(3)").textContent;
            form.cidade.value = enderecoInfo.querySelector("h3:nth-child(4)").textContent.split(",")[0];
            form.estado.value = enderecoInfo.querySelector("h3:nth-child(4)").textContent.split(",")[1].split("-")[0].trim();
            form.cep.value = enderecoInfo.querySelector("h3:nth-child(4)").textContent.split("-")[1].trim();
            form.telefone.value = enderecoInfo.querySelector("h3:nth-child(5)").textContent.split("-")[1].trim();
        });
    });

    closeModal.addEventListener("click", () => {
        modal.style.display = "none";
    });

    window.addEventListener("click", (event) => {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    });

    addButton.addEventListener("click", () => {
        addModal.style.display = "block";
    });

    closeAddModal.addEventListener("click", () => {
        addModal.style.display = "none";
    });

    window.addEventListener("click", (event) => {
        if (event.target == addModal) {
            addModal.style.display = "none";
        }
    });

    addForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const nome = addForm.nome.value;
        const rua = addForm.rua.value;
        const cidade = addForm.cidade.value;
        const estado = addForm.estado.value;
        const cep = addForm.cep.value;
        const telefone = addForm.telefone.value;

        const newEndereco = document.createElement("div");
        newEndereco.className = "informacoes-endereco";
        newEndereco.innerHTML = `
            <h3>Endereço Adicionado</h3>
            <p>${nome}</p>
            <h3>${rua}</h3>
            <h3>${cidade}, ${estado} - ${cep}</h3>
            <h3>TEL- ${telefone}</h3>
            <div class="button-group">
                <button class="edit-btn">Editar Endereço</button>
                <button class="remove-btn">Remover Endereço</button>
            </div>
        `;
        cardsEndereco.appendChild(newEndereco);

        addModal.style.display = "none";
    });
});
