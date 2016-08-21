package ru.analteam.gtracks.model.converter.security;

import ru.analteam.gtracks.dto.UserDto;
import ru.analteam.gtracks.model.security.SecUser;

/**
 * Created by dima-pc on 21.08.2016.
 */
public class SecUser2UserDtoFullConverter extends SecUser2UserDtoSecuredConverter {
    @Override
    public UserDto convert(SecUser source) {
        UserDto result = super.convert(source);
        result.setPassword(source.getPassword());
        return result;
    }
}
