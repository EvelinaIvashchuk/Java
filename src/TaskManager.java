import java.text.SimpleDateFormat;
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
                case "5": searchTask(); break;
                case "6": sortTasks(); break;
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
    private static void sortTasks() {
        System.out.println("Оберіть критерій сортування:");
        System.out.println("1. За датою виконання (від найстаріших)");
        System.out.println("2. За датою створення (від найновіших)");
        System.out.print("Ваш вибір: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                tasks.sort(Comparator.comparing(Task::getDueDate));
                System.out.println("Відсортовано за датою виконання:");
                break;
            case "2":
                tasks.sort(Comparator.comparing(Task::getCreatedDate).reversed());
                System.out.println("Відсортовано за датою створення:");
                break;
            default:
                System.out.println("Невірний вибір!");
                return;
        }
        readTasks();
    }
    private static void searchTask() {
        System.out.println("Оберіть критерій пошуку:");
        System.out.println("1. За назвою");
        System.out.println("2. За терміном виконання (yyyy-MM-dd)");
        System.out.print("Ваш вибір: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Введіть ключове слово для пошуку в назві: ");
                String keyword = scanner.nextLine().toLowerCase();
                tasks.stream()
                        .filter(task -> task.getTitle().toLowerCase().contains(keyword))
                        .forEach(System.out::println);
                break;

            case "2":
                System.out.print("Введіть дату виконання (yyyy-MM-dd): ");
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date searchDate = sdf.parse(scanner.nextLine());

                    tasks.stream()
                            .filter(task -> sdf.format(task.getDueDate()).equals(sdf.format(searchDate))) // Порівнюємо лише дату
                            .forEach(System.out::println);
                } catch (Exception e) {
                    System.out.println("Невірний формат дати!");
                }
                break;

            default:
                System.out.println("Невірний вибір!");
        }
    }
}
