import java.util.concurrent.Semaphore;

public class Admin implements Runnable {

    // Семфор для контролю кількості товарів
    static Semaphore stockSemaphore = new Semaphore(0);
    static int stock = 0;  // Початкова кількість товарів

    // Прапор, який визначає, чи працює магазин
    static boolean isShopOpen = true;

    @Override
    public void run() {
        try {
            // Симуляція робочих годин адміністратора
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 10000) {
                if (isShopOpen) {
                    addStock();  // Додаємо товар
                }
                Thread.sleep(1000);  // Адміністратор додає товар кожну секунду
            }
            System.out.println("Адміністратор припинив роботу.");
        } catch (InterruptedException e) {
            System.out.println("Помилка при додаванні товару.");
        }
    }

    // Метод для додавання товару
    private void addStock() throws InterruptedException {
        stock++;  // Збільшуємо кількість товарів
        stockSemaphore.release();  // Дозволяємо покупцю придбати товар
        System.out.println("Адміністратор додав товар. Товарів у наявності: " + stock);
    }
}
