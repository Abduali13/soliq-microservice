package com.company.userservice.controller;

import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.UserRequest;
import com.company.userservice.dto.UserResponse;
import com.company.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.userservice.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto<UserResponse>> create(@RequestBody UserRequest dto){
        return convertStatusCodeByData(this.userService.create(dto));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<UserResponse>> get(@RequestParam(value = "id") Integer userId){
        return convertStatusCodeByData(this.userService.get(userId));
    }

    @PutMapping
    public ResponseEntity<ResponseDto<UserResponse>> update(@RequestParam(value = "id") Integer userId, @RequestBody UserRequest dto){
        return convertStatusCodeByData(this.userService.update(userId, dto));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<UserResponse>> delete(@RequestParam(value = "id") Integer userId){
        return convertStatusCodeByData(this.userService.delete(userId));
    }


}
