package org.example.Task4;

/** Проверка пароля на сложность.
 * Пароль должен отвечать следующим требованиям:
 * - не менее 8 символов в длину
 * - содержит строчные, заглавные буквы и цифры
 * - не должен совпадать с имененем пользователя
 * - не должен содержать пробельных символов, табуляции и кавычек
 */
public class PasswordValidator {
    /**
     * Проверка валидности пароля
     * @param password пароль
     * @param userName имя пользователя
     * @return возвращает true, если пароль отвечает всем требованиям
     */
    public static boolean isValidPassword(String password, String userName) {
        if (password.length() < 8) {
            return false;
        }
        if (!hasDigits(password)) {
            return false;
        }
        if (!hasLowercase(password)) {
            return false;
        }
        if (!hasUppercase(password)) {
            return false;
        }
        if (password == userName) {
            return false;
        }
        if (hasSpacesOrQuotes(password)) {
            return false;
        }
        return true;
    }

    private static boolean hasDigits(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isDigit(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasLowercase(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isLowerCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasUppercase(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isUpperCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasSpacesOrQuotes(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isSpaceChar(symbol)
                    || symbol == '\t' || symbol == '"') {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the password for compliance with the requirements
     * @param password
     * @param userName
     * @return ValidationResult with a boolean compliance value and a list of error descriptions.
     */
    public ValidationResult validatePassword(String password, String userName) {
        ValidationResult result = new ValidationResult();
        if (password.length() < 8) {
            result.setValid(false);
            result.addErrorDescription(
                    "The password must not be shorter than 8 characters.");
        }
        if (!hasDigits(password)) {
            result.setValid(false);
            result.addErrorDescription(
                    "The password must contain at least one digit.");
        }
        if (!hasLowercase(password)) {
            result.setValid(false);
            result.addErrorDescription(
                    "The password must contain both upper and lower case characters.");
        }
        if (!hasUppercase(password)) {
            result.setValid(false);
            result.addErrorDescription(
                    "The password must contain both upper and lower case characters.");
        }
        if (password.equals(userName)) {
            result.setValid(false);
            result.addErrorDescription(
                    "The password must not match the username.");
        }
        if (hasSpacesOrQuotes(password)) {
            result.setValid(false);
            result.addErrorDescription(
                    "The password must not contain spaces, tabs, or quotation marks.");
        }
        return result;
    }
}
