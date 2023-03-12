import java.util.concurrent.ThreadLocalRandom;
public class Receiver implements Runnable {
    private Data load;
    public Receiver(Data d){
        this.load = d;
    }

    public void run() {
        for(String receivedMessage = load.receive();
            !"End".equals(receivedMessage);
            receivedMessage = load.receive()) {

            System.out.println(receivedMessage);

            // имитация обработки данных
            try {
                // приостанавливаем поток на случайное время
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}