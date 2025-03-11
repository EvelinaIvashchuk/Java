import java.util.*;

class Task {
    private static int idCounter = 1;
    private int id;
    private String title;
    private String description;
    private Date dueDate;
    private Date createdDate;

    public Task(String title, String description, Date dueDate) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.createdDate = new Date();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Date getDueDate() { return dueDate; }
    public Date getCreatedDate() { return createdDate; }
}

public class TaskManager {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nМеню:");
            System.out.println("1. Створення завдання");
            System.out.println("2. Видалення завдання");
            System.out.println("3. Зчитування завдань");
            System.out.println("4. Оновлення завдання");
            System.out.println("5. Пошук завдання");
            System.out.println("6. Сортування завдань");
            System.out.println("7. Вихід");
            System.out.print("Виберіть опцію: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("[Створення завдання - реалізація відсутня]");
                    break;
                case "2":
                    System.out.println("[Видалення завдання - реалізація відсутня]");
                    break;
                case "3":
                    System.out.println("[Зчитування завдань - реалізація відсутня]");
                    break;
                case "4":
                    System.out.println("[Оновлення завдання - реалізація відсутня]");
                    break;
                case "5":
                    System.out.println("[Пошук завдання - реалізація відсутня]");
                    break;
                case "6":
                    System.out.println("[Сортування завдань - реалізація відсутня]");
                    break;
                case "7":
                    running = false;
                    System.out.println("Додаток закрито.");
                    break;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
        scanner.close();
    }
}
