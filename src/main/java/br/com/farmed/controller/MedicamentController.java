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

import br.com.farmed.entity.Medicament;
import br.com.farmed.service.CategoryService;
import br.com.farmed.service.MedicamentService;

@Controller
@RequestMapping("/medicamentos")
public class MedicamentController {

    @Autowired
    private MedicamentService medicamentService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("medicaments", medicamentService.listMedicaments());

        return "app/medicaments/list";
    }

    @GetMapping("criar")
    public String post(Model model) {
        model.addAttribute("medicament", new Medicament());
        model.addAttribute("categories", categoryService.listCategories());
        return "app/medicaments/form";
    }

    @PostMapping("salvar")
    public String store(@ModelAttribute Medicament medicament) {
        String medicamentSlug = this.medicamentService.saveMedicament(medicament);

        return "redirect:/medicamentos/editar/" + medicamentSlug;
    }

    @GetMapping("editar/{slug}")
    public String edit(@PathVariable("slug") String medicamentSlug, Model model) {
        Medicament medicament = medicamentService.findMedicamentBySlug(medicamentSlug);

        model.addAttribute("medicament", medicament);
        model.addAttribute("categories", categoryService.listCategories());

        return "app/medicaments/form";
    }

    @DeleteMapping("deletar/{slug}")
    public String destroy(@PathVariable("slug") String medicamentSlug) {
        medicamentService.deleteMedicament(medicamentSlug);
        return "app/medicaments/list";
    }
    
}
