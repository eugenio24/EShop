function validateForm(){
    
    var isValid = true;
    
    var name = document.getElementById("name").value;
    var surname = document.getElementById("surname").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("psw").value;
    var cPassword = document.getElementById("cPsw").value;
    
    if(name.match(/\d+/g)){
        document.getElementById("nameError").innerHTML = "*Name contains numbers";
        isValid = false;
    }else{
        document.getElementById("nameError").innerHTML = "";
    }
    
    if(surname.match(/\d+/g)){
        document.getElementById("surnameError").innerHTML = "*Surname contains numbers";
        isValid = false;
    }else{
        document.getElementById("surnameError").innerHTML = "";
    }
    
    if(username.match(/^[0-9a-zA-Z]+$/)){
        if(username.length < 8){
            document.getElementById("usernameError").innerHTML = "*Username is not 8 charachter long";
            isValid = false;
        }else{
            document.getElementById("usernameError").innerHTML = "";
        }
    }else{
        document.getElementById("usernameError").innerHTML = "*Username contain unwanted charachters or spaces";
        isValid = false;
    }
    
    if(password.length < 8){
        isValid = false;
        document.getElementById("passwordError").innerHTML = "*The password is not 8 charachter long";
    }else{
        console.log("ciao come va");
        if(password != cPassword){
            isValid = false;
            document.getElementById("passwordError").innerHTML = "*The passwords is not coincide";
        }else{
            document.getElementById("passwordError").innerHTML = "";
        }
    }

    return isValid;
}
