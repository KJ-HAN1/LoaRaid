package com.loaraid.raidscheduler.user;

import com.loaraid.raidscheduler.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(Long userId);
    Optional<UserEntity> findByIdAndDeletedAtIsNull(Long userId);

}
