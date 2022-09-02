package com.example.common.repository;

import com.example.common.model.CircularMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircularMessageRepository extends JpaRepository<CircularMessage, Long> {
}
