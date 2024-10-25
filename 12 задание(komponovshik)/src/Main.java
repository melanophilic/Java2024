import java.util.ArrayList;
import java.util.List;

// Абстрактный класс для элементов проекта (задачи, этапы, проекты)
abstract class ProjectComponent {
    protected String name;

    public ProjectComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Общие операции для всех элементов (могут быть пустыми)
    public void add(ProjectComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(ProjectComponent component) {
        throw new UnsupportedOperationException();
    }

    public abstract void display(int depth);
}

// Конкретный класс для задачи
class Task extends ProjectComponent {
    public Task(String name) {
        super(name);
    }

    @Override
    public void display(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("- Задача: " + name);
    }
}

// Компоновщик (Composite) для этапов и проектов
class ProjectGroup extends ProjectComponent {
    private List<ProjectComponent> children = new ArrayList<>();

    public ProjectGroup(String name) {
        super(name);
    }

    @Override
    public void add(ProjectComponent component) {
        children.add(component);
    }

    @Override
    public void remove(ProjectComponent component) {
        children.remove(component);
    }

    @Override
    public void display(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("+ " + name + ":");
        for (ProjectComponent component : children) {
            component.display(depth + 1);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем структуру проекта
        ProjectComponent mainProject = new ProjectGroup("Мой проект");

        ProjectComponent phase1 = new ProjectGroup("Этап 1");
        phase1.add(new Task("Задача 1.1"));
        phase1.add(new Task("Задача 1.2"));

        ProjectComponent phase2 = new ProjectGroup("Этап 2");
        phase2.add(new Task("Задача 2.1"));
        ProjectComponent subPhase21 = new ProjectGroup("Подэтап 2.1");
        subPhase21.add(new Task("Задача 2.1.1"));
        subPhase21.add(new Task("Задача 2.1.2"));
        phase2.add(subPhase21);
        phase2.add(new Task("Задача 2.2"));

        mainProject.add(phase1);
        mainProject.add(phase2);

        // Выводим структуру проекта
        mainProject.display(0);
    }
}
