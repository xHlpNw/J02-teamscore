package org.example.Task5;

import lombok.Getter;

@Getter
public class AlreadyExistsException extends Exception {
    private final String value;
    private final int position;

    public AlreadyExistsException(String value, int position) {
        super(String.format(
                "Значение \"%s\" уже было введено ранее под номером %d.",
                value, position));
        this.value = value;
        this.position = position;
    }
}
