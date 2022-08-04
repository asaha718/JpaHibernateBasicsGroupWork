package org.example.snippets.service;

import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
import org.example.shared.io.validation.NonBlankInputValidationRule;

import javax.persistence.*;

public interface SnippetService {
    void saveSnippet();
    void saveMoreSnippets();
    void findSnippetByTitle();
}
