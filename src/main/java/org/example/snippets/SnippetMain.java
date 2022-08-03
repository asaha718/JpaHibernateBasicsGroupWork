package org.example.snippets;

import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
import org.example.shared.io.validation.NonBlankInputValidationRule;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SnippetMain {
    public static void main(String[] args) throws Exception {
        UserOutputService userOutputService = new ConsoleUserOutputServiceImpl();
        try (UserInputService userInputService = new ConsoleUserInputServiceImpl(userOutputService)) {
            userOutputService.print("WELCOME");
            String response = userInputService.getUserInput("Insert snippet",
                    new NonBlankInputValidationRule());

            // create EntityManager (copy paste this when needed)
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            // access transaction object (copy paste this when needed)
            EntityTransaction transaction = entityManager.getTransaction();

            // create and use transactions (copy paste this when needed)
            transaction.begin();

            // Insert
//            entityManager.persist(); // IMPORTANT!
        }
    }
}
