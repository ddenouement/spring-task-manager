package com.example.taskmanager.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class RedirectOnLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = getUrl(authentication);

        if (response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    protected String getUrl(Authentication authentication) {
        String url = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("Logged in" + authentication.getPrincipal());
        List<String> roles = new ArrayList();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        System.out.println("Logged in role" + roles.get(0));
        if (roles.contains("ROLE_ADMIN")) {
            url = "/admin/dashboard";
        } else if (roles.contains("ROLE_USER")) {
            url = "/users/dashboard";
        } else {
            url = "/login?error";
        }

        return url;
    }
}
