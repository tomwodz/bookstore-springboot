function addToCart(id){
    $.ajax({
        url: "http://localhost:8080/api/v1/cart/add/" + id,
        method: "GET"
    })
    .done(wynik => {

    });

}

function saveBook(){
    var book = {
        id: 0,
        title: "Nowa książka z Javy",
        author: "Tadeusz Kowalski",
        price: 100.00,
        quantity: 100,
        isbn: "978-83-832-2156-4"
    };

    $.ajax({
        url: "http://localhost:8080/api/v1/book/",
        method: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(book)
    })
        .done(book => {
            console.log(book.id);
            console.log(book.title);
            console.log(book.author);
            console.log(book.price);
            console.log(book.quantity);
            console.log(book.isbn);
        });
}