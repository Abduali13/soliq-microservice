package com.company.userservice.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {

    private int code;
    private boolean success;
    private String message;
    private T content;

}
