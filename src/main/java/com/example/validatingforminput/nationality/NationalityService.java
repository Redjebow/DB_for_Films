package com.example.validatingforminput.nationality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NationalityService {
    @Autowired
    NationalityRepository nationalityRepository;
    public void deleteNationality(Long id) {
        nationalityRepository.deleteById(id);
    }
}
