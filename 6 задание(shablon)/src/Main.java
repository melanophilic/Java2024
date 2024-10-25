import java.util.Date;

// Абстрактный класс для всех типов задач
abstract class Task {
    protected String title;
    protected String description;
    protected Date dueDate;
    protected boolean completed;

    public Task(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
    }

    // Шаблонный метод
    public final void manageTask() {
        createTask();
        assignTask();
        workOnTask();
        if (isCompleted()) {
            completeTask();
        }
    }

    // Абстрактные методы, которые должны быть реализованы подклассами
    protected abstract void createTask();
    protected abstract void assignTask();
    protected abstract void workOnTask();
    protected abstract void completeTask();

    // Метод для проверки завершения задачи
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Геттеры и сеттеры (при необходимости)
    // ...
}

// Конкретный тип задачи - Задача
class SimpleTask extends Task {
    public SimpleTask(String title, String description, Date dueDate) {
        super(title, description, dueDate);
    }

    @Override
    protected void createTask() {
        System.out.println("Задача '" + title + "' создана.");
    }

    @Override
    protected void assignTask() {
        System.out.println("Задача '" + title + "' не назначена на кого-либо (личная задача).");
    }

    @Override
    protected void workOnTask() {
        System.out.println("Выполняем задачу: " + description);
        // Логика выполнения задачи
    }

    @Override
    protected void completeTask() {
        System.out.println("Задача '" + title + "' выполнена.");
    }
}

// Конкретный тип задачи - Проект
class Project extends Task {
    private String assignedTo;

    public Project(String title, String description, Date dueDate, String assignedTo) {
        super(title, description, dueDate);
        this.assignedTo = assignedTo;
    }

    @Override
    protected void createTask() {
        System.out.println("Проект '" + title + "' создан.");
    }

    @Override
    protected void assignTask() {
        System.out.println("Проект '" + title + "' назначен на: " + assignedTo);
    }

    @Override
    protected void workOnTask() {
        System.out.println("Работаем над проектом: " + description);
        // Логика выполнения проекта
    }

    @Override
    protected void completeTask() {
        System.out.println("Проект '" + title + "' завершен.");
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        // Создание задач разных типов
        Task simpleTask = new SimpleTask("Купить продукты", "Купить молоко, хлеб, яйца", new Date());
        Task project = new Project("Разработать сайт", "Создать сайт на Java", new Date(), "Разработчик");

        // Управление задачами
        simpleTask.manageTask();
        System.out.println("---------------------");
        project.manageTask();
    }
}

