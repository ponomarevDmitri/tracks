package ru.analteam.gtracks.model.converter.security;

import com.sun.istack.internal.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.analteam.gtracks.auxiliary.ListConverter;
import ru.analteam.gtracks.model.security.SecRole;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima-pc on 07.02.2016.
 */
public class SecRole2GrantedAuthorityConverter implements ListConverter<SecRole, GrantedAuthority> {
    public GrantedAuthority convert(SecRole secRole) throws AuthenticationException {
        if (secRole == null) {
            throw new AuthenticationServiceException("SecRole is not specified (null)");
        }
        return new SimpleGrantedAuthority(secRole.getName());
    }

    @NotNull
    public List<GrantedAuthority> convert(@Nullable List<SecRole> sourceList) {
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();

        for (SecRole role : sourceList) {
            if (role != null) {
                result.add(convert(role));
            }
        }

        return result;
    }
}
