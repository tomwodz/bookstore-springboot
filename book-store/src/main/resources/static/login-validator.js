function validate() {
    var login = document.getElementById("login");
    var password = document.getElementById("password");
    var info = document.getElementById("info")

    var passwordRegex = /^.{3,}$/;
    var loginRegex = /^.{3,}$/;

    var result = true;
    var infoResult = "";


    if (!loginRegex.test(login.value)) {
        infoResult = infoResult + "Zły login.\t";
        login.style.background = "#fcc2c2";
        result = false;
    } else {
        login.style.background = "#ffffff";
    }
    if (!passwordRegex.test(password.value)) {
        infoResult = infoResult + "Złe hasło.";
        password.style.background = "#fcc2c2";
        result = false;
    } else {
        password.style.background = "#ffffff";
    }
    info.innerHTML = infoResult;
    return result;
}