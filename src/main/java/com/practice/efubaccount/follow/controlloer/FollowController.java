package com.practice.efubaccount.follow.controlloer;

import com.practice.efubaccount.follow.dto.FollowListResponse;
import com.practice.efubaccount.follow.dto.FollowStatusResponse;
import com.practice.efubaccount.follow.enums.FollowStatus;
import com.practice.efubaccount.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;

    // TODO 1) 팔로우 추가
    @PostMapping("/{targetId}")
    public ResponseEntity<FollowStatusResponse> follow(@RequestHeader("Auth-id") Long requesterId,
                                               @PathVariable("targetId") Long targetId) {

        FollowStatusResponse response = followService.follow(requesterId, targetId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // TODO 2) 팔로우 취소
    @DeleteMapping("/{targetId}")
    public ResponseEntity<FollowStatusResponse> unfollow(@RequestHeader("Auth-id") Long requesterId,
                                                       @PathVariable("targetId") Long targetId) {

        FollowStatusResponse response = followService.unfollow(requesterId, targetId);
        return ResponseEntity.ok(response);
    }
    // TODO 3) 팔로우 여부 조회
    @GetMapping("/status")
    public ResponseEntity<FollowStatusResponse> getFollowStatus(@RequestHeader("Auth-id") Long requesterId,
                                                                @RequestParam("email") String targetEmail) {

        FollowStatusResponse response = followService.checkFollowStatusByEmail(requesterId, targetEmail);

        return ResponseEntity.ok(response);
    }

    // TODO 4) 팔로우 목록 조회
    @GetMapping
    public ResponseEntity<FollowListResponse> getFollowList (@RequestHeader("Auth-id") Long requesterId) {
        FollowListResponse response = followService.getFollowList(requesterId);

        return ResponseEntity.ok(response);
    }
}
