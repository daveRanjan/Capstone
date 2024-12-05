package com.scaler.userservice.repositories;

import com.scaler.userservice.entities.Session;
import com.scaler.userservice.entities.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findFirstByTokenAndSessionStatus(String token, SessionStatus sessionStatus);
}
