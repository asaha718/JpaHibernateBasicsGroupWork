package org.example.snippets;

import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
import org.example.shared.io.validation.NonBlankInputValidationRule;
import org.example.snippets.model.Snippet;

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

        userOutputService.print("WELCOME");

        try {
            getUserSnippetInput(userInputService, userOutputService, entityManager);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // close entity manager
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void getUserSnippetInput(UserInputService userInputService, UserOutputService userOutputService, EntityManager entityManager) throws Exception {
        String body = userInputService.getUserInput("PLEASE ENTER A CODE SNIPPET : ",
                new NonBlankInputValidationRule());
        String title = userInputService.getUserInput("WHAT IS A TITLE FOR THIS SNIPPET ? ",
                new NonBlankInputValidationRule());
        String tag = userInputService.getUserInput("WHAT IS A WORD TO REPRESENT THIS SNIPPET ? ",
                new NonBlankInputValidationRule());

        //create new instance of a snippet
        Snippet newSnippet = new Snippet();
        newSnippet.setBody(body);
        newSnippet.setTitle(title);
        newSnippet.setTag(tag);

        // access transaction object (copy paste this when needed)
        EntityTransaction transaction = entityManager.getTransaction();

        // create and use transactions (copy paste this when needed)
        transaction.begin();

        // Insert
        entityManager.persist(newSnippet); // IMPORTANT!

        transaction.commit();

        getMoreUserInput(userOutputService, userInputService, entityManager);
    }

    public static void getMoreUserInput(UserOutputService userOutputService, UserInputService userInputService, EntityManager entityManager) throws Exception {
        try (userInputService) {
            String response = userInputService.getUserInput("Do you want to create another snippet (1) or search for snippet by title (2)?",
                    new NonBlankInputValidationRule());
            if (response.equals("1")) {
                getUserSnippetInput(userInputService, userOutputService, entityManager);
            } else if (response.equals("2")) {
                searchUserSnippet(userInputService, userOutputService, entityManager);
            }
        }
    }

    public static void searchUserSnippet(UserInputService userInputService, UserOutputService userOutputService, EntityManager entityManager) throws Exception {
        try (userInputService) {
            String title = userInputService.getUserInput("Enter the title of the snippet you'd like to search: ",
                    new NonBlankInputValidationRule());

            Snippet foundSnippet = entityManager.find(Snippet.class, );
            System.out.println(foundSnippet);
        }
    }
}