package com.challenge.foro_hub.infra.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class LOL {

        public static void main(String[] args) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            System.out.println(encoder.encode("123456")); // reemplazá por la contraseña real
        }
}
