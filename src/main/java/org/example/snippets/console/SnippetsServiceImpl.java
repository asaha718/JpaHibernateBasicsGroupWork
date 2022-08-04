package org.example.snippets.console;

import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.validation.NonBlankInputValidationRule;
import org.example.snippets.db.SnippetsRepository;
import org.example.snippets.model.Snippet;
import org.example.snippets.service.SnippetService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SnippetsServiceImpl implements SnippetService {
    private UserOutputService userOutputService;
    private UserInputService userInputService;
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    private SnippetsRepository snippetsRepository;

    public SnippetsServiceImpl(
            UserOutputService userOutputService, UserInputService userInputService,
            EntityManager entityManager, EntityManagerFactory entityManagerFactory,
            SnippetsRepository snippetsRepository)
    {
        this.userOutputService = userOutputService;
        this.userInputService = userInputService;
        this.entityManager = entityManager;
        this.entityManagerFactory = entityManagerFactory;
        this.snippetsRepository = new SnippetsRepository(entityManager);
    }

    @Override
    public void saveSnippet() {
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

        snippetsRepository.save(newSnippet);
        saveMoreSnippets();
    }

    @Override
    public void saveMoreSnippets() {
            String response = userInputService.getUserInput(
                    "Do you want to create another snippet (1) or search for snippet by title (2)?",
                    new NonBlankInputValidationRule());
            if (response.equals("1")) {
                saveSnippet();
            } else if (response.equals("2")) {
                findSnippetByTitle();
            }
        }

    @Override
    public void findSnippetByTitle() {
        String title = userInputService.getUserInput("What is the title of the snippet you are searching ? ",
                new NonBlankInputValidationRule());
        snippetsRepository.findByTitle(title);
    }
}
