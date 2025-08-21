import java.util.*;

class Task {
    String description;
    boolean completed;

    Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + (completed ? "X" : " ") + "] " + description;
    }
}

public class Main {
    static List<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("\n1. Добавить задачу\n2. Показать задачи\n3. Удалить задачу\n4. Изменить статус");
                System.out.print("Выберите опцию: ");
                
                String option = scanner.nextLine();
                switch (option) {
                    case "1": addTask(); break;
                    case "2": showTasks(); break;
                    case "3": deleteTask(); break;
                    case "4": toggleTask(); break;
                    default: System.out.println("Неверная опция");
                }
            } catch (NoSuchElementException e) {
                System.out.println("\nЗавершение работы приложения");
                break;
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                if (scanner.hasNextLine()) scanner.nextLine(); // Очистка буфера
            }
        }
    }

    static void addTask() {
        System.out.print("Введите описание: ");
        String description = scanner.nextLine();
        tasks.add(new Task(description));
    }

    static void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    static void deleteTask() {
        showTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Введите номер для удаления: ");
        try {
            int index = getIndex();
            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
            } else {
                System.out.println("Неверный номер задачи");
            }
        } catch (Exception e) {
            System.out.println("Ошибка удаления: " + e.getMessage());
        }
    }

    static void toggleTask() {
        showTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Введите номер для изменения: ");
        try {
            int index = getIndex();
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                task.completed = !task.completed;
            } else {
                System.out.println("Неверный номер задачи");
            }
        } catch (Exception e) {
            System.out.println("Ошибка изменения: " + e.getMessage());
        }
    }

    static int getIndex() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input) - 1;
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: введите число: ");
            }
        }
    }
}