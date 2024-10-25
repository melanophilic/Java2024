import java.util.Date;

// Класс задачи (Task)
class Task {
    private String description;
    private int priority;
    private Date dueDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Задача{" +
                "описание='" + description + '\'' +
                ", приоритет=" + priority +
                ", дата=" + dueDate +
                '}';
    }
}

// Абстрактный класс строителя (TaskBuilder)
abstract class TaskBuilder {
    protected Task task;

    public TaskBuilder() {
        this.task = new Task();
    }

    public abstract void reset();
    public abstract void setDescription(String description);
    public abstract void setPriority(int priority);
    public abstract void setDueDate(Date dueDate);

    public Task getTask() {
        return task;
    }
}

// Конкретный строитель (SimpleTaskBuilder)
class SimpleTaskBuilder extends TaskBuilder {

    @Override
    public void reset() {
        this.task = new Task();
    }

    @Override
    public void setDescription(String description) {
        task.setDescription(description);
    }

    @Override
    public void setPriority(int priority) {
        task.setPriority(priority);
    }

    @Override
    public void setDueDate(Date dueDate) {
        task.setDueDate(dueDate);
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем строитель
        SimpleTaskBuilder taskBuilder = new SimpleTaskBuilder();

        // Создаем задачу 1
        taskBuilder.reset();
        taskBuilder.setDescription("Купить стройматериалы");
        taskBuilder.setPriority(1);
        Task task1 = taskBuilder.getTask();

        // Создаем задачу 2
        taskBuilder.reset();
        taskBuilder.setDescription("Позвонить в банк");
        taskBuilder.setDueDate(new Date());
        Task task2 = taskBuilder.getTask();

        // Вывод информации о задачах
        System.out.println(task1.toString());
        System.out.println(task2.toString());
    }
}
