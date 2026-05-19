package com.practice.efubaccount.follow.service;

import com.practice.efubaccount.account.domain.Account;
import com.practice.efubaccount.account.service.AccountService;
import com.practice.efubaccount.follow.domain.Follow;
import com.practice.efubaccount.follow.dto.FollowListResponse;
import com.practice.efubaccount.follow.dto.FollowStatusResponse;
import com.practice.efubaccount.follow.enums.FollowStatus;
import com.practice.efubaccount.follow.repository.FollowRepository;
import com.practice.efubaccount.global.exception.CustomException;
import com.practice.efubaccount.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FollowService {

    private final AccountService accountService;
    private final FollowRepository followRepository;

    // TODO 1) 팔로우 추가

    // TODO 2) 팔로우 취소

    // TODO 3) 팔로우 여부 조회

    // TODO 4) 팔로우 목록 조회


}
