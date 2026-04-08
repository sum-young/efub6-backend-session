package com.practice.efubaccount.post.service;

import com.practice.efubaccount.account.domain.Account;
import com.practice.efubaccount.account.service.AccountsService;
import com.practice.efubaccount.global.exception.CustomException;
import com.practice.efubaccount.global.exception.ErrorCode;
import com.practice.efubaccount.post.domain.Post;
import com.practice.efubaccount.post.dto.request.PostCreateRequest;
import com.practice.efubaccount.post.dto.request.PostUpdateRequest;
import com.practice.efubaccount.post.dto.response.PostListResponse;
import com.practice.efubaccount.post.dto.response.PostResponse;
import com.practice.efubaccount.post.dto.summary.PostSummary;
import com.practice.efubaccount.post.repository.PostRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final AccountsService accountsService;
    private final PostRepository postRepository;

    @Transactional
    public Long createPost(PostCreateRequest request) {
        Account writerAccount = accountsService.findByAccountId(request.getAccountId());

        Post newPost = request.toEntity(writerAccount);
        postRepository.save(newPost);

        return newPost.getId();
    }

    @Transactional (readOnly = true) //DB 수정하지 않으니까 readOnly=true 설정
    public PostListResponse getAllPosts() {
        List<PostSummary> postSummaries = postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PostSummary::from) //Post가 인자로 들어가서, PostSummary로 반환
                .toList();

        return new PostListResponse(postSummaries, postRepository.count());
    }

    @Transactional //조회수 업데이트가 필요 (readOnly = false 여야함)
    public PostResponse getPost(Long postId) {
        //조회수 증가 (JPQL 사용 -> 성능적으로 좋음)
        postRepository.increaseViewCount(postId);
        Post post = findByPostId((postId));

        return PostResponse.from(post);
    }

    @Transactional
    public void updatePostContent(Long postId, Long accountId, @Valid PostUpdateRequest request) {
        Post post = findByPostId(postId);
        Account account = accountsService.findByAccountId(accountId);

        authorizePostWriter(post,account);
        post.changeContent(request.content());
    }

    @Transactional
    public void deletePost(Long postId, Long accountId) {
        Post post = findByPostId(postId);
        Account account = accountsService.findByAccountId(accountId);

        authorizePostWriter(post, account);
        postRepository.delete(post);
    }

    public Post findByPostId(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }

    private void authorizePostWriter(Post post, Account account) {
        if(!post.getWriter().equals(account)) {
            throw new CustomException(ErrorCode.POST_ACCOUNT_MISMATCH);
        }
    }

}
