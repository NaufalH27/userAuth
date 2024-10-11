package io.userauth.data.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.userauth.models.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class UserRepositoryImpl implements UserRepository {
    
    @PersistenceContext
    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void createUser(Users user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Users findById(UUID id){
        return entityManager.find(Users.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Users findByName(String name) {
        final TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.username = :username", Users.class);
        return query.setParameter("username", name).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Users findByEmail(String email) {
        final TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class);
        return query.setParameter("email", email).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Transactional
    public List<Users> getAllUsers() {
        final TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u", Users.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        Users user = entityManager.find(Users.class, id);
        entityManager.remove(user);
    }
}
