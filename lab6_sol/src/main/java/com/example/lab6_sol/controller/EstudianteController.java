package com.example.lab6_sol.controller;

import com.example.lab6_sol.entity.Usuario;
import com.example.lab6_sol.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/lista")
    public String listaUsuarios(Model model){
        List<Usuario> estudiantes = usuarioRepository.findByRolid(5);
        model.addAttribute("estudiantes", estudiantes);
        return "lista_usuarios";
    }

    @GetMapping("/new")
    public String nuevo(@ModelAttribute("estudiante") @Valid Usuario usuario, BindingResult  bindingResult){
        System.out.println("entraa");
        return "newform";
    }

    @GetMapping("/edit")
    public String edit(Model model, @ModelAttribute("estudiante") @Valid Usuario usuario, BindingResult  bindingResult,@RequestParam("id") int id){
        Optional<Usuario> optProduct = usuarioRepository.findById(id);
        if (optProduct.isPresent()) {
            usuario = optProduct.get();
            model.addAttribute("usuario", usuario);
            return "newform";
        } else {
            return "redirect:/estudiante/lista";
        }
    }



    @GetMapping("/save")
    public String save(@ModelAttribute("estudiante") Usuario usuario){

        return "newform";
    }
}
