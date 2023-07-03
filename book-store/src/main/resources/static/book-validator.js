function validate() {
    var title = document.getElementById("title");
    var author = document.getElementById("author");
    var price = document.getElementById("price");
    var quantity = document.getElementById("quantity");
    var isbn = document.getElementById("isbn");
    var info = document.getElementById("info");

    var titleRegex = /^.+$/;
    var authorRegex = /^[A-Z][a-z]+( [A-Z][a-z]+)? [A-Z][a-z]+([ -][A-Z][a-z]+)?$/;
    var priceRegex = /^[0-9]+\.[0-9]{1,2}$/;
    var quantityRegex = /^[0-9]+$/;
    var isbnRegex = /^(978|979)-[0-9]{2}-[0-9]{2,6}-[0-9]{1,5}-[0-9]$/;

    var result = true;
    var infoResult = "";

    if(!titleRegex.test(title.value)) {
        infoResult = infoResult + "Zły tytuł. ";
        title.style.background = "#fcc2c2";
        result = false;
    } else {
        title.style.background = "#ffffff";
    }

    if(!authorRegex.test(author.value)) {
        infoResult = infoResult + "Zły autor. ";
        author.style.background = "#fcc2c2";
        result = false;
    } else {
        author.style.background = "#ffffff";
    }

    if(!priceRegex.test(price.value) || price.value <= 0) {
        infoResult = infoResult + "Zła cena. ";
        price.style.background = "#fcc2c2";
        result = false;
    } else {
        price.style.background = "#ffffff";
    }

    if(!quantityRegex.test(quantity.value)) {
        infoResult = infoResult + "Zła ilość. ";
        quantity.style.background = "#fcc2c2";
        result = false;
    } else {
        quantity.style.background = "#ffffff";
    }

    if(!isbnRegex.test(isbn.value)) {
        infoResult = infoResult + "Zły ISBN. ";
        isbn.style.background = "#fcc2c2";
        result = false;
    } else {
        isbn.style.background = "#ffffff";
    }

    info.innerHTML = infoResult;
    return result;
}