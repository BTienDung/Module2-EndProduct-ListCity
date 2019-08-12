package com.codegym.service;

import com.codegym.model.City;

import java.util.List;

public interface CityService {
    void save(City city);
    City findById(Long id);
    void delete(Long id);
    Iterable<City> findAll();
}
