package com.ahmed.secoopecproductnetwork.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Builder
public class Token {
    @Id
    @GeneratedValue
    private Integer id;
    private String token;

    private LocalDateTime createdat;
    private LocalDateTime expiresat ;

    private LocalDateTime validateat;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;



}
