package com.example.mc3.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "circular_message")
public class CircularMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "sesion_id", updatable = false)
    private Long sessionId;

    @Column(name = "MC1_timestamp")
    private LocalDateTime mc1Timestamp;

    @Column(name = "MC2_timestamp")
    private LocalDateTime mc2Timestamp;

    @Column(name = "MC3_timestamp")
    private LocalDateTime mc3Timestamp;

    @Column(name = "end_timestamp")
    private LocalDateTime endTimestamp;
}
