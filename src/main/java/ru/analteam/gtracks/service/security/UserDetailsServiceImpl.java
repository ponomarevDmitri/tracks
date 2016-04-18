package ru.analteam.gtracks.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.analteam.gtracks.model.converter.security.SecRole2GrantedAuthorityConverter;
import ru.analteam.gtracks.model.converter.security.SecUser2UserConverter;
import ru.analteam.gtracks.model.security.SecUser;
import ru.analteam.gtracks.repository.SecUserDao;

/**
 * Created by dima-pc on 06.02.2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    Converter<SecUser, User> secUser2UserConverter;

    @Autowired
    private SecUserDao secUserDao;

//    @Autowired
//    private ShaPasswordEncoder passwordEncoder; //todo initiate

    public UserDetailsServiceImpl() {
        secUser2UserConverter = new SecUser2UserConverter(new SecRole2GrantedAuthorityConverter());
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecUser user = secUserDao.findUserByName(username);
        return secUser2UserConverter.convert(user);
    }
}
