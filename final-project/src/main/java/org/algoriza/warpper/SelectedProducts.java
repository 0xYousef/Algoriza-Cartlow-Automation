package org.algoriza.warpper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.algoriza.dto.ProductDTO;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class SelectedProducts {
    @JsonProperty("selected products")
    private Map<String, List<ProductDTO>> selectedProducts;
}
