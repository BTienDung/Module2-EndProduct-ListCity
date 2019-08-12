package com.codegym.service.impl;

import com.codegym.model.City;
import com.codegym.repository.CityRepository;
import com.codegym.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        City city = cityRepository.findOne(id);
        cityRepository.delete(city);
    }

    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

}
