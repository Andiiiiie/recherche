package com.example.recherche.entity;

import com.example.recherche.service.ConnexionBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "produit")
public class Produit {
    @Column(name = "prix")
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @Column(name = "designation")
    private String designation;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "qualite")
    private Integer qualite;



    public static int get_indice(String requete)
    {
        List<String> list1= Arrays.asList("meilleur","prix");//1
        List<String> list2=Arrays.asList("moins","cher");//1
        List<String> list3=Arrays.asList("meilleur","rapport","qualite","prix");//2
        List<String> list4=Arrays.asList("pire","rapport","qualite" ,"prix");//3
        List<String> list11=Arrays.asList("pire","qualite" ,"prix");//3
        List<String> list12=Arrays.asList("meilleur","qualite" ,"prix");//2
        List<String> list5=Arrays.asList("pire","prix");//4
        List<String> list6=Arrays.asList("plus","cher");//4
        List<String> list7=Arrays.asList("meilleur","qualite");//5
        List<String> list8=Arrays.asList("plus","qualite");//5
        List<String> list9=Arrays.asList("pire","qualite");//6
        List<String> list10=Arrays.asList("moins","qualite");//6



        List<List<String>> l1=Arrays.asList(list1,list2);
        List<List<String>> l2=Arrays.asList(list3,list12);
        List<List<String>> l3=Arrays.asList(list4,list11);
        List<List<String>> l4=Arrays.asList(list5,list6);
        List<List<String>> l5=Arrays.asList(list7,list8);
        List<List<String>> l6=Arrays.asList(list9,list10);

        List<List<List<String>>> lists=Arrays.asList(l1,l2,l3,l4,l5,l6);

        int ind=-1;

            int co=-1;
            int temp=-1;
            for (List<List<String>> list:lists)
            {
                temp=get_co(requete,list);
                if(temp>co)
                {
                    co=temp;
                    ind=lists.indexOf(list);
                }
            }

        return ind;
    }

    public static Categorie get_categorie(String requete , ConnexionBase connexionBase)
    {
        Categorie categorie=null;
        List<Categorie> categories=connexionBase.getCategorieRepository().findAll();
        for (Categorie categorie1:categories)
        {
            if(requete.contains(categorie1.getDesignation()))
            {
                categorie=categorie1;
            }
        }
        return categorie;
    }

    public static List<Produit> get_liste(int indice_mot_cles,Categorie categorie,ConnexionBase connexionBase)
    {
        List<Produit> produits=null;
        if(indice_mot_cles==1)
        {
            if(categorie==null)
            {
                produits=connexionBase.getProduitRepository().findByCategorieOrderByPrixAsc();

            }
            else {
                produits=connexionBase.getProduitRepository().findByCategorieOrderByPrixAsc(categorie);

            }
        }
        else if(indice_mot_cles==2)
        {
            if(categorie==null)
            {
                produits=connexionBase.getProduitRepository().findByRapportAsc();

            }
            else {
                produits=connexionBase.getProduitRepository().findByRapportAsc(categorie);

            }
        }
        else if(indice_mot_cles==3)
        {
            if(categorie==null)
            {
                produits=connexionBase.getProduitRepository().findByRapportDesc();

            }
            else {
                produits=connexionBase.getProduitRepository().findByRapportDesc(categorie);
            }

        }
        else if(indice_mot_cles==4)
        {
            if(categorie==null)
            {
                produits=connexionBase.getProduitRepository().findByCategorieOrderByPrixDesc();
            }
            else {
                produits=connexionBase.getProduitRepository().findByCategorieOrderByPrixDesc(categorie);
            }
        }
        else if(indice_mot_cles==5)
        {
            if(categorie==null)
            {
                produits=connexionBase.getProduitRepository().findByCategorieOrderByQualiteDesc();
            }
            else {
                produits=connexionBase.getProduitRepository().findByCategorieOrderByQualiteDesc(categorie);
            }
        }
        else if (indice_mot_cles==6)
        {
            if(categorie==null)
            {
                produits=connexionBase.getProduitRepository().findByCategorieOrderByQualiteAsc();
            }
            else {
                produits=connexionBase.getProduitRepository().findByCategorieOrderByQualiteAsc(categorie);
            }
        }
        else
        {
            throw new RuntimeException("Aucune correspondance");
        }

        return produits;
    }

    public static int get_correspondance(String requete,List<String> liste)
    {
        int co=0;
        for (String s:liste)
        {
            if(requete.contains(s))
            {
                co+=1;
            }
        }
        if(co!=liste.size())
        {
            return -1;
        }
        return co;
    }

    public static int get_co(String requete,List<List<String>> liste)
    {
        int co=-1;
        int temp=0;
        for (List<String> list:liste)
        {
            temp=get_correspondance(requete,list);
            if(temp>co)
            {
                co=temp;
            }
        }
        return co;
    }


    public static List<Produit> recherche(String requete,ConnexionBase connexionBase)
    {
        requete=requete.toLowerCase();
        //get categorie
        Categorie categorie1=get_categorie(requete,connexionBase);
        //get mots clees
        int indice=get_indice(requete)+1;

        return  get_liste(indice,categorie1,connexionBase);
    }

}