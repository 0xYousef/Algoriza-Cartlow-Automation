package org.algoriza.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    @JsonAlias({"email", "mobile"})
    private String email;
    private String password;
}
