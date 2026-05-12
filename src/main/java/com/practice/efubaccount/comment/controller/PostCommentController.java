package com.practice.efubaccount.comment.controller;

import com.practice.efubaccount.comment.domain.Comment;
import com.practice.efubaccount.comment.dto.request.CommentRequest;
import com.practice.efubaccount.comment.dto.request.CommentUpdateRequest;
import com.practice.efubaccount.comment.dto.response.CommentResponse;
import com.practice.efubaccount.comment.service.CommentService;
import com.practice.efubaccount.post.dto.response.PostCommentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class PostCommentController {

    private final CommentService commentService;


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Void> createComment(@PathVariable("postId") Long postId,
                                              @RequestBody CommentRequest request) {
        Long id = commentService.createComment(postId,request);
        return ResponseEntity.created(URI.create("/posts/"+postId+"/comments/"+id)).build();
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<PostCommentResponse> getComments(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(commentService.getPostCommentList(postId));
    }

//    // 댓글 수정
//    @PatchMapping
//    public ResponseEntity<CommentResponse> updateComment() {
//    }
//
//    // 댓글 삭제
//    @DeleteMapping
//    public ResponseEntity<Void> deleteComment() {
//    }
//
//    // 댓글 좋아요
//    @PostMapping
//    public ResponseEntity<String> likeComment() {
//    }
//
//    // 댓글 좋아요 취소
//    @DeleteMapping
//    public ResponseEntity<String> unlikeComment() {
//    }
}