package spring.ws.carrentaldirectoryweb.core.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsReadDto;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsWebDto;
import spring.ws.carrentaldirectoryweb.core.entity.RecordsEntity;
import spring.ws.carrentaldirectoryweb.core.repository.RecordRepository;
import spring.ws.carrentaldirectoryweb.core.repository.RecordsRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@ToString
@Service
public class RecordsService {
    private final RecordsRepository recordsRepository;
    public List<RecordsReadDto> findAll() {

        return recordsRepository.findAll().stream().map(recordsEntity -> RecordsReadDto.builder()
                .id(recordsEntity.getId())
                .fio(recordsEntity.getFio())
                .phoneNumber(recordsEntity.getPhoneNumber())
                .markName(recordsEntity.getMarkName())
                .first_date(recordsEntity.getFirstDate())
                .last_date(recordsEntity.getLastDate())
                .build()).collect(Collectors.toList());
    }

    public Boolean dellEntity(RecordsWebDto recordsWebDto) {
        recordsRepository.save(RecordsEntity.builder()
                .fio(recordsWebDto.getFio())
                .phoneNumber(recordsWebDto.getPhoneNumber())
                .markName(recordsWebDto.getMarkName())
                .firstDate(recordsWebDto.getFirsDate())
                .lastDate(recordsWebDto.getLastDate())
                .build());
        return true;
    }

    public Boolean addEntity(RecordsWebDto recordsWebDto) {
        recordsRepository.save(RecordsEntity.builder()
                .fio(recordsWebDto.getFio())
                .phoneNumber(recordsWebDto.getPhoneNumber())
                .markName(recordsWebDto.getMarkName())
                .firstDate(recordsWebDto.getFirsDate())
                .lastDate(recordsWebDto.getLastDate())
                .build());
        return true;
    }
    public Boolean delAll() {
        recordsRepository.deleteAll();
        return true;
    }

}
