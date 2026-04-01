package com.practice.efubaccount.repository;

import com.practice.efubaccount.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //이메일 중복 검사를 위한 쿼리
    boolean existsByEmail(String email);

    //회원 아이디로 실제 존재하는지 검색하는 쿼리
    Optional<Account> findByAccountId(Long accountId);


}
