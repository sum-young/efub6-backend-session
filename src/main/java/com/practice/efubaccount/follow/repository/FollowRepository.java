package com.practice.efubaccount.follow.repository;

import com.practice.efubaccount.account.domain.Account;
import com.practice.efubaccount.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {


}
