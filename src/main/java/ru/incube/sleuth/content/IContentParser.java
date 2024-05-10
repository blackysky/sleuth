package ru.incube.sleuth.content;

import java.util.List;

public interface IContentParser<T> {
    T parseContent(List<String> content);
}
