package com.Cursojava.Curso.Controller;
import com.Cursojava.Curso.Dao.UsuarioDao;
import com.Cursojava.Curso.Model.User;
import com.Cursojava.Curso.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;


    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    //Listar Usuario
    @RequestMapping(value = "api/Users",method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token){
        if(!validarToken(token)){return null;}
        return usuarioDao.getUsuario();
    }

    //Registrar Usuario
    @RequestMapping(value = "api/Users",method = RequestMethod.POST)
    public void registrarUsers(@RequestBody User user){
        usuarioDao.registrar(user);
    }



    //Eliminar Usuario
    @RequestMapping(value = "api/Users/{id}",method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
        if(!validarToken(token)){return ;}
    usuarioDao.eliminar(id);
    }



}
