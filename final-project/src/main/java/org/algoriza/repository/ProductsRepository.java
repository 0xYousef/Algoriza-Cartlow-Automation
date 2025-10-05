package org.algoriza.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.algoriza.dto.ProductDTO;
import org.algoriza.utils.JsonParser;
import org.algoriza.utils.RandomUtil;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class ProductsRepository {
    private static final String FILE_PATH = "data/selected_products.json";
    private List<ProductDTO> products;

    public ProductsRepository(String region) {
        loadProducts(region);
    }


    private void loadProducts(String region) {
        try {
            // Read root JSON node from resources
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(JsonParser.class.getClassLoader().getResourceAsStream(FILE_PATH));

            JsonNode regionNode = root.path("selected products").path(region);
            products = Arrays.asList(mapper.treeToValue(regionNode, ProductDTO[].class));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductDTO> getAll() {
        return products;
    }

    public ProductDTO getRandom() {
        return RandomUtil.getRandomElement(products);
    }

    public ProductDTO getByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .findFirst()
                .orElse(null);
    }
}
