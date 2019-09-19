package com.byteworks.devops.repository;

import com.byteworks.devops.entities.FoodInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodInformationRepository extends JpaRepository<FoodInformation, Long> {
}
