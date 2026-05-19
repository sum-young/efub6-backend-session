package com.practice.efubaccount.follow.controlloer;

import com.practice.efubaccount.follow.dto.FollowListResponse;
import com.practice.efubaccount.follow.dto.FollowStatusResponse;
import com.practice.efubaccount.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;

    // TODO 1) 팔로우 추가

    // TODO 2) 팔로우 취소

    // TODO 3) 팔로우 여부 조회

    // TODO 4) 팔로우 목록 조회

}
