import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Интерфейс для наблюдаемого устройства
interface NetworkDevice {
    void addObserver(NetworkAdmin observer);
    void removeObserver(NetworkAdmin observer);
    void notifyObservers(String message);
}

// Конкретный класс сетевого устройства (например, сервер)
class Server implements NetworkDevice {
    private String name;
    private String status;
    private List<NetworkAdmin> observers = new ArrayList<>();

    public Server(String name) {
        this.name = name;
        this.status = "OK";  // Начальное состояние
    }

    public void setStatus(String status) {
        this.status = status;
        // Уведомляем администраторов при изменении состояния
        notifyObservers("Статус сервера " + name + ": " + status);
    }

    @Override
    public void addObserver(NetworkAdmin observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(NetworkAdmin observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (NetworkAdmin observer : observers) {
            observer.update(message);
        }
    }
}

// Интерфейс для наблюдателя (администратора)
interface NetworkAdmin {
    void update(String message);
}

// Конкретный класс администратора
class ConcreteAdmin implements NetworkAdmin {
    private String name;

    public ConcreteAdmin(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Администратор " + name + " получил уведомление: " + message);
    }
}

public class NetworkMonitoringSystem {
    public static void main(String[] args) {
        // Создаем серверы
        Server server1 = new Server("Server1");
        Server server2 = new Server("Server2");

        // Создаем администраторов
        NetworkAdmin admin1 = new ConcreteAdmin("Admin1");
        NetworkAdmin admin2 = new ConcreteAdmin("Admin2");

        // Администраторы подписываются на уведомления от серверов
        server1.addObserver(admin1);
        server1.addObserver(admin2);
        server2.addObserver(admin2);

        // Изменяем статус серверов (симуляция событий)
        server1.setStatus("Warning: CPU Load High");
        server2.setStatus("Error: Database connection failed");
        server1.setStatus("OK");
    }
}
