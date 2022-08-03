package org.example.snippets.db;

import org.example.shared.io.db.Repository;
import org.example.snippets.model.Snippet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class SnippetsRepository implements Repository<Snippet> {

    @Override
    public Snippet save(Snippet snippet) {
        return null;
    }

    @Override
    public Optional<Snippet> findById(Long id) {
        return Optional.empty();
    }

    public List<Snippet> findByTitle(String title) {
        // Part 2b - Find (with native query) (please note the strange HQL syntax)
            TypedQuery<Snippet> snippetResult = entityManager.createQuery(
                    "select S from SNIPPET_DATA S where S.title " +
                            "= title",
                    Snippet.class);
            Snippet snippet = snippetResult.getResultList().get(0);
            System.out.println(snippet.getBody());

        ; }
}
