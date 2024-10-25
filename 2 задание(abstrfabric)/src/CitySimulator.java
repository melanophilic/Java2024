import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Абстрактный класс для зданий
abstract class Building {
    protected String name;

    public Building(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void showInfo();
}

// Конкретные классы зданий
class ResidentialBuilding extends Building {
    private int residents;

    public ResidentialBuilding(String name, int residents) {
        super(name);
        this.residents = residents;
    }

    public void showInfo() {
        System.out.println("Жилой дом: " + name);
        System.out.println("Жителей: " + residents);
    }
}

class CommercialBuilding extends Building {
    private String type;

    public CommercialBuilding(String name, String type) {
        super(name);
        this.type = type;
    }

    public void showInfo() {
        System.out.println("Коммерческое здание: " + name);
        System.out.println("Тип: " + type);
    }
}

class IndustrialBuilding extends Building {
    private String product;

    public IndustrialBuilding(String name, String product) {
        super(name);
        this.product = product;
    }

    public void showInfo() {
        System.out.println("Промышленное здание: " + name);
        System.out.println("Производит: " + product);
    }
}

// Абстрактная фабрика для зданий
interface BuildingFactory {
    Building createBuilding(String name);
}

// Конкретные фабрики для разных типов зданий
class ResidentialBuildingFactory implements BuildingFactory {
    @Override
    public Building createBuilding(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество жителей: ");
        int residents = scanner.nextInt();
        return new ResidentialBuilding(name, residents);
    }
}

class CommercialBuildingFactory implements BuildingFactory {
    @Override
    public Building createBuilding(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите тип здания (магазин, ресторан, др.): ");
        String type = scanner.nextLine();
        return new CommercialBuilding(name, type);
    }
}

class IndustrialBuildingFactory implements BuildingFactory {
    @Override
    public Building createBuilding(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите производимый продукт: ");
        String product = scanner.nextLine();
        return new IndustrialBuilding(name, product);
    }
}

// Класс симулятора города
public class CitySimulator {
    private List<Building> buildings = new ArrayList<>();

    public void constructBuilding(BuildingFactory factory) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название здания: ");
        String name = scanner.nextLine();
        buildings.add(factory.createBuilding(name));
    }

    public void showCityInfo() {
        System.out.println("\n----- Информация о городе -----");
        for (Building building : buildings) {
            building.showInfo();
            System.out.println("--------------------");
        }
    }

    public static void main(String[] args) {
        CitySimulator city = new CitySimulator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Построить жилой дом");
            System.out.println("2. Построить коммерческое здание");
            System.out.println("3. Построить промышленное здание");
            System.out.println("4. Показать информацию о городе");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    city.constructBuilding(new ResidentialBuildingFactory());
                    break;
                case 2:
                    city.constructBuilding(new CommercialBuildingFactory());
                    break;
                case 3:
                    city.constructBuilding(new IndustrialBuildingFactory());
                    break;
                case 4:
                    city.showCityInfo();
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
