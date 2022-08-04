package org.example.shared.io.db;

import org.example.snippets.model.Snippet;

import java.util.Optional;

public interface Repository <T> {
    T save(T t);

    Optional<T> findById(Long id);
}
