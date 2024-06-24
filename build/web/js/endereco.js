document.addEventListener("DOMContentLoaded", () => {
    const addModal = document.getElementById("add-modal");
    const closeAddModal = document.querySelector(".close-add");
    const addButton = document.getElementById("add-btn");
    
    addButton.addEventListener("click", () => {
        addModal.style.display = "block";
    });

    closeAddModal.addEventListener("click", () => {
        addModal.style.display = "none";
    });




});
        