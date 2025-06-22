let precoTotal = 0;
let qtdTotal = 0;

const inputQtdTotal = document.getElementById("quantidades");
const inputPrecoTotal = document.getElementById("total");

function addProduct(number) {
    const quantidadeProdutoSelecionado = document.getElementById("qty" + number);
    quantidadeProdutoSelecionado.value++;
    calculate();
}

function calculate() {
    let precAtual, qtdAtual;
    precoTotal = 0;
    qtdTotal = 0;

    for (let i = 1; i <= 4; i++) { // Adjusted for the actual number of products
        const precoElement = document.getElementById('price' + i);
        const qtdElement = document.getElementById('qty' + i);

        if (precoElement && qtdElement) {
            precAtual = parseFloat(precoElement.textContent);
            qtdAtual = parseFloat(qtdElement.value);

            precoTotal += precAtual * qtdAtual;
            qtdTotal += qtdAtual;
        }
    }

    // Apply discounts
    if (precoTotal > 100) precoTotal *= 0.95;
    if (qtdTotal >= 5) precoTotal *= 0.95;

    inputQtdTotal.innerText = qtdTotal;
    inputPrecoTotal.innerText = precoTotal.toFixed(2);
}

function valid() {
    if (precoTotal <= 0 && qtdTotal <= 0) {
        alert("Erro: o carrinho está vazio");
        return false;
    }
    window.location.href = "checkout.html";
    return true;
}


function clean() {
    for (let i = 1; i <= 4; i++) { // Adjusted for the actual number of products
        const qtdElement = document.getElementById('qty' + i);
        if (qtdElement) qtdElement.value = 0;
    }
    precoTotal = 0;
    qtdTotal = 0;
    inputQtdTotal.innerText = 0;
    inputPrecoTotal.innerText = "0.00";
}
