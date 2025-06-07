package br.com.farmed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.farmed.entity.Equipment;
import br.com.farmed.service.CategoryService;
import br.com.farmed.service.EquipmentService;

@Controller
@RequestMapping("/equipamentos")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("equipments", equipmentService.listEquipments());

        return "app/equipments/list";
    }

    @GetMapping("criar")
    public String post(Model model) {
        model.addAttribute("equipment", new Equipment());
        model.addAttribute("categories", categoryService.listCategories());
        return "app/equipments/form";
    }

    @PostMapping("salvar")
    public String store(@ModelAttribute Equipment equipment) {
        String equipmentSlug = this.equipmentService.saveEquipment(equipment);

        return "redirect:/equipamentos/editar/" + equipmentSlug;
    }

    @GetMapping("editar/{slug}")
    public String edit(@PathVariable("slug") String equipmentSlug, Model model) {
        Equipment equipment = equipmentService.findEquipmentBySlug(equipmentSlug);

        model.addAttribute("equipment", equipment);
        model.addAttribute("categories", categoryService.listCategories());

        return "app/equipments/form";
    }

    @DeleteMapping("deletar/{slug}")
    public String destroy(@PathVariable("slug") String equipmentSlug) {
        equipmentService.deleteEquipment(equipmentSlug);
        return "app/equipments/list";
    }
    
}
