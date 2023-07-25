package org.platformlib.platformbackend.controller;


import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.platformlib.platformbackend.entity.Member;
import org.platformlib.platformbackend.repository.MemberRepository;
import org.platformlib.platformbackend.service.JwtService;
import org.platformlib.platformbackend.service.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;


@RestController
public class AccountController {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/api/account/signup")
    public ResponseEntity signup(@RequestBody Map<String, String> params, HttpServletResponse res){
        String email = params.get("email");
        String password = params.get("password");

        // Check if the account already exists with the given email
        Member existingMember = memberRepository.findByEmail(email);
        if (existingMember != null) {
            // Account with the provided email already exists, throw an exception
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account with the provided email already exists.");
        }

        // Create a new Member entity
        Member newMember = new Member();
        newMember.setEmail(email);
        newMember.setPassword(password);

        try {
            // Save the new member to the database
            Member savedMember = memberRepository.save(newMember);
            // Optionally, you can return the ID of the saved member in the response
            int Id = savedMember.getId();
            return new ResponseEntity<>(Id, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            // If there is an integrity violation, it means that the member could not be saved due to a constraint violation.
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save the new member.");
        }
    }


    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params, HttpServletResponse res) {
        Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));
        if (member != null) {
            int id = member.getId();
            // jwtService to generate a JSON Web Token (JWT) containing the user's ID. The getToken method takes the key "id" and the user's ID as parameters to create the token.
            String token = jwtService.getToken("id", id);

            Cookie cookie = new Cookie("token", token);
            // "setHttpOnly" is used to set the HttpOnly attribute on the "token" cookie, which holds a JWT (JSON Web Token) for user authentication
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            res.addCookie(cookie);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/account/logout")
    public ResponseEntity logout(HttpServletResponse res){
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/account/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
        Claims claims = jwtService.getClaims(token);

        if (claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
