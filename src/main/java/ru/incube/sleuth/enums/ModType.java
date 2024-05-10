package ru.incube.sleuth.enums;

import lombok.Getter;

@Getter
public enum ModType {
    SERVER("Серверный"),
    CLIENT("Клиентский"),
    BOTH("Оба"),
    UNKNOWN("Неизвестный");

    private final String description;

    ModType(String description) {
        this.description = description;
    }
}