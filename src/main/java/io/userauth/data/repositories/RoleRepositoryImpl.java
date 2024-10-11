package io.userauth.data.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.userauth.models.Roles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


@Repository
public class RoleRepositoryImpl implements RoleRepository{

    private final EntityManager entityManager;

    public RoleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
        
    @Override
    public List<Roles> getAllRoles() {
        final TypedQuery<Roles> query = entityManager.createQuery("SELECT u FROM Roles u", Roles.class);
        return query.getResultList();
    }

    @Override
    public Roles getRoleByName(String roleName) {
        final TypedQuery<Roles> query = entityManager.createQuery("SELECT u FROM Roles u WHERE u.name = :name", Roles.class);
        return query.setParameter("name",roleName).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void createRole(Roles role){
        entityManager.persist(role);
    }

}
