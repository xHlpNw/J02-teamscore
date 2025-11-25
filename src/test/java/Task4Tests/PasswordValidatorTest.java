package Task4Tests;

import org.example.Task4.PasswordValidator;
import org.example.Task4.ValidationResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
    private void testPassword(String password, String username, boolean result) {
        // Act
        boolean validationResult = PasswordValidator.isValidPassword(password, username);

        // Assert
        assertEquals(result, validationResult);
    }

    @ParameterizedTest
    @DisplayName("Valid password is accepted")
    @ValueSource(strings =
            {"Test6789", "1R2g3T4k5Y", "0123456789aA", "_TestUser74"})
    public void validPassword(String password) {
        testPassword(password, "TestUser", true);
    }

    @ParameterizedTest
    @DisplayName("Password is less 8 symbols")
    @ValueSource(strings =
            {"abcdefg", "_ _ _ ", "1234", "1", ""})
    public void tooShortPassword(String password) {
        testPassword(password, "TestUser", false);
    }

    @ParameterizedTest
    @DisplayName("Password has no digits")
    @ValueSource(strings =
            {"asdfghjkl", "__TgTg__", "AbCdEfGhIjKlMn"})
    public void noDigitsPassword(String password) {
        testPassword(password, "TestUser", false);
    }

    @ParameterizedTest
    @DisplayName("Password has no lower case letters")
    @ValueSource(strings =
            {"QWERTYU2", "01234ABCD", "P@$$W0RD"})
    public void noLowerCasePassword(String password) {
        testPassword(password, "TestUser", false);
    }

    @ParameterizedTest
    @DisplayName("Password has no upper case letters")
    @ValueSource(strings =
            {"qwerty1234", ")(*&56nm", "password5"})
    public void noUpperCasePassword(String password) {
        testPassword(password, "TestUser", false);
    }

    @Test
    @DisplayName("Password equals username")
    public void equalsUsernamePassword() {
        String username = "TestUser1";
        testPassword(username, username, false);
    }

    @ParameterizedTest
    @DisplayName("Password has spaces or quotes")
    @ValueSource(strings =
            {"qwerty 1234", "_&*^\tBg79", "\"34jdfgER", " \t\r\n\"Rg6*"})
    public void hasSpacesQuotesPassword(String password) {
        testPassword(password, "TestUser", false);
    }

    void testPasswordValidation(
            String password, String username,
            List<String> errorMessagesExpected, boolean isValidExpected) {
        ValidationResult result =
                PasswordValidator.validatePassword(password, username);
        assertEquals(isValidExpected, result.isValid());

        assertEquals(
                new HashSet<>(errorMessagesExpected),
                new HashSet<>(result.getErrorDescriptions()));
    }

    @ParameterizedTest
    @ValueSource(strings =
            {"Test6789", "1R2g3T4k5Y", "0123456789aA", "_TestUser74"})
    void validateCorrectPassword(String password) {
        testPasswordValidation(
                password, "TestUser1",
                new ArrayList<>(), true);
    }

    @Test
    void validateShortPassword() {
        testPasswordValidation(
                "TestPW1", "TestUser1",
                List.of("The password must not be shorter than 8 characters."),
                false);
    }

    @Test
    void validateHasNoDigits() {
        testPasswordValidation(
                "TestPassword", "TestUser1",
                List.of("The password must contain at least one digit."),
                false);
    }

    @Test
    void validateHasNoLowercase() {
        testPasswordValidation(
                "TESTPASSWORD1", "TestUser1",
                List.of("The password must contain at least one lowercase letter."),
                false);
    }

    @Test
    void validateHasNoUppercase() {
        testPasswordValidation(
                "testpassword1", "TestUser1",
                List.of("The password must contain at least one uppercase letter."),
                false);
    }

    @Test
    void validatePasswordEqualsUsername() {
        String password = "TestUser1";
        testPasswordValidation(
                password, password,
                List.of("The password must not match the username."),
                false);
    }

    @ParameterizedTest
    @ValueSource(strings =
            { "Qwerty 1234", "_&*^\tBg79", "\"34jdfgER", " \r\n\"Rg6*" })
    void validateSpaceCharacters(String password) {
        testPasswordValidation(
                password, "TestUser1",
                List.of("The password must not contain spaces, tabs, or quotation marks."),
                false);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                "_ _ _ ; TestUser1;" +
                        "The password must not contain spaces, tabs, or quotation marks.|" +
                        "The password must not be shorter than 8 characters.|" +
                        "The password must contain at least one uppercase letter.|" +
                        "The password must contain at least one lowercase letter.|" +
                        "The password must contain at least one digit.",
                "test; test;" +
                        "The password must not be shorter than 8 characters.|" +
                        "The password must contain at least one digit.|" +
                        "The password must contain at least one uppercase letter.|" +
                        "The password must not match the username."

            },
            delimiter = ';'
    )
    void validateMultipleErrors(String password,
                                String username,
                                String errors) {
        List<String> expectedErrors = List.of(errors.split("\\|"));
        testPasswordValidation(
                password, username,
                expectedErrors,
                false);
    }

}