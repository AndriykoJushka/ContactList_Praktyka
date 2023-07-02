import java.util.Objects;
import java.util.Scanner;

public class Main {
    static String[] commands = {"exit", "add", "delete", "search", "edit", "print"};
    //список команд


    public static void main (String[] args){
        ContactList myContactList = new ContactList();
        myContactList.add("Gas", "1");
        myContactList.add("Police", "2");
        myContactList.add("Med", "3");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введіть будь-яку команду");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")){
                System.out.println("До зустрічі!");
                System.exit(0);

            } else if (command.equalsIgnoreCase("help")){
                System.out.println("Всі доступні команди:");
                for (int i = 0; i < commands.length; i++){
                    System.out.println(i + "." + commands[i]);
                }

            } else if (command.equalsIgnoreCase("add")){
                System.out.println("Введіть ім'я");
                String name = scanner.nextLine();
                name = name.toLowerCase();
                System.out.println("Введіть номер телефону");
                String number = scanner.nextLine();
                myContactList.add(name, number);
                System.out.println("Номер збережено");

            } else if (command.equalsIgnoreCase("search")){
                System.out.println("Введіть ім'я");
                String name = scanner.nextLine();
                name = name.toLowerCase();
                String number = myContactList.search(name);
                if (number == null){
                    System.out.println("Номер не знайдено");
                } else {
                    System.out.println(number);
                }

            } else if (command.equalsIgnoreCase("delete")){
                System.out.println("Введіть ім'я людини, номер якої Ви хочете видалити");
                String name = scanner.nextLine();
                name = name.toLowerCase();
                if (myContactList.delete(name)){
                    System.out.println("Номер успішно видалено");
                } else {
                    System.out.println("Даного контакту не існує");
                }

            } else if (command.equalsIgnoreCase("edit")){
                System.out.println("Якщо Ви хочете змінити номер, введіть 1. Якщо Ви хочете змінити ім'я, введіть 2.");
                String userInput = scanner.nextLine();
                if (Objects.equals(userInput, "1")) {
                    System.out.println("Введіть ім'я людини, номер якої Ви хочете поміняти");
                    String name = scanner.nextLine();
                    if (myContactList.numberExist(name)){
                        System.out.println("Введіть новий номер");
                        String number = scanner.nextLine();
                        myContactList.editNumber(name, number);
                        System.out.println("Номер успішно змінено");
                    } else {
                        System.out.println("Контакта з таким іменем не існує");
                    }
                } else if (Objects.equals(userInput, "2")) {
                    System.out.println("Введіть ім'я, яке Ви хочете поміняти");
                    String oldName = scanner.nextLine();
                    if (myContactList.numberExist(oldName)){
                        System.out.println("Введіть нове ім'я");
                        String newName = scanner.nextLine();
                        myContactList.editName(oldName, newName);
                        System.out.println("Ім'я успішно змінено");
                    } else {
                        System.out.println("Контакта з таким іменем не існує");
                    }
                }

            } else if (command.equalsIgnoreCase("print")) {
                myContactList.print();

            }else {
                System.out.println("Даної команди не існує");
            }

        }
    }
}