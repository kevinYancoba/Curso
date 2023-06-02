package com.Cursojava.Curso.Dao;
import com.Cursojava.Curso.Model.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsuario() {
        String query = "FROM User";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void registrar(User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,user.getPasword());
        user.setPasword(hash);
        entityManager.merge(user);
    }

    @Override
    public User obtenerUsuarioPorCredenciales(User user) {
        String query = "FROM User where email = :email";

        List<User> lista = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        if(lista.isEmpty()){
            return null;
        }

        String paswordHashed = lista.get(0).getPasword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(paswordHashed, user.getPasword())){
            return lista.get(0);
    }
        return null;
    }
}
