package ru.analteam.gtracks.model.converter.security;

import org.springframework.core.convert.converter.Converter;
import ru.analteam.gtracks.dto.UserDto;
import ru.analteam.gtracks.model.security.SecUser;

/**
 * Created by dima-pc on 21.08.2016.
 */
public class SecUser2UserDtoSecuredConverter implements Converter<SecUser, UserDto> {
    @Override
    public UserDto convert(SecUser source) {
        UserDto userDto = new UserDto();
        userDto.setUsername(source.getUsername());
        return userDto;
    }
}
