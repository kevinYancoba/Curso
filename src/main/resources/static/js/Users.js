// Call the dataTables jQuery plugin
$(document).ready(function() {
   cargarUsuarios();
  $('#Users').DataTable();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario(){
    document.getElementById("txt-email-usuario").outerHTML = localStorage.email;
}
async function cargarUsuarios(){
     const request = await fetch('api/Users', {
        method: 'GET',
        headers: getHeaderds()

      });
     // user usado en el for each
      const users = await request.json();


      let listadoHtml = '';
      for(let user of  users){
          let botonEliminar = '<a href="#" onclick="eliminarUsuario(' +user.id+ ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
          let telefonotxt = user.telefono == null? '--------':user.telefono;
          let usuariohtml = '<tr><td>'+ user.id +'</td><td>'+ user.nombre + ' '+ user.apellido +'</td><td>'+ user.email+'</td><td>'+ telefonotxt+'</td><td>'+ botonEliminar +'</td></tr>';
          listadoHtml += usuariohtml;
      }

      document.querySelector('#Users tbody').outerHTML = listadoHtml;
}

    function getHeaderds(){
        return {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        }
    }

    async function eliminarUsuario(id) {
        if (!confirm('¿Desea eliminar este Usuario?')){
            return;
        }

        await fetch('api/Users/' + id, {
            method: 'DELETE',
            headers: getHeaderds()
        });
        location.reload();
    }