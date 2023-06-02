$(document).ready(function() {
    //on ready
});

async function registrarUsuarios(){
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.pasword = document.getElementById('txtPasword').value;

    let repetirPasword = document.getElementById('txtRepetirPasword').value;

    if(repetirPasword !== datos.pasword){
        alert("El Pasword No es incompatible");
        return;
    }


    const request = await fetch('api/Users', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    alert("Tu cuenta fue creada Satisfacotriamente");
    window.location.href = 'login.html';
}