package com.fdorval.spoilgot.dao.model;

public enum Season {
    S1(1), S2(2), S3(3), S4(4), S5(5), S6(6), S7(7), S8(8);

    private Integer intValue;

    private Season(Integer intValue) {
        this.intValue = intValue;
    }


    public Integer getValue() {
        return this.intValue;
    }

    public static Season fromInt(Integer i) {
        return Season.valueOf("S" + i);
    }

    public static Season fromString(String i) {
        return Season.valueOf("S" + i);
    }


}
