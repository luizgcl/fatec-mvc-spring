package br.com.farmed.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.farmed.entity.Equipment;


@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findBySlugProduct(String slugProduct);

    void deleteBySlugProduct(String slugProduct);
    
}
