package spring.ws.carrentaldirectoryweb.core.GenerateRandom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class GenerateFIO {

    public static List<String> getFIO() {
        String string;
        List<String> list = new ArrayList<>();

        try {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/random.data/FIO.txt"));

            while (reader.ready()){
                string = reader.readLine();
                list.add(string);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }


    private GenerateFIO() {
    }
}

