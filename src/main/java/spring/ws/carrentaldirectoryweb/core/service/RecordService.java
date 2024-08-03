package spring.ws.carrentaldirectoryweb.core.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ws.carrentaldirectoryweb.core.dto.RecordInputDto;
import spring.ws.carrentaldirectoryweb.core.dto.RecordReadDto;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsReadDto;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsWebDto;
import spring.ws.carrentaldirectoryweb.core.entity.RecordEntity;
import spring.ws.carrentaldirectoryweb.core.entity.RecordsEntity;
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
                .model_name(recordEntity.getModelName())
                .date(recordEntity.getDate())
                .stateNumber(recordEntity.getStateNumber())
                .year(recordEntity.getYear())
                .build()).collect(Collectors.toList());
    }

    public Boolean dellEntity(RecordInputDto RecordInputDto) {
        recordRepository.save(RecordEntity.builder()
                        .stateNumber(RecordInputDto.getStateNumber())
                        .modelName(RecordInputDto.getModelName())
                        .date(RecordInputDto.getDate())
                        .year(RecordInputDto.getYear())
                .build());
        return true;
    }

    public Boolean addEntity(RecordInputDto recordInputDto) {
        recordRepository.save(RecordEntity.builder()
                    .stateNumber(recordInputDto.getStateNumber())
                    .year(recordInputDto.getYear())
                    .modelName(recordInputDto.getModelName())
                    .date(recordInputDto.getDate())
                .build());
        return true;
    }
    public Boolean delAll() {
        recordRepository.deleteAll();
        return true;
    }

}
