package org.algoriza.warpper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.algoriza.dto.AccountDTO;

import java.util.List;


@Getter
@Setter
public class Accounts {
    @JsonProperty("accounts")
    private List<AccountDTO> accounts;
}
