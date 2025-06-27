package com.example.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Member;
import com.example.entity.Reaction;
import com.example.entity.ReactionType;
import com.example.entity.TargetType;
import com.example.repository.ReactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReactionService {
	
	private final ReactionRepository reactionRepository;
	
	public void react(Member member, TargetType targetType, Integer targetId, ReactionType reactionType) {
        Optional<Reaction> existingReaction = reactionRepository
            .findByMemberAndTargetTypeAndTargetId(member, targetType, targetId);

        if (existingReaction.isPresent()) {
            Reaction reaction = existingReaction.get();

            if (reaction.getReactionType() == reactionType) {
                // 같은 반응이면 취소
                reactionRepository.delete(reaction);
            } else {
                // 다른 반응이면 업데이트
                reaction.setReactionType(reactionType);
                reaction.setCreatedAt(LocalDateTime.now());
                reactionRepository.save(reaction);
            }

        } else {
            // 새 반응 등록
            Reaction reaction = new Reaction();
            reaction.setMember(member);
            reaction.setTargetType(targetType);
            reaction.setTargetId(targetId);
            reaction.setReactionType(reactionType);
            reaction.setCreatedAt(LocalDateTime.now());

            reactionRepository.save(reaction);
        }
    }

    public long getLikeCount(TargetType type, Integer targetId) {
        return reactionRepository.countByTargetTypeAndTargetIdAndReactionType(type, targetId, ReactionType.LIKE);
    }

    public long getDislikeCount(TargetType type, Integer targetId) {
        return reactionRepository.countByTargetTypeAndTargetIdAndReactionType(type, targetId, ReactionType.DISLIKE);
    }
}