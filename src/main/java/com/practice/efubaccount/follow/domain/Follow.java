package com.practice.efubaccount.follow.domain;

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
public class Follow extends BaseEntity {

    @Id
    @Column(name = "follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Account <-> Account 사이의 다대다를 중간에 follow로 빼낸 느낌 Account <-> Follow <-> Account
    //연결 테이블

    //팔로우하는 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false, updatable = false)
    private Account follower;

    //팔로우 당하는 사람
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "followee_id", nullable = false, updatable = false)
    private Account followee;

    @Builder
    public Follow(Account follower, Account followee) {
        this.followee = followee;
        this.follower = follower;
    }
}
