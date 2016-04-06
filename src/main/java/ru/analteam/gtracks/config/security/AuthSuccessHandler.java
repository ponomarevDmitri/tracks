package ru.analteam.gtracks.config.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dima-pc on 04.04.2016.
 */
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {

        return super.determineTargetUrl(request, response);
    }
}
