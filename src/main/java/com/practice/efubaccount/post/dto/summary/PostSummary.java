package com.practice.efubaccount.post.dto.summary;

import com.practice.efubaccount.post.domain.Post;

public record PostSummary(
        Long postId,
        String nickName,
        String title,
        Long viewCount
) {
    public static PostSummary from(Post post) {
        return new PostSummary(
                post.getId(),
                post.getWriter().getNickname(),
                post.getTitle(),
                post.getViewCount()
        );
    }
}