package com.company.userservice.service.impl;
import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.UserRequest;
import com.company.userservice.dto.UserResponse;
import com.company.userservice.entity.User;
import com.company.userservice.exception.ResourceNotFoundException;
import com.company.userservice.repository.UserRepository;
import com.company.userservice.service.UserService;
import com.company.userservice.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;


    @Override
    public ResponseDto<UserResponse> create(UserRequest dto) {
        try {
            User entity = this.userMapper.toEntity(dto);
            return ResponseDto.<UserResponse>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.userMapper.toDto(
                                    this.userRepository.save(entity)
                            )
                    )
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public ResponseDto<UserResponse> get(Integer userId) {
        try {
            User user = this.userRepository.findUserByUserIdAndDeletedAtIsNull(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

            return ResponseDto.<UserResponse>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.userMapper.toDtoWithCard(user)
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserResponse>builder()
                    .code(-3)
                    .message(e.getMessage())
                    .build();
        }

    }

    @Override
    public ResponseDto<UserResponse> update(Integer userId, UserRequest dto) {
        try {
            User user = this.userRepository.findUserByUserIdAndDeletedAtIsNull(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

            return ResponseDto.<UserResponse>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.userMapper.toDtoWithCard(
                                    this.userRepository.save(
                                            this.userMapper.update(user, dto)
                                    )
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserResponse>builder()
                    .code(-3)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<UserResponse> delete(Integer userId) {
        try {
            User user = this.userRepository.findUserByUserIdAndDeletedAtIsNull(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
            user.setDeletedAt(LocalDateTime.now());
            return ResponseDto.<UserResponse>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.userMapper.toDtoWithCard(
                                    this.userRepository.save(user)
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserResponse>builder()
                    .code(-3)
                    .message(e.getMessage())
                    .build();
        }
    }
}
