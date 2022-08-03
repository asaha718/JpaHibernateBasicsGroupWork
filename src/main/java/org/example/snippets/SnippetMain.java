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
    public static void main(String[] args) throws Exception {
//        UserOutputService userOutputService = new ConsoleUserOutputServiceImpl();

        getUserSnippetInput();
//                String response = userInputService.getUserInput("Do you want to create another snippet (1) or search for snippet by title (2)?",
//                        new NonBlankInputValidationRule());
//
//                if(response.equals("1"))

    }

    public static void getUserSnippetInput() throws Exception {
        UserOutputService userOutputService = new ConsoleUserOutputServiceImpl();

        Boolean isSnippetLooping = true;
        while (isSnippetLooping) {
            try (UserInputService userInputService = new ConsoleUserInputServiceImpl(userOutputService)) {
                userOutputService.print("WELCOME");
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

                // create EntityManager (copy paste this when needed)
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
                EntityManager entityManager = entityManagerFactory.createEntityManager();

                // access transaction object (copy paste this when needed)
                EntityTransaction transaction = entityManager.getTransaction();

                // create and use transactions (copy paste this when needed)
                transaction.begin();

                // Insert
                entityManager.persist(newSnippet); // IMPORTANT!

                transaction.commit();
            }
        }
    }
}