package io.userauth.data.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.userauth.models.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class UserRepositoryImpl implements UserRepository {
    
    @PersistenceContext
    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void createUser(User user){
        entityManager.persist(user);
    }


    @Override
    @Transactional(readOnly = true)
    public User findById(int id){
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByName(String name) {
        final TypedQuery<User> query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.name = :name", User.class);
        return query.setParameter("name", name).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email){
        final TypedQuery<User> query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email", User.class);
        return query.setParameter("email", email).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        final TypedQuery<User> query = entityManager.createQuery("SELECT u FROM UserEntity u", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateEmail(int id, String newEmail) {
        User user = entityManager.find(User.class, id);
        if (user != null){
            user.setEmail(newEmail);
        }
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
      
    
}
