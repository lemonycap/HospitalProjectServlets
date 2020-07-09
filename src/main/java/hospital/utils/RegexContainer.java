package hospital.utils;

public interface RegexContainer {
    String REGEX_NAME_ENG="^[A-Z][a-z]{3,29}$";
    String REGEX_EMAIL = "([a-z0-9_-]+\\.)*[a-z0-9_-]+\\@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    String REGEX_PASSWORD = "^[A-Za-z0-9_-]{3,30}$";
}
