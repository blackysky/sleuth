package ru.incube.sleuth.enums;

import lombok.Getter;

@Getter
public enum DataModel {
    SIMPLE("Простая"),
    ADVANCED("Сложная");

    private final String description;

    DataModel(String description) {
        this.description = description;
    }
}
