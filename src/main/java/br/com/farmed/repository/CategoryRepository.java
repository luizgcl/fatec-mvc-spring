package br.com.farmed.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.farmed.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
    Optional<Category> findBySlugCategory(String slugCategory);

    void deleteBySlugCategory(String slugCategory);

}
