package org.example.snippets.db;

import org.example.shared.io.db.Repository;
import org.example.snippets.model.Snippet;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class SnippetsRepository implements Repository<Snippet> {
    private EntityManager entityManager;
    public SnippetsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<Snippet> findByTitle(String title) {
        TypedQuery<Snippet> snippetResult =
                entityManager.createQuery(
                                "SELECT s FROM Snippet s WHERE s.title = :title"
                                , Snippet.class)
                        .setParameter("title", title);
        List<Snippet> snippetList = snippetResult.getResultList();
        return snippetList;
    }

    @Override
    public Snippet save(Snippet snippet) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(snippet);
            entityManager.getTransaction().commit();
            return snippet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Snippet> findById(Long id) {
        return Optional.empty();
    }
}
