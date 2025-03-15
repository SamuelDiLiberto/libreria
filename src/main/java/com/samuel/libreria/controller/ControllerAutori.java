package com.samuel.libreria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.samuel.libreria.service.ServiceAutore;
import org.springframework.web.bind.annotation.GetMapping;

// @Controller perchè questa è una classe Controller

@Controller
public class ControllerAutori {

  // @Autowired perchè non ho messo @Data. se mettevo @Data al posto di @Autowired dovevo mettere le variabili final

  @Autowired
  private ServiceAutore serviceAutore;

  // @GetMapping perchè ricevo una richiesta get HTTP dalla URL /autori che io utente scrivo e gli rispondo caricandogli la pagina html

  @GetMapping("/autori")
  public String listaAutori(Model model){
    model.addAttribute("lista", serviceAutore.findAll());
    return "autori.html";
  }
}
