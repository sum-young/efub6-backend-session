package com.practice.efubaccount.post.domain;

import com.practice.efubaccount.account.domain.Account;
import com.practice.efubaccount.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO) //자동으로 채워지는? AUTO
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)//다대일 연결 (게시물을 누가 썼는지)
    private Account writer;

    @Column (nullable = false)
    private String title;

    @Column (nullable = false)
    private String content;

    @Column (nullable = false)
    private Long viewCount;

    @Builder
    public Post(String title, Account writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.viewCount = 0L;
    }

    public void changeContent(String newContent) {
        this.content = newContent;
    }
}