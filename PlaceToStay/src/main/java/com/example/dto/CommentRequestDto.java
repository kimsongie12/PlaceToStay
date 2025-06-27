package com.example.dto;

import lombok.Data;

@Data
public class CommentRequestDto {
    private Integer postId;
    private Integer memberId;
    private String content;
}
