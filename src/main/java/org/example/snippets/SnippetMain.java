package org.example.snippets;

import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
import org.example.shared.io.validation.NonBlankInputValidationRule;
import org.example.snippets.console.SnippetsServiceImpl;
import org.example.snippets.db.SnippetsRepository;
import org.example.snippets.model.Snippet;
import org.example.snippets.service.SnippetService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SnippetMain {

    public static void main(String[] args) {

        UserOutputService userOutputService = new ConsoleUserOutputServiceImpl();
        UserInputService userInputService = new ConsoleUserInputServiceImpl(userOutputService);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        SnippetsRepository repository = new SnippetsRepository(entityManager);

        SnippetService snippetService = new SnippetsServiceImpl(userOutputService, userInputService, entityManager, entityManagerFactory, repository);
        userOutputService.print("WELCOME");

        try {
            snippetService.saveSnippet();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}