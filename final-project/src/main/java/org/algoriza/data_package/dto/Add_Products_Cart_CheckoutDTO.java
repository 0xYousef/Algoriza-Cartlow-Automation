package org.algoriza.data_package.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.algoriza.dto.AccountDTO;
import org.algoriza.dto.ProductDTO;



import java.util.List;

@Builder
@Setter
@Getter
public class Add_Products_Cart_CheckoutDTO {
    private AccountDTO account;
    private List<ProductDTO> products;
}
