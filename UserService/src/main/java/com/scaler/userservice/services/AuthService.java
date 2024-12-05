package com.scaler.userservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.userservice.dtos.CustomSpringUser;
import com.scaler.userservice.entities.Session;
import com.scaler.userservice.entities.SessionStatus;
import com.scaler.userservice.entities.User;
import com.scaler.userservice.repositories.SessionRepository;
import com.scaler.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ObjectMapper objectMapper;


    public User signUp(String email, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public Session login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid userOptional or password");
        }

        User user = userOptional.get();

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid userOptional or password");
        }

        Session session = new Session();
        session.setUser(user);
        session.setToken(java.util.UUID.randomUUID().toString());
        Date expiredDate = getExpiredDate();
        session.setExpiringAt(expiredDate);

        return sessionRepository.save(session);
    }

    // expiration date will be 30 days after today.
    private Date getExpiredDate() {

        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(new Date());


        //add(Calendar.DAY_OF_MONTH, -5).
        calendarDate.add(Calendar.DAY_OF_MONTH, 30);

        Date expiredDate = calendarDate.getTime();
        return expiredDate;

    }

    public void logout(String token) {
        Optional<Session> tokenOptional = sessionRepository.findFirstByTokenAndSessionStatus(token, SessionStatus.ENDED);

        if (tokenOptional.isEmpty()) {
            throw new RuntimeException("Token is invalid ");
        }

        Session session = tokenOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);

        sessionRepository.save(session);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomSpringUser(user.getEmail(), user.getPassword());
    }
}
