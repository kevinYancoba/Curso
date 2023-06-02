package com.Cursojava.Curso.Dao;
import com.Cursojava.Curso.Model.User;

import java.util.List;

public interface UsuarioDao {

    public List<User> getUsuario();

    void eliminar(long id);

    void registrar(User user);


    User obtenerUsuarioPorCredenciales(User user);
}
