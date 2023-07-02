function validate() {
    var name = document.getElementById("name");
    var surname = document.getElementById("surname");
    var email = document.getElementById("email");
    var login = document.getElementById("login");
    var password = document.getElementById("password");
    var password2 = document.getElementById("password2");
    var info = document.getElementById("info")

    var nameRegex = /^[A-Z][a-z]+$/;
    var surnameRegex = /^[A-Z][a-z]+([ -][A-Z][a-z])?$/;
    var loginRegex = /^.{3,}$/;
    var passwordRegex = /^.{3,}$/;
    var emailRegex = /^[\w\.-]+@[\w\.-]+\.[A-Aa-z0-9]{2,3}$/;

    var result = true;
    var infoResult = "";


    if (!nameRegex.test(name.value)) {
        infoResult = infoResult + "Złe imię.\t";
        name.style.background = "#fcc2c2";
        result = false;
    } else {
        name.style.background = "#ffffff";
    }

    if (!surnameRegex.test(surname.value)) {
        infoResult = infoResult + "Złe nazwisko.\t";
        surname.style.background = "#fcc2c2";
        result = false;
    } else {
        surname.style.background = "#ffffff";
    }

    if (!emailRegex.test(email.value)) {
        infoResult = infoResult + "Zły e-mail.\t";
        email.style.background = "#fcc2c2";
        result = false;
    } else {
        email.style.background = "#ffffff";
    }


    if (!loginRegex.test(login.value)) {
        infoResult = infoResult + "Zły login.\t";
        login.style.background = "#fcc2c2";
        result = false;
    } else {
        login.style.background = "#ffffff";
    }

    if (!passwordRegex.test(password.value)) {
        infoResult = infoResult + "Złe hasło.\t";
        password.style.background = "#fcc2c2";
        result = false;
    } else {
        password.style.background = "#ffffff";
    }

    if (!passwordRegex.test(password2.value)) {
        infoResult = infoResult + "Hasła nie są takie same.";
        password2.style.background = "#fcc2c2";
        result = false;
    } else {
        password2.style.background = "#ffffff";
    }

    info.innerHTML = infoResult;
    return result;
}