package spring.ws.carrentaldirectoryweb.core.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ws.carrentaldirectoryweb.core.dto.RecordReadDto;
import spring.ws.carrentaldirectoryweb.core.dto.RecordWebDto;
import spring.ws.carrentaldirectoryweb.core.entity.RecordEntity;
import spring.ws.carrentaldirectoryweb.core.repository.RecordRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@ToString
@Service
public class RecordService {
    private final RecordRepository recordRepository;
    public List<RecordReadDto> findAll() {

        return recordRepository.findAll().stream().map(recordEntity -> RecordReadDto.builder()
                .id(recordEntity.getId())
                .stateNumber(recordEntity.getStateNumber())
                .phoneNumber(recordEntity.getPhoneNumber())
                .markName(recordEntity.getMarkName())
                .date(recordEntity.getDate())
                .build()).collect(Collectors.toList());
    }

    public Boolean dellEntity(RecordWebDto recordWebDto) {
        recordRepository.save(RecordEntity.builder()
                        .stateNumber(recordWebDto.getStateNumber())
                        .phoneNumber(recordWebDto.getPhoneNumber())
                        .markName(recordWebDto.getMarkName())
                        .date(recordWebDto.getDate())
                .build());
        return true;
    }

    public Boolean addEntity(RecordWebDto recordWebDto) {
        recordRepository.save(RecordEntity.builder()
                    .stateNumber(recordWebDto.getStateNumber())
                    .phoneNumber(recordWebDto.getPhoneNumber())
                    .markName(recordWebDto.getMarkName())
                    .date(recordWebDto.getDate())
                .build());
        return true;
    }
    public Boolean delAll() {
        recordRepository.deleteAll();
        return true;
    }

}
