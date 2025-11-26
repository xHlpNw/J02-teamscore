package org.example.Task5;

import java.util.ArrayList;
import java.util.List;

public class InputHistory {
    List<String> values = new ArrayList<>();

    public void add(String value) throws AlreadyExistsException {
        if (values.contains(value)) {
            throw new AlreadyExistsException(value, values.indexOf(value) + 1);
        }
        values.add(value);
    }

    public int getSize() {
        return values.size();
    }
}
