package br.com.farmed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.farmed.entity.Medicament;
import br.com.farmed.repository.MedicamentRepository;

@Service
public class MedicamentService {
    
    @Autowired
    private MedicamentRepository medicamentRepository;

    @Autowired
    private SlugService slugService;

    public String saveMedicament(Medicament medicament) {
        if (medicament.getSlugProduct() == null || medicament.getSlugProduct().isEmpty()) {
            medicament.setSlugProduct(slugService.slugify(medicament.getNameProduct()));
        }

        Medicament medicamentSaved = medicamentRepository.save(medicament);
        
        return medicamentSaved.getSlugProduct();
    }

    public List<Medicament> listMedicaments() {
        return medicamentRepository.findAll();
    }

    public Medicament findMedicamentBySlug(String slug) {
        return medicamentRepository.findBySlugProduct(slug).orElse(null);
    }

    public void deleteMedicament(String slug) {
        medicamentRepository.deleteBySlugProduct(slug);
    }
  
}
