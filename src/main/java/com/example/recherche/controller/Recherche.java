package com.example.recherche.controller;

import com.example.recherche.entity.Produit;
import com.example.recherche.service.ConnexionBase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Getter
@Setter
public class Recherche {
    ConnexionBase connexionBase;

    public Recherche(ConnexionBase connexionBase) {
        this.connexionBase = connexionBase;
    }
    @GetMapping("/")
    public String start()
    {
        return "recherche";
    }

    @PostMapping("/recherche")
    public String recherche(Model model, HttpServletRequest request)
    {
        try {
            List<Produit> produits=Produit.recherche(request.getParameter("requete"),connexionBase);
            model.addAttribute("produits",produits);
        }catch (RuntimeException e)
        {
            model.addAttribute("error",e.getMessage());
        }

        return "recherche";
    }
}
