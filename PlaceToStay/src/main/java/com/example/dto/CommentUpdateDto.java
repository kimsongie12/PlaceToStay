package com.example.dto;

import lombok.Data;

@Data
public class CommentUpdateDto {
    private Integer memberId;
    private String content;
}