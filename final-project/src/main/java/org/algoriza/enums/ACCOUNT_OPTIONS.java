package org.algoriza.enums;

public enum ACCOUNT_OPTIONS {
    SIGN_IN("Sign In"),
    CREATE_NEW_ACCOUNT("Create new account"),
    MY_ORDERS("My Orders"),
    MY_WISHLIST("My Wishlist"),
    PROFILE_INFO("Profile info"),
    LOGOUT("Logout");

    private final String displayName;

    ACCOUNT_OPTIONS(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
