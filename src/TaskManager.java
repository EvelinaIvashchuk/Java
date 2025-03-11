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

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Description: " + description + ", Due: " + dueDate + ", Created: " + createdDate;
    }
}

public class TaskManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Task> tasks = new ArrayList<>();
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
                case "1": createTask(); break;
                case "2": deleteTask(); break;
                case "3": readTasks(); break;
                case "4": updateTask(); break;
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
    private static void createTask() {
        System.out.print("Введіть назву завдання: ");
        String title = scanner.nextLine();
        System.out.print("Введіть опис: ");
        String description = scanner.nextLine();
        System.out.print("Введіть дату виконання (yyyy-MM-dd): ");
        try {
            Date dueDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
            tasks.add(new Task(title, description, dueDate));
            System.out.println("Завдання створено!");
        } catch (Exception e) {
            System.out.println("Невірний формат дати!");
        }
    }

    private static void readTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Завдань немає.");
        } else {
            tasks.forEach(System.out::println);
        }
    }

    private static void updateTask() {
        System.out.print("Введіть ID завдання для оновлення: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Task task : tasks) {
            if (task.getId() == id) {
                System.out.print("Нова назва: ");
                task.setTitle(scanner.nextLine());
                System.out.print("Новий опис: ");
                task.setDescription(scanner.nextLine());
                System.out.print("Нова дата (yyyy-MM-dd): ");
                try {
                    task.setDueDate(new java.text.SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine()));
                    System.out.println("Завдання оновлено!");
                } catch (Exception e) {
                    System.out.println("Невірний формат дати!");
                }
                return;
            }
        }
        System.out.println("Завдання не знайдено.");
    }

    private static void deleteTask() {
        System.out.print("Введіть ID завдання для видалення: ");
        int id = Integer.parseInt(scanner.nextLine());
        tasks.removeIf(task -> task.getId() == id);
        System.out.println("Завдання видалено.");
    }
}
