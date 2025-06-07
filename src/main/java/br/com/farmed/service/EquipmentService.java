package br.com.farmed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.farmed.entity.Equipment;
import br.com.farmed.repository.EquipmentRepository;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private SlugService slugService;

    public String saveEquipment(Equipment equipment) {
        if (equipment.getSlugProduct() == null || equipment.getSlugProduct().isEmpty()) {
            equipment.setSlugProduct(slugService.slugify(equipment.getNameProduct()));
        }

        Equipment equipmentSaved = equipmentRepository.save(equipment);

        return equipmentSaved.getSlugProduct();
    }

    public List<Equipment> listEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment findEquipmentBySlug(String slug) {
        return equipmentRepository.findBySlugProduct(slug).orElse(null);
    }

    public void deleteEquipment(String slug) {
        equipmentRepository.deleteBySlugProduct(slug);
    }
    
}
