package com.loaraid.raidscheduler.user;

import com.loaraid.raidscheduler.entity.UserEntity;
import com.loaraid.raidscheduler.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepo userRepo;

    @Test
    public void registerNewUser() {
        String userId = "test#1234";

        userService.registerUser(userId);

        verify(userRepo).save(any(UserEntity.class));

    }

    @Test
    public void findUserById() {
        Long userId = 1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);

        when(userRepo.findByIdAndDeletedAtIsNull(userId))
                .thenReturn(Optional.of(userEntity));

        UserEntity actualUser = userService.findByIdAndDeletedAtIsNull(userId);

        assertEquals(userId,actualUser.getUserId());
        verify(userRepo).findByIdAndDeletedAtIsNull(userId);
    }

    @Test
    public void whenFindUserByIdNotFound() {
        //given
        Long emptyUserId = 1L;

        when(userRepo.findByIdAndDeletedAtIsNull(emptyUserId))
                .thenReturn(Optional.empty());

        //when
        assertThrows(UserNotFoundException.class,() ->
                userService.findByIdAndDeletedAtIsNull(emptyUserId));

        //than
        verify(userRepo).findByIdAndDeletedAtIsNull(emptyUserId);
    }

    @Test
    public void updateUser(){
        //given
        Long userId = 1L;
        String discordId = "test#1234";
        String newDiscordId = "new#5678";

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setDiscordId(discordId);

        //when
        when(userRepo.findByIdAndDeletedAtIsNull(userId))
                .thenReturn(Optional.of(userEntity));

        userService.updateDiscordId(userId,newDiscordId);

        //than
        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepo).findByIdAndDeletedAtIsNull(userId);
        verify(userRepo).save(captor.capture());
        assertNotNull(captor.getValue().getUpdatedAt());
        assertEquals(newDiscordId,captor.getValue().getDiscordId());
    }

    @Test
    public void deleteUser(){
        //given
        Long userId = 1L;
        String discordId = "test#1234";
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setDiscordId(discordId);

        //when
        when(userRepo.findByIdAndDeletedAtIsNull(userId))
                .thenReturn(Optional.of(userEntity))
                .thenReturn(Optional.empty());

        userService.deleteUser(userId);

        //then
        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepo, times(1)).save(captor.capture());
        assertNotNull(captor.getValue().getDeletedAt());

        assertThrows(UserNotFoundException.class,() ->
                userService.findByIdAndDeletedAtIsNull(userId));

        verify(userRepo, times(2)).findByIdAndDeletedAtIsNull(userId);


    }
}