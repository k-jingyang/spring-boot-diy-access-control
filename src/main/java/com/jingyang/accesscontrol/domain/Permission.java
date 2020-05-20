package com.jingyang.accesscontrol.domain;

public enum Permission {
    READ(4), UPDATE(2), DELETE(1);

    private int bitMask;

    Permission(int bitMask) {
        this.bitMask = bitMask;
    }

    public boolean isWithin(int givenBitMask) {
        if ((this.bitMask & givenBitMask) == this.bitMask) {
            return true;
        }
        return false;
    }
}
