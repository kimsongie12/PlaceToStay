package com.example.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TargetType targetType; // POST, COMMENT

    @Column(nullable = false)
    private Integer targetId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReactionType reactionType; // LIKE, DISLIKE

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // equals, hashCode는 member, targetType, targetId 기준으로 오버라이드해도 좋음
}
