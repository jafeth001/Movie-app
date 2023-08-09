package com.backendengineer.springfordemo.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterrequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
