package spring.ws.carrentaldirectoryweb.core.Hellper;

public class SearchMessage {
    public static String message;
    public static Integer step;

    public SearchMessage() {
        message = "";
    }

    public void addRecord(String res) {
        message += res;
    }

    public String getString() {
        return message;
    }

    public void clearMessage() {
        message = null;
    }
}
