package ru.analteam.gtracks.repository;

import org.springframework.stereotype.Repository;
import ru.analteam.gtracks.model.security.SecUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by dima-pc on 07.04.2016.
 */
@Repository
public class SecUserDao implements ISecUserDao {

    @PersistenceContext
    private EntityManager em;

    public SecUser findUserByName(String username) {
        TypedQuery<SecUser> query = em.createQuery(
                "select su from SecUser su where su.username = :name", SecUser.class);
        query.setParameter("name", username);
        return query.getSingleResult();
    }
}
