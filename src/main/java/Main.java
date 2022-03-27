import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите логин:");
        String login = console.nextLine();
        System.out.println("Введите пароль:");
        String pass = console.nextLine();
        PasswordHandler handler = new PasswordHandler();
        handler.addAccount(login, pass);
        System.out.println("Учетная запись успешно создана");
        System.out.println("Необходима авторизация, введите логин:");
        login = console.nextLine();
        System.out.println("Введите пароль:");
        pass = console.nextLine();
        handler.chekAccount(login, pass);
    }
}
