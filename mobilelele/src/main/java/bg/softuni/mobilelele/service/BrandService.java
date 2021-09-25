package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.BrandEntity;

public interface BrandService {

    void initializeBrand();

    BrandEntity findByName(String name);
}
