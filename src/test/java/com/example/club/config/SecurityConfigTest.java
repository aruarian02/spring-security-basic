package com.example.club.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void passwordTest() {
        String password = "1111";

        String encodedPassword = passwordEncoder.encode(password);

        System.out.println("암호화된 패스워드: " + encodedPassword);

        boolean matches = passwordEncoder.matches(password, encodedPassword);

        System.out.println("비밀번호 매치 결과: " + matches);
    }
}