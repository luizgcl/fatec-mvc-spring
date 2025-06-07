package br.com.farmed.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.farmed.entity.Medicament;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Long> {

    Optional<Medicament> findBySlugProduct(String slugProduct);

    void deleteBySlugProduct(String slugProduct);

}
