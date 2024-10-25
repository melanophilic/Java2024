import java.util.Scanner;

// Интерфейс для блюд
interface Dish {
    String getName();
    double getPrice();
    String getDescription();
}

// Конкретные классы блюд (Итальянская)
class ItalianPizza implements Dish {
    @Override
    public String getName() {
        return "Пепперони";
    }

    @Override
    public double getPrice() {
        return 10.50;
    }

    @Override
    public String getDescription() {
        return "Классическая пицца с пепперони и сыром моцарелла";
    }
}

// Конкретные классы блюд (Китайская)
class ChineseNoodles implements Dish {
    @Override
    public String getName() {
        return "Лапша по-сычуаньски";
    }

    @Override
    public double getPrice() {
        return 8.75;
    }

    @Override
    public String getDescription() {
        return "Острая лапша с овощами и курицей";
    }
}

// Конкретные классы блюд (Мексиканская)
class MexicanBurrito implements Dish {
    @Override
    public String getName() {
        return "Буррито с говядиной";
    }

    @Override
    public double getPrice() {
        return 9.25;
    }

    @Override
    public String getDescription() {
        return "Сытное буррито с говядиной, фасолью и рисом";
    }
}

// Абстрактная фабрика для создания блюд
interface KitchenFactory {
    Dish createDish();
}

// Конкретные фабрики для создания блюд разных кухонь (Итальянская)
class ItalianKitchen implements KitchenFactory {
    @Override
    public Dish createDish() {
        return new ItalianPizza();
    }
}

// Конкретные фабрики для создания блюд разных кухонь (Китайская)
class ChineseKitchen implements KitchenFactory {
    @Override
    public Dish createDish() {
        return new ChineseNoodles();
    }
}

// Конкретные фабрики для создания блюд разных кухонь (Мексиканская)
class MexicanKitchen implements KitchenFactory {
    @Override
    public Dish createDish() {
        return new MexicanBurrito();
    }
}

// Класс заказа
class Order {
    private Dish dish;

    public Order(KitchenFactory factory) {
        this.dish = factory.createDish();
    }

    public void printOrderDetails() {
        System.out.println("Ваш заказ:");
        System.out.println("Блюдо: " + dish.getName());
        System.out.println("Описание: " + dish.getDescription());
        System.out.println("Цена: $" + dish.getPrice());
    }
}

// Главный класс приложения
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Выбор кухни
        KitchenFactory kitchen = chooseKitchen(scanner);
        if (kitchen == null) {
            System.out.println("Неверный выбор кухни!");
            scanner.close();
            return;
        }

        // Создание заказа
        Order order = new Order(kitchen);
        order.printOrderDetails();

        scanner.close();
    }

    // Метод для выбора кухни
    private static KitchenFactory chooseKitchen(Scanner scanner) {
        System.out.println("Выберите кухню:");
        System.out.println("1. Итальянская");
        System.out.println("2. Китайская");
        System.out.println("3. Мексиканская");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        switch (choice) {
            case 1:
                return new ItalianKitchen();
            case 2:
                return new ChineseKitchen();
            case 3:
                return new MexicanKitchen();
            default:
                return null;
        }
    }
}

