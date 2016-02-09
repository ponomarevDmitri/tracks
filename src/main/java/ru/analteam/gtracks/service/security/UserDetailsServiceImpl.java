package ru.analteam.gtracks.service.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.analteam.gtracks.model.security.SecUser;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by dima-pc on 06.02.2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    EntityManager em;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Query query = em.createQuery("select su from SecUser su where su.name = :name", SecUser.class);
        query.setParameter("name", s);

//        UserDetails ud = new User();
//        ud.set

        List result = query.getResultList();
        return null;//todo
    }
}
