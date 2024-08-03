package spring.ws.carrentaldirectoryweb.core.Hellper;

public class DebugMessage {
    public static String message;

    public DebugMessage() {
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
