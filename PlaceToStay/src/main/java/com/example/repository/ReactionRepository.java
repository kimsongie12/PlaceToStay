package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Member;
import com.example.entity.Reaction;
import com.example.entity.ReactionType;
import com.example.entity.TargetType;

public interface ReactionRepository extends JpaRepository<Reaction, Integer>{
	Optional<Reaction> findByMemberAndTargetTypeAndTargetId(Member member, TargetType targetType, Integer targetId);

    Long countByTargetTypeAndTargetIdAndReactionType(TargetType targetType, Integer targetId, ReactionType reactionType);

    void deleteByMemberAndTargetTypeAndTargetId(Member member, TargetType targetType, Integer targetId);
}