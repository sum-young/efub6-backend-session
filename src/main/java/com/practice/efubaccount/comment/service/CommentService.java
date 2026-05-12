package com.practice.efubaccount.comment.service;

import com.practice.efubaccount.account.dto.response.AccountCommentResponse;
import com.practice.efubaccount.comment.domain.CommentLike;
import com.practice.efubaccount.comment.dto.request.CommentRequest;
import com.practice.efubaccount.comment.dto.request.CommentUpdateRequest;
import com.practice.efubaccount.comment.dto.response.CommentResponse;
import com.practice.efubaccount.comment.repository.CommentLikeRepository;
import com.practice.efubaccount.global.exception.CustomException;
import com.practice.efubaccount.global.exception.ErrorCode;
import com.practice.efubaccount.post.dto.response.PostCommentResponse;
import com.practice.efubaccount.account.domain.Account;
import com.practice.efubaccount.account.service.AccountService;
import com.practice.efubaccount.comment.domain.Comment;
import com.practice.efubaccount.comment.repository.CommentRepository;
import com.practice.efubaccount.post.domain.Post;
import com.practice.efubaccount.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final AccountService accountService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public Long createComment(Long postId, CommentRequest request) {
        Long accountId = request.getAccountId();
        Account writer = accountService.findByAccountId(accountId);
        Post post = postService.findByPostId(postId);
        Comment newComment = request.toEntity(writer, post);
        commentRepository.save(newComment);
        return newComment.getId();
    }

    @Transactional(readOnly = true)
    public PostCommentResponse getPostCommentList(Long postId) {
        List<Comment> commentList = commentRepository.findAllByPostIdOrderByCreatedAt(postId);
        return PostCommentResponse.of(postId,commentList);
    }

    @Transactional(readOnly=true)
    public AccountCommentResponse getAccountCommentList(Long accountId) {
        Account account = accountService.findByAccountId(accountId);
        List<Comment> commentList = commentRepository.findAllByWriterAccountIdOrderByCreatedAtDesc(accountId);
        return AccountCommentResponse.of(account, commentList);
    }

    @Transactional
    public CommentResponse updateComment(Long commentId, CommentUpdateRequest request, Long accountId) {
        Comment comment = findByCommentId(commentId);
        Account account = accountService.findByAccountId(accountId);
        authorizeCommentWriter(comment, account);

        comment.updateContent(request.getContent());

        return CommentResponse.of(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long accountId) {
        Comment comment = findByCommentId(commentId);
        Account account = accountService.findByAccountId(accountId);
        authorizeCommentWriter(comment, account);

        commentRepository.delete(comment);
    }

    @Transactional
    public void likeComment(Long commentId, Long accountId) {
        Comment comment = findByCommentId(commentId);
        Account account = accountService.findByAccountId(accountId);

        if(commentLikeRepository.existsByCommentAndAccount(comment, account)) {
            throw new CustomException(ErrorCode.LIKE_ALREADY_EXISTS);
        }

        CommentLike like = CommentLike.builder()
                .comment(comment)
                .account(account)
                .build();

        commentLikeRepository.save(like);
    }

    @Transactional
    public void unlikeComment(Long commentId, Long accountId) {
        Comment comment = findByCommentId(commentId);
        Account account = accountService.findByAccountId(accountId);
        CommentLike like = commentLikeRepository.findByCommentAndAccount(comment, account)
                .orElseThrow(() -> new CustomException(ErrorCode.LIKE_NOT_FOUND));

        commentLikeRepository.delete(like);
    }

    private Comment findByCommentId (Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }

    private void authorizeCommentWriter(Comment comment, Account account) {
        if(!comment.getWriter().equals(account)){
            throw new CustomException(ErrorCode.COMMENT_ACCOUNT_MISMATCH);
        }
    }
}




