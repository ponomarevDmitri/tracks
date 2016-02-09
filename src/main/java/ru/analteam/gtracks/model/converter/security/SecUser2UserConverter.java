package ru.analteam.gtracks.model.converter.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.User;
import ru.analteam.gtracks.model.security.SecUser;

/**
 * Created by dima-pc on 07.02.2016.
 */
public class SecUser2UserConverter implements Converter<SecUser, User> {
    public User convert(SecUser secUser) {

        User user = new User();
        return user;
    }
}
