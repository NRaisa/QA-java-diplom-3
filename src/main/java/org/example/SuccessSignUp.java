package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessSignUp {
        private boolean success;
        private String accessToken;
        private String refreshToken;
        private UserInfo user;
   }
