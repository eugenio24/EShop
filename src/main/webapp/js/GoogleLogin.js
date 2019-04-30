
function start() {
    gapi.load('auth2', function() {
        auth2 = gapi.auth2.init({
            client_id: '480786958905-knf9821bhhm4aanvkjtcb72iuu7kthe3.apps.googleusercontent.com'
        });
    });
}

function btnGoogle_onClick(){
    auth2.grantOfflineAccess().then(signInCallback);
}

function signInCallback(authResult) {
    if (authResult['code']) {
        console.log(authResult['code']);
        
        $.ajax({
            type: 'POST',
            url: 'GoogleLogin',
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            },
            data: { 
                'code': authResult['code'] 
            },
            success: function (data) {
                data = JSON.parse(data);
                if(data.success){
                    location.href = data.url;                
                }else{
                    //todo stampare errore
                }
            }
        });
    } else {
        // todo: dare un feedback all'utente per l'errore
        console.log("Error during Google Login");
    }
}