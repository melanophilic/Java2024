import java.util.concurrent.Semaphore;

public class ResourceAccessSync {

    // Создаем семафор с одним разрешением (1 - значит, что только один поток
    // может получить доступ к ресурсу одновременно)
    private static Semaphore semaphore = new Semaphore(1);

    // Общий ресурс, к которому нужен синхронизированный доступ
    private static class SharedResource {
        public void accessResource(String threadName) {
            System.out.println("Ресурс используется потоком: " + threadName);
            try {
                Thread.sleep(2000); // Имитация работы с ресурсом
            } catch (InterruptedException e) {
                System.err.println("Поток прерван: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
            System.out.println("Ресурс освобожден потоком: " + threadName);
        }
    }

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // Создаем несколько потоков, которые будут пытаться
        // получить доступ к ресурсу
        Thread thread1 = new Thread(() -> accessResourceSafely(resource, "Поток 1"));
        Thread thread2 = new Thread(() -> accessResourceSafely(resource, "Поток 2"));
        Thread thread3 = new Thread(() -> accessResourceSafely(resource, "Поток 3"));

        // Запускаем потоки
        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static void accessResourceSafely(SharedResource resource, String threadName) {
        try {
            // Пытаемся получить доступ к ресурсу
            semaphore.acquire();
            System.out.println(threadName + " получил доступ к ресурсу.");
            resource.accessResource(threadName);
        } catch (InterruptedException e) {
            System.err.println("Поток прерван: " + e.getMessage());
            Thread.currentThread().interrupt(); // Восстанавливаем флаг прерывания
        } finally {
            // Освобождаем ресурс в блоке finally,
            // чтобы гарантировать освобождение, даже если возникло исключение
            System.out.println(threadName + " освобождает ресурс.");
            semaphore.release();
        }
    }
}
