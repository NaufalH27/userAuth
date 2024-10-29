package io.userauth.dto.auth;

import jakarta.servlet.http.Cookie;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenDTO {
   private Cookie accessToken;
   private Cookie refreshToken;  
}
