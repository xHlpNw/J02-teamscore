package org.example.Task4;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Password validation result
 */
@Getter
@Setter
public class ValidationResult {
    // Логическое значение соответствия пароля требованиям
    private boolean isValid;

    // Список строк с описанием ошибок
    private List<String> errorDescriptions;

    public ValidationResult() {
        isValid = true;
        errorDescriptions = new ArrayList<>();
    }

    /**
     * adds the error description string to the list if it was not found
     * in it earlier, and returns the boolean value of the addition.
     * @param description
     * @return true
     */
    public boolean addErrorDescription(String description) {
        if (errorDescriptions.contains(description)) return false;
        errorDescriptions.add(description);
        return true;
    }
}
