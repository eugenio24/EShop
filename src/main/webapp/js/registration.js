
function validateForm(){
    
    let isValid = true;
    
    let name = document.getElementById("name").value;
    let surname = document.getElementById("surname").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("psw").value;
    let cPassword = document.getElementById("cPsw").value;
    
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
            document.getElementById("usernameError").innerHTML = "*Username is not 8 charachters long";
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
        document.getElementById("passwordError").innerHTML = "*The password is not 8 charachters long";
    }else{        
        if(password != cPassword){
            isValid = false;
            document.getElementById("passwordError").innerHTML = "*Passwords doesn't match";
        }else{
            document.getElementById("passwordError").innerHTML = "";
        }
    }

    return isValid;
}
