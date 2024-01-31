package com.example.recherche.repository;

import com.example.recherche.entity.Categorie;
import com.example.recherche.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    @Query("select p from Produit p where p.categorie = ?1 order by p.prix")
    List<Produit> findByCategorieOrderByPrixAsc(Categorie categorie);

    @Query("select p from Produit p where p.categorie = ?1 order by p.prix DESC ")
    List<Produit> findByCategorieOrderByPrixDesc(Categorie categorie);

    @Query("select p from Produit p where p.categorie = ?1 order by p.qualite")
    List<Produit> findByCategorieOrderByQualiteAsc(Categorie categorie);

    @Query("select p from Produit p where p.categorie = ?1 order by p.qualite desc ")
    List<Produit> findByCategorieOrderByQualiteDesc(Categorie categorie);

    @Query("select p from Produit p where p.categorie = ?1 order by p.prix/p.qualite")
    List<Produit> findByRapportAsc(Categorie categorie);

    @Query("select p from Produit p where p.categorie = ?1 order by p.prix/p.qualite DESC ")
    List<Produit> findByRapportDesc(Categorie categorie);



    //sans categorie
    @Query("select p from Produit p  order by p.prix")
    List<Produit> findByCategorieOrderByPrixAsc();

    @Query("select p from Produit p  order by p.prix DESC ")
    List<Produit> findByCategorieOrderByPrixDesc();

    @Query("select p from Produit p  order by p.qualite")
    List<Produit> findByCategorieOrderByQualiteAsc();

    @Query("select p from Produit p  order by p.qualite desc ")
    List<Produit> findByCategorieOrderByQualiteDesc();

    @Query("select p from Produit p  order by p.prix/p.qualite")
    List<Produit> findByRapportAsc();

    @Query("select p from Produit p  order by p.prix/p.qualite DESC ")
    List<Produit> findByRapportDesc();


}