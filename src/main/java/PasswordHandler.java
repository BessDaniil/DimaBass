import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PasswordHandler {
    //неизменяемая кодовая фраза
    private static final String CODE_PHRASE = "code";
    //файл хранения паролей
    private static final String FILE_NAME = "LogPass.txt";

    //создание учетной записи (заполнение логина и пароля в файл)
    public void addAccount(String login, String pass) {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            writer.write(login + ";");
            writer.write(addCodingPhrase(pass));
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //добавление кодировки к паролю
    private String addCodingPhrase(String pass) {
        String[] symbols = pass.split("");
        String[] code = CODE_PHRASE.split("");
        int j = 0;
        final int codeLength = CODE_PHRASE.length();
        String encodingPass = "";
        for (int i = 0; i < symbols.length; i++) {
            if (j == codeLength) {
                j = 0;
            }
            encodingPass = encodingPass + symbols[i] + code[j];
            j++;
        }
        return encodingPass;
    }

    //проверка наличия логина и пароля в файле
    public void chekAccount(String login, String pass) {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Scanner scanner = new Scanner(reader);
            String[] line = scanner.nextLine().split(";");
            String fileLogin = line[0];
            String filePass = line[1];
            //избегаем проверки пустого логина или пароля (Null Pointer Exception)
            if (StringUtils.equals(fileLogin, login) && StringUtils.equals(filePass, addCodingPhrase(pass))) {
                System.out.println("Вы успешно авторизованы");
            } else System.out.println("Неверный логин или пароль");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
