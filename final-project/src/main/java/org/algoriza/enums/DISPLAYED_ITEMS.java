package org.algoriza.enums;

public enum DISPLAYED_ITEMS {
    MAX(50),
    MIN(25);

    private final int products;
    DISPLAYED_ITEMS(int products) {
        this.products = products;
    }
    public int switchTo() {
        return products;
    }
}
