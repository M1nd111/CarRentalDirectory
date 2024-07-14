package spring.ws.carrentaldirectoryweb.core.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.ws.carrentaldirectoryweb.core.dto.RecordReadDto;
import spring.ws.carrentaldirectoryweb.core.repository.RecordRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ToString
@Service
public class RecordService {
    @Autowired
    RecordRepository recordRepository;
    public List<RecordReadDto> findAll() {

        return recordRepository.findAll().stream().map(recordEntity -> RecordReadDto.builder()
                .id(recordEntity.getId())
                .stateNumber(recordEntity.getStateNumber())
                .phoneNumber(recordEntity.getPhoneNumber())
                .markName(recordEntity.getMarkName())
                .date(recordEntity.getDate())
                .build()).collect(Collectors.toList());
    }
}
