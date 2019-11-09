package com.gxb.threadcoreknowledge.juc.enums;

public enum CountryEnum {

    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"秦"),
    FOUR(4,"燕"),
    FIVE(5,"赵"),
    SIX(6,"魏"),
    SEVEN(7,"韩"),

    ;

    private Integer retCode;
    private String retMessage;

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if (value.retCode == index) {
                return value;
            }
        }
        return null;
    }
}
