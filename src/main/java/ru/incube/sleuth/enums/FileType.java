package ru.incube.sleuth.enums;

import lombok.Getter;

@Getter
public enum FileType {
    RECIPES("Рецепты"),
    LOOT_TABLES("Таблицы дропа"),
    ADVANCEMENTS("Достижения"),
    STRUCTURES("Структуры"),
    TAGS("Теги"),
    WORLD_GEN("Генерация мира");

    private final String description;

    FileType(String description) {
        this.description = description;
    }
}
