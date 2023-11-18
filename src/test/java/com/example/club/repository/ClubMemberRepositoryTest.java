package com.example.club.repository;

import com.example.club.entity.ClubMember;
import com.example.club.entity.ClubMemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClubMemberRepositoryTest {

    @Autowired
    ClubMemberRepository clubMemberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> {
                    ClubMember clubMember = ClubMember.builder()
                            .email("user" + i + "@naver.com")
                            .name("사용자" + i)
                            .password(passwordEncoder.encode("1111"))
                            .fromSocial(false)
                            .build();

                    clubMember.addMemberRole(ClubMemberRole.USER);

                    if (i > 80)
                        clubMember.addMemberRole(ClubMemberRole.MANAGER);

                    if (i > 90)
                        clubMember.addMemberRole(ClubMemberRole.ADMIN);

                    clubMemberRepository.save(clubMember);
                });
    }

    @Test
    public void testRead() {
        Optional<ClubMember> byEmail = clubMemberRepository.findByEmail("user95@naver.com", false);

        byEmail.ifPresent(System.out::println);
    }
}