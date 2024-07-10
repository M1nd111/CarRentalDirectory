package spring.ws.carrentaldirectoryweb.core.Hellper;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import spring.ws.carrentaldirectoryweb.core.repository.UserUploadRepository;
import spring.ws.carrentaldirectoryweb.core.entity.UserUpload;
import spring.ws.carrentaldirectoryweb.sd.hashTable.hash.TableEntity.DynamicTableStatus01;


import java.time.LocalTime;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UploadHashTableToDatabase {
    private final UserUploadRepository userRepository;
    SessionFactory sessionFactory;
    private final static  String DELETE_ALL_ROWS_SQL = """
                                            DELETE FROM user_result.%s
                                            """;



    public void upload(ArrayList<DynamicTableStatus01> list){

        userRepository.deleteAll();

        for (int it = 0; it < list.size(); it++) {
            if(list.get(it) != null) {
                if (list.get(it).getLine() != null) {
                    String[] parts = list.get(it).getAllPartsLine();
                    UserUpload upload = UserUpload.builder()
                            .attemptsNumber(it)
                            .time(LocalTime.of(Integer.parseInt(parts[0].split(":")[0]),
                                    Integer.parseInt(parts[0].split(":")[1])))
                            .firstname(parts[1])
                            .name(parts[2])
                            .lastname(parts[3])
                            .markName(parts[4])
                            .applicationNumber(Long.valueOf(parts[5]))
                            .firstNumber(list.get(it).getId())
                            .build();

                    userRepository.save(upload);

                }
            }

        }

    }



}
