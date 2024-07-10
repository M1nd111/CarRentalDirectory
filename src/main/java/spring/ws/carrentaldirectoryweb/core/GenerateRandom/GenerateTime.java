package spring.ws.carrentaldirectoryweb.core.GenerateRandom;

import java.time.LocalTime;
import java.util.Random;

public class GenerateTime {

    public static LocalTime generateTime() {
        Random random = new Random();

        int h = random.nextInt(24);
        int m = random.nextInt(60);
        int s = random.nextInt(60);

        return LocalTime.of(h,m);
    }

    private GenerateTime() {
    }
}
