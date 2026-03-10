package com.challenge.foro_hub.infra.security;

import com.challenge.foro_hub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        //Evitar validar token en /login
        if (path.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("Entro al filtro");
        var tokenJWT = recuperarToken(request);
        if(tokenJWT !=null){
            var subject=tokenService.getSubject(tokenJWT);
            var usuario= repository.findByCorreoElectronico(subject);
            var authenticaction=new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticaction);
            System.out.println("Usuario Logeado!");
        }
        filterChain.doFilter(request,response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var autherizationHeader=request.getHeader("Authorization");
        if (autherizationHeader !=null){
            return autherizationHeader.replace("Bearer ", "");
        }
        return null;
    }

}
