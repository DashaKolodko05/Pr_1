public class OnlineStore {

    public static void main(String[] args) {
        // Створюємо потоки для адміністратора та покупців
        Thread adminThread = new Thread(new Admin());
        Thread buyer1Thread = new Thread(new Buyer());
        Thread buyer2Thread = new Thread(new Buyer());
        Thread buyer3Thread = new Thread(new Buyer());
        Thread buyer4Thread = new Thread(new Buyer());

        // Запускаємо потоки
        adminThread.start();
        buyer1Thread.start();
        buyer2Thread.start();
        buyer3Thread.start();
        buyer4Thread.start();

        // Симуляція робочих годин магазину
        try {
            Thread.sleep(10000);  // Магазин працює 10 секунд
        } catch (InterruptedException e) {
            System.out.println("Помилка під час симуляції робочого часу магазину.");
        }

        // Закриваємо магазин
        Admin.isShopOpen = false;
        System.out.println("Магазин закритий.");
    }
}
