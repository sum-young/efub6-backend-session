package com.practice.efubaccount.post.dto.response;

import com.practice.efubaccount.post.dto.summary.PostSummary;

import java.util.List;

public record PostListResponse(
        List<PostSummary> posts,
        Long totalPosts
) {}