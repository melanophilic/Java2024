import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Интерфейс для стратегии сохранения/загрузки
interface ConfigurationStrategy {
    void save(ServerConfiguration config) throws IOException;
    ServerConfiguration load() throws IOException;
}

// Стратегия сохранения/загрузки в файл
class FileConfigurationStrategy implements ConfigurationStrategy {
    private String filename;

    public FileConfigurationStrategy(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(ServerConfiguration config) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(config);
        }
        System.out.println("Конфигурация сохранена в файл: " + filename);
    }

    @Override
    public ServerConfiguration load() throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Файл конфигурации не найден. Будет создана новая конфигурация.");
            return new ServerConfiguration();
        }
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            return (ServerConfiguration) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Ошибка загрузки конфигурации из файла: " + e.getMessage());
        }
    }
}

// Класс для конфигурации сервера
class ServerConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ipAddress;
    private int port;
    private Map<String, String> properties = new HashMap<>();

    public ServerConfiguration() {}

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

    @Override
    public String toString() {
        return "Конфигурация сервера{" +
                "IP адрес='" + ipAddress + '\'' +
                ", порт=" + port +
                ", дополнительные свойства=" + properties +
                '}';
    }
}

// Класс для управления конфигурацией
class ServerConfigurationManager {
    private ConfigurationStrategy strategy;
    private ServerConfiguration config;

    public ServerConfigurationManager(ConfigurationStrategy strategy) {
        this.strategy = strategy;
        // Попытка загрузить существующую конфигурацию
        try {
            this.config = strategy.load();
        } catch (IOException e) {
            System.err.println("Ошибка загрузки конфигурации: " + e.getMessage());
            // Создаем пустую конфигурацию в случае ошибки
            this.config = new ServerConfiguration();
        }
    }

    public void setConfiguration(ServerConfiguration config) {
        this.config = config;
    }

    public ServerConfiguration getConfiguration() {
        return config;
    }

    public void saveConfiguration() throws IOException {
        strategy.save(config);
    }

    public void loadConfiguration() throws IOException {
        this.config = strategy.load();
    }
}

public class Main {
    public static void main(String[] args) {
        // Используем стратегию сохранения/загрузки в файл
        ConfigurationStrategy strategy = new FileConfigurationStrategy("server.config");
        ServerConfigurationManager manager = new ServerConfigurationManager(strategy);

        // Настраиваем конфигурацию
        manager.getConfiguration().setIpAddress("192.168.1.100");
        manager.getConfiguration().setPort(8080);
        manager.getConfiguration().setProperty("database.url", "jdbc:mysql://localhost:3306/mydb");
        manager.getConfiguration().setProperty("database.user", "user");
        manager.getConfiguration().setProperty("database.password", "password");

        try {
            // Сохраняем конфигурацию
            manager.saveConfiguration();

            // Изменяем конфигурацию (симуляция)
            manager.getConfiguration().setPort(8888);

            // Загружаем конфигурацию из файла
            manager.loadConfiguration();

            // Выводим конфигурацию
            System.out.println(manager.getConfiguration().toString());

        } catch (IOException e) {
            System.err.println("Ошибка работы с конфигурацией: " + e.getMessage());
        }
    }
}
