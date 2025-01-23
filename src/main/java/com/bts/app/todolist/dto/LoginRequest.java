package com.bts.app.todolist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String UserName;
    private String Password;
}
