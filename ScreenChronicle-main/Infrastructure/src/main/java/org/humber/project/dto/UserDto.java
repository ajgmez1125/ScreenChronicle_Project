package org.humber.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
    private String roles = "User";
}
