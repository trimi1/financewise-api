package be.helmo.api.service;

import be.helmo.api.dto.DeviseDTO;
import be.helmo.api.dto.mappers.DeviseMapper;
import be.helmo.api.infrastructure.repository.IDeviseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviseService {

    @Autowired
    IDeviseRepository deviseRepository;

    public DeviseService() {
    }

    public List<DeviseDTO> getDevises() {
        return DeviseMapper.toDTOList(deviseRepository.findAll());
    }
}
