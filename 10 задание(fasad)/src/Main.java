import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Подсистема управления задачами
class TaskManager {
    public void createTask(String taskName) {
        System.out.println("Создана задача: " + taskName);
    }

    public void assignTask(String taskName, String assignee) {
        System.out.println("Задача '" + taskName + "' назначена на: " + assignee);
    }

    public void completeTask(String taskName) {
        System.out.println("Задача '" + taskName + "' выполнена.");
    }
}

// Подсистема управления сроками
class ScheduleManager {
    public void setDeadline(String taskName, Date deadline) {
        System.out.println("Срок для задачи '" + taskName + "' установлен на: " + deadline);
    }
}

// Подсистема управления ресурсами
class ResourceManager {
    public void allocateResource(String resourceName, String taskName) {
        System.out.println("Ресурс '" + resourceName + "' выделен для задачи '" + taskName + "'");
    }

    public void releaseResource(String resourceName) {
        System.out.println("Ресурс '" + resourceName + "' освобожден.");
    }
}

// Фасад для управления проектом
class ProjectManagerFacade {
    private TaskManager taskManager;
    private ScheduleManager scheduleManager;
    private ResourceManager resourceManager;

    public ProjectManagerFacade() {
        this.taskManager = new TaskManager();
        this.scheduleManager = new ScheduleManager();
        this.resourceManager = new ResourceManager();
    }

    // Упрощенный интерфейс для создания задачи
    public void createTask(String taskName, String assignee, Date deadline, String resource) {
        taskManager.createTask(taskName);
        scheduleManager.setDeadline(taskName, deadline);
        if (resource != null) {
            resourceManager.allocateResource(resource, taskName);
        }
        taskManager.assignTask(taskName, assignee);
    }

    // Упрощенный интерфейс для завершения задачи
    public void completeTask(String taskName, String resource) {
        taskManager.completeTask(taskName);
        if (resource != null) {
            resourceManager.releaseResource(resource);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ProjectManagerFacade projectManager = new ProjectManagerFacade();

        // Используем фасад для управления задачами, сроками и ресурсами
        projectManager.createTask("Разработать модуль A", "Разработчик 1", new Date(2024, 10, 20), "Сервер разработки");
        projectManager.createTask("Протестировать модуль B", "Тестировщик", new Date(2024, 10, 25), null);

        // Завершение задачи
        projectManager.completeTask("Разработать модуль A", "Сервер разработки");
    }
}
