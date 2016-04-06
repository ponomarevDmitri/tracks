package ru.analteam.gtracks.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.analteam.gtracks.auxiliary.ListConverter;
import ru.analteam.gtracks.model.converter.security.SecRole2GrantedAuthorityConverter;
import ru.analteam.gtracks.model.converter.security.SecUser2UserConverter;
import ru.analteam.gtracks.model.security.SecRole;
import ru.analteam.gtracks.model.security.SecUser;

import javax.persistence.*;
import javax.transaction.TransactionManager;
import java.util.List;

/**
 * Created by dima-pc on 06.02.2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager em;
    Converter<SecUser, User> secUser2UserConverter;

//    @Autowired
//    private ShaPasswordEncoder passwordEncoder; //todo initiate

    public UserDetailsServiceImpl() {
        secUser2UserConverter = new SecUser2UserConverter(new SecRole2GrantedAuthorityConverter());
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TypedQuery<SecUser> query = em.createQuery("select su from SecUser su where su.username = :name", SecUser.class);
        query.setParameter("name", s);

        SecUser user = query.getSingleResult();
        User converted = secUser2UserConverter.convert(user);
        return converted;
    }
}
