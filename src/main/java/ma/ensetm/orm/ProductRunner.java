package ma.ensetm.orm;

import lombok.RequiredArgsConstructor;
import ma.ensetm.orm.model.Product;
import ma.ensetm.orm.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRunner implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        // 1. Ajouter des produits
        productRepository.save(new Product(null, "Ordinateur portable", 8500.0, 12));
        productRepository.save(new Product(null, "Souris sans fil", 120.0, 50));
        productRepository.save(new Product(null, "Clavier mécanique", 350.0, 30));
        productRepository.save(new Product(null, "Écran 24 pouces", 1400.0, 8));
        System.out.println(">>> Produits ajoutés avec succès");

        // 2. Consulter tous les produits
        List<Product> produits = productRepository.findAll();
        System.out.println("\n>>> Liste de tous les produits :");
        produits.forEach(System.out::println);

        // 3. Consulter un produit par id
        Long idRecherche = produits.get(0).getId();
        Optional<Product> produit = productRepository.findById(idRecherche);
        System.out.println("\n>>> Produit avec id=" + idRecherche + " :");
        produit.ifPresent(System.out::println);

        // 4. Chercher des produits (par nom, par prix, par quantité)
        System.out.println("\n>>> Recherche par nom contenant 'clavier' :");
        productRepository.findByNameContainingIgnoreCase("clavier")
                .forEach(System.out::println);

        System.out.println("\n>>> Recherche des produits avec prix > 1000 :");
        productRepository.findByPriceGreaterThan(1000)
                .forEach(System.out::println);

        System.out.println("\n>>> Recherche des produits avec quantité < 10 :");
        productRepository.findByQuantityLessThan(10)
                .forEach(System.out::println);

        // 5. Mettre à jour un produit
        Product produitAModifier = produits.get(1);
        produitAModifier.setPrice(99.0);
        produitAModifier.setQuantity(45);
        productRepository.save(produitAModifier);
        System.out.println("\n>>> Produit mis à jour : " + produitAModifier);

        // 6. Supprimer un produit
        Long idASupprimer = produits.get(3).getId();
        productRepository.deleteById(idASupprimer);
        System.out.println("\n>>> Produit avec id=" + idASupprimer + " supprimé");

        // Vérification finale
        System.out.println("\n>>> Liste finale des produits :");
        productRepository.findAll().forEach(System.out::println);
    }
}
