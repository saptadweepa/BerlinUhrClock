package com.clock;

public enum Color {
    RED("R"),
    YELLOW("Y"),
    OFF("O");

    public final String colorValue;

    Color(String colorValue){
        this.colorValue = colorValue;
    }
}
