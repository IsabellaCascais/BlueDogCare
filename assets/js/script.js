document.addEventListener("DOMContentLoaded", function() {
    const servicos = document.querySelectorAll("input[name='servico']");
    const opcaoDayCare = document.getElementById("opcao-daycare");

    servicos.forEach(servico => {
        servico.addEventListener("change", function() {
            if (this.value === "daycare") {
                opcaoDayCare.style.display = "block";
            } else {
                opcaoDayCare.style.display = "none";
            }
        });
    });
});