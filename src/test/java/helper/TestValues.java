package helper;

import java.util.Random;

public class TestValues {
    public static final String USERNAME = "fominaelena";
    public static final String PASSWORD = "1P73BP4Z";
    public static final String USERNAME_WITH_SPACE = " fominaelena";
    public static final String PASSWORD_WITH_SPACE = " 1P73BP4Z";
    public static final String WRONG_VALUES = "Неверные данные для авторизации";
    public static final String MAX_LENGTH_CHARS = "Логин или пароль слишком длинные";
    public static final String EMPTY_USERNAME = "";
    public static final String EMPTY_PASSWORD = "";

    public static String RANDOM_VALUE(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }
}
