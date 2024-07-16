package spring.ws.carrentaldirectoryweb.core.Hellper;

import spring.ws.carrentaldirectoryweb.core.dto.RecordReadDto;
import spring.ws.carrentaldirectoryweb.core.dto.RecordWebDto;

import java.util.ArrayList;

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
