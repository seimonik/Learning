public class Data {
    private String packet; // данные, которые передаются
    private boolean transfer = true;
    // True - получатель ждет
    // False - отправитель ждет

    // метод для получателя
    public synchronized String receive() {
        while (transfer) {
            try {
                wait(); // ждем данные от отправителя
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        transfer = true;

        String returnPacket = packet;
        notifyAll();
        return returnPacket; // возвращаем полученную информацию
    }

    // метод для отправителя
    public synchronized void send(String packet) {
        while (!transfer) {
            try {
                wait(); // ждем, кода получатель будет свободен
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        transfer = false;

        this.packet = packet;
        notifyAll();
    }
}
