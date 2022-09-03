package com.example.mc2.repository;

import com.example.mc2.model.CircularMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircularMessageRepository extends JpaRepository<CircularMessage, Long> {
}
