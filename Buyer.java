import java.util.concurrent.Semaphore;

public class Buyer implements Runnable {

    @Override
    public void run() {
        try {
            // Покупець намагається купити товар в межах робочого часу
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 5000) {
                if (Admin.isShopOpen) {
                    buyProduct();
                } else {
                    System.out.println("Магазин закритий. Покупець не може купити товар.");
                    return;
                }
                Thread.sleep(1500);  // Покупець намагається купити товар кожну секунду
            }
        } catch (InterruptedException e) {
            System.out.println("Помилка під час покупки.");
        }
    }

    // Метод для покупки товару
    private void buyProduct() throws InterruptedException {
        if (Admin.stockSemaphore.tryAcquire()) {  // Перевіряємо наявність товару
            Admin.stock--;  // Зменшуємо кількість товарів
            System.out.println("Покупець придбав товар. Товарів у наявності: " + Admin.stock);
        } else {
            System.out.println("Товар відсутній в наявності.");
        }
    }
}
