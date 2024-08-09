package com.pk48.BourseIntercommunale.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthentificationRequest {
    private String email;
    private String password;
    private String firstName;
}
