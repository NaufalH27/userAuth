package io.userauth.data.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.userauth.models.entities.UserEntity;
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
    public void createUser(UserEntity user){
        entityManager.persist(user);
    }


    @Override
    @Transactional(readOnly = true)
    public UserEntity findById(int id){
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findByName(String name) {
        final TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.name = :name", UserEntity.class);
        return query.setParameter("name", name).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findByEmail(String email){
        final TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class);
        return query.setParameter("email", email).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Transactional
    public List<UserEntity> getAllUsers() {
        final TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateEmail(int id, String newEmail) {
        UserEntity user = entityManager.find(UserEntity.class, id);
        if (user != null){
            user.setEmail(newEmail);
        }
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        UserEntity user = entityManager.find(UserEntity.class, id);
        entityManager.remove(user);
    }
      
    
}
