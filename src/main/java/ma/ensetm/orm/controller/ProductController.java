package ma.ensetm.orm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensetm.orm.model.Product;
import ma.ensetm.orm.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    
    @GetMapping
    public String listeProduits(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
                                 Model model) {
        model.addAttribute("produits",
                keyword.isBlank()
                        ? productRepository.findAll()
                        : productRepository.findByNameContainingIgnoreCase(keyword));
        model.addAttribute("keyword", keyword);
        return "products/list"; 
    }

    
    @GetMapping("/new")
    public String formulaireAjout(Model model) {
        model.addAttribute("product", new Product());
        return "products/form";
    }

    
    @GetMapping("/edit/{id}")
    public String formulaireEdition(@PathVariable Long id, Model model) {
        Product produit = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit introuvable, id=" + id));
        model.addAttribute("product", produit);
        return "products/form";
    }

    
    @PostMapping("/save")
    public String enregistrerProduit(@Valid @ModelAttribute("product") Product product,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            
            return "products/form";
        }
        productRepository.save(product);
        return "redirect:/products";
    }

    
    @GetMapping("/delete/{id}")
    public String supprimerProduit(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

}
