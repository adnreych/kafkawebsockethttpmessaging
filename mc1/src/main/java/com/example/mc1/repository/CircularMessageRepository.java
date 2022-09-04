package com.example.mc1.repository;

import com.example.mc1.model.CircularMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircularMessageRepository extends JpaRepository<CircularMessage, Long> {

    List<CircularMessage> findAllBySessionId(Long sessionId);
}
