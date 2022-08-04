package org.example.snippets.service;

import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
import org.example.shared.io.validation.NonBlankInputValidationRule;
import org.example.snippets.model.Snippet;

import javax.persistence.*;
import java.util.List;

public interface SnippetService {
    void saveSnippet();
    void saveMoreSnippets();
    List<Snippet> findSnippetByTitle();
}
