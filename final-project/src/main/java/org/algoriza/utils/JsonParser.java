package org.algoriza.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T readFromFile(String filePath, Class<T> clazz) throws IOException {
        return objectMapper.readValue(new File(filePath), clazz);
    }

    public static <T> T readFromResources(String resourcePath, Class<T> clazz) throws IOException {
        InputStream inputStream = JsonParser.class.getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }
        return objectMapper.readValue(inputStream, clazz);
    }

    public static <T> List<T> readListFromFile(String filePath, Class<T> clazz) throws IOException {
        return objectMapper.readValue(new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public static JsonNode readNode(String filePath, String... path) throws IOException {
        JsonNode node = objectMapper.readTree(new File(filePath));
        for (String p : path) {
            node = node.path(p);
        }
        return node;
    }

    public static void writeToFile(String filePath, Object obj) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), obj);
    }
}