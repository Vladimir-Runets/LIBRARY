package by.fpmibsu.LIBRARY.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    Integer user_id;
    String login;
    String password;
    String mail;
}
