package com.Cursojava.Curso.Controller;

import com.Cursojava.Curso.Dao.UsuarioDao;
import com.Cursojava.Curso.Model.User;
import com.Cursojava.Curso.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login",method = RequestMethod.POST)
    public String login(@RequestBody User user){

        User usuarioLoggeado = usuarioDao.obtenerUsuarioPorCredenciales(user);
       if(usuarioLoggeado != null){
           String tokenJWT = jwtUtil.create(String.valueOf(usuarioLoggeado.getId()),usuarioLoggeado.getEmail());
           return tokenJWT;
       }
       return "FAIL";
    }


}
