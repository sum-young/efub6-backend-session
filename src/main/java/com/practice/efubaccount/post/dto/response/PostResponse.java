package com.practice.efubaccount.post.dto.response;

import com.practice.efubaccount.post.domain.Post;

import java.time.LocalDateTime;

public record PostResponse(
        Long postId,
        Long accountId,
        String nickName,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        Long viewCount
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getWriter().getAccountId(),
                post.getWriter().getNickname(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getModifiedAt(),
                post.getViewCount()
        );
    }
}