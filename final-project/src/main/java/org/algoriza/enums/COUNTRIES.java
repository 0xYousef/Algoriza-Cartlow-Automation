package org.algoriza.enums;

public enum COUNTRIES {
    UAE, KSA, INTL;
    public String current(){
        return name().toUpperCase();
    }
}
