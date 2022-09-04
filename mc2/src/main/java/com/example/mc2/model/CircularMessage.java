package com.example.mc2.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "circular_message")
public class CircularMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "session_id", updatable = false)
    private Long sessionId;

    @Column(name = "MC1_timestamp")
    private LocalDateTime mc1Timestamp;

    @Column(name = "MC2_timestamp")
    private LocalDateTime mc2Timestamp;

    @Column(name = "MC3_timestamp")
    private LocalDateTime mc3Timestamp;

    @Column(name = "end_timestamp")
    private LocalDateTime endTimestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CircularMessage that = (CircularMessage) o;
        return Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(mc1Timestamp, that.mc1Timestamp) &&
                Objects.equals(mc2Timestamp, that.mc2Timestamp) &&
                Objects.equals(mc3Timestamp, that.mc3Timestamp) &&
                Objects.equals(endTimestamp, that.endTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, mc1Timestamp, mc2Timestamp, mc3Timestamp, endTimestamp);
    }

    @Override
    public String toString() {
        return "CircularMessage{" +
                "id=" + id +
                ", sessionId=" + sessionId +
                ", mc1Timestamp=" + mc1Timestamp +
                ", mc2Timestamp=" + mc2Timestamp +
                ", mc3Timestamp=" + mc3Timestamp +
                ", endTimestamp=" + endTimestamp +
                '}';
    }
}
