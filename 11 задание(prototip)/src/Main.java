import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Абстрактный класс для сценариев тестирования
abstract class TestScenario implements Cloneable {
    private String name;

    public TestScenario(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Методы для модификации параметров сценария (абстрактные)
    public abstract void randomizeData();
    public abstract void changeEnvironment(String newEnvironment);

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Ошибка клонирования сценария: " + e.getMessage());
        }
    }
}

// Конкретный сценарий для тестирования API
class ApiTestScenario extends TestScenario {
    private String apiEndpoint;
    private String requestMethod;
    private String requestBody;

    public ApiTestScenario(String name, String apiEndpoint, String requestMethod, String requestBody) {
        super(name);
        this.apiEndpoint = apiEndpoint;
        this.requestMethod = requestMethod;
        this.requestBody = requestBody;
    }

    @Override
    public void randomizeData() {
        // Логика для рандомизации данных (requestBody)
        System.out.println("Данные сценария '" + getName() + "' изменены.");
    }

    @Override
    public void changeEnvironment(String newEnvironment) {
        // Логика для изменения окружения (например, apiEndpoint)
        this.apiEndpoint = newEnvironment + apiEndpoint;
        System.out.println("Окружение сценария '" + getName() + "' изменено на: " + newEnvironment);
    }

    @Override
    public String toString() {
        return "ApiTestScenario{" +
                "name='" + getName() + '\'' +
                ", apiEndpoint='" + apiEndpoint + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestBody='" + requestBody + '\'' +
                '}';
    }
}

// Генератор сценариев
class TestScenarioGenerator {
    private List<TestScenario> scenarioPrototypes;
    private Random random = new Random();

    public TestScenarioGenerator() {
        scenarioPrototypes = new ArrayList<>();
        // Добавление прототипов сценариев
        scenarioPrototypes.add(new ApiTestScenario("API Test 1",
                "/api/users", "POST", "{\"name\":\"user1\"}"));
        scenarioPrototypes.add(new ApiTestScenario("API Test 2",
                "/api/products", "GET", ""));
    }

    public TestScenario generateScenario() {
        // Случайный выбор прототипа сценария
        int index = random.nextInt(scenarioPrototypes.size());
        TestScenario prototype = scenarioPrototypes.get(index);

        // Клонирование и модификация сценария
        TestScenario newScenario = (TestScenario) prototype.clone();
        newScenario.setName("Клон " + prototype.getName());
        newScenario.randomizeData();
        // newScenario.changeEnvironment("TEST_ENV_");
        return newScenario;
    }
}

public class Main {
    public static void main(String[] args) {
        TestScenarioGenerator generator = new TestScenarioGenerator();

        // Генерация и вывод информации о новых сценариях
        for (int i = 0; i < 5; i++) {
            TestScenario scenario = generator.generateScenario();
            System.out.println(scenario.toString());
        }
    }
}
