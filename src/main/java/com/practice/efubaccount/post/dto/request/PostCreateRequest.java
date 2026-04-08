package com.practice.efubaccount.post.dto.request;

import com.practice.efubaccount.account.domain.Account;
import com.practice.efubaccount.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostCreateRequest{
    @NotNull
    private Long accountId;

    @NotBlank(message = "제목을 입력해야 합니다.") //에러나면 띄울 메시지 옆에.
    private String title;

    @Size(min=5, max=500, message = "내용은 5자이상 500자이하로 입력해야합니다.")
    private String content;

    public Post toEntity(Account account) {
        return Post.builder()
                .title(title)
                .content(content)
                .writer(account)
                .build();
    }
}