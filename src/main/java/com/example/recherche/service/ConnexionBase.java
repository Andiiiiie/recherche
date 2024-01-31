package com.example.recherche.service;

import com.example.recherche.entity.Categorie;
import com.example.recherche.entity.Produit;
import com.example.recherche.repository.CategorieRepository;
import com.example.recherche.repository.ProduitRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter
public class ConnexionBase {
    CategorieRepository categorieRepository;
    ProduitRepository produitRepository;

    public ConnexionBase(CategorieRepository categorieRepository, ProduitRepository produitRepository) {
        this.categorieRepository = categorieRepository;
        this.produitRepository = produitRepository;
    }
}
