package br.com.farmed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.farmed.entity.Category;
import br.com.farmed.service.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/categorias")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("categories", categoryService.listCategories());

        return "app/categories/list";
    }

    @GetMapping("criar")
    public String post(Model model) {
        model.addAttribute("category", new Category());
        return "app/categories/form";
    }

    @PostMapping("salvar")
    public String store(@ModelAttribute Category category) {
        String categorySlug = this.categoryService.saveCategory(category);

        return "redirect:/categorias/editar/" + categorySlug;
    }

    @GetMapping("editar/{slug}")
    public String edit(@PathVariable("slug") String categorySlug, Model model) {
        Category category = categoryService.findCategoryBySlug(categorySlug);

        model.addAttribute("category", category);

        return "app/categories/form";
    }

    @DeleteMapping("deletar/{slug}")
    public String destroy(@PathVariable("slug") String categorySlug) {
        categoryService.deleteCategory(categorySlug);
        return "app/categories/list";
    }

}
