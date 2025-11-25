package org.example.Task4;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationResult {
    private boolean isValid;
    private List<String> errorDescriptions;

    public ValidationResult() {
        isValid = true;
        errorDescriptions = new ArrayList<>();
    }

    public boolean addErrorDescription(String description) {
        if (errorDescriptions.contains(description)) return false;
        errorDescriptions.add(description);
        return true;
    }
}
