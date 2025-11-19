package com.loaraid.raidscheduler.user;

import com.loaraid.raidscheduler.entity.UserEntity;
import com.loaraid.raidscheduler.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public void registerUser(String discordId) {
        UserEntity user = new UserEntity();
        user.setDiscordId(discordId);
        userRepo.save(user);
    }

//    public UserEntity findById(Long userId) throws UserNotFoundException {
//        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
//        if(user.getDeletedAt() != null){
//            throw new UserNotFoundException(userId);
//        } return user;
////        return userRepo.findByUserId(userId).orElseThrow(() -> new UserNotFoundException(userId));
//    }
    public UserEntity findByIdAndDeletedAtIsNull(Long userId){
        return userRepo.findByIdAndDeletedAtIsNull(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public void updateDiscordId(Long userId, String newDiscordId) {
        UserEntity user = findByIdAndDeletedAtIsNull(userId);
        user.setDiscordId(newDiscordId);
        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);

    }

    //soft delete
    @Transactional
    public void deleteUser(Long userId) {
        UserEntity user = findByIdAndDeletedAtIsNull(userId);
        user.setDeletedAt(LocalDateTime.now());
        userRepo.save(user);
    }
}
