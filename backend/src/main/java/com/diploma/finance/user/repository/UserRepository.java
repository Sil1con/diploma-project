package com.diploma.finance.user.repository;

import com.diploma.finance.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);

    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
