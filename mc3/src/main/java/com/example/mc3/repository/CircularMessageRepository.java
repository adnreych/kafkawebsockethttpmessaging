package com.example.mc3.repository;

import com.example.mc3.model.CircularMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircularMessageRepository extends JpaRepository<CircularMessage, Long> {
}
