package com.Ciclo3.ProyectoArray.controller;

///a partri de este momento modificacmos el fullcontrller por el RESTcontroller Y RAMA controller_typeREST
import com.Ciclo3.ProyectoArray.models.Empresa;
import com.Ciclo3.ProyectoArray.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class Controlador {
    @Autowired
    EmpresaService objempServicio;

    @GetMapping ({"/","/VerEmpresas"})
    public String verEmpresas(Model model){
        List<Empresa> listaEmpresas=objempServicio.listarAllEmpresas();
        model.addAttribute("empLista", listaEmpresas);
        return "veoEmpresas"; ///para llamar la vista cargando  la plantilla HTML
    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model){
        Empresa obj_empresa= new Empresa();
        model.addAttribute("empNueva",obj_empresa);
        return "agregarEmpresa"; ///para llamar la vista cargando  la plantilla HTML
    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes) {
        if (objempServicio.crearOactualizarEmpresa(emp) == true) {
            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
        return "redirect:/AgregarEmpresa";
    }

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empresa emp=objempServicio.consultarEmpresaXId(id);
        //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
        model.addAttribute("empeditada",emp);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(@ModelAttribute("empeditada") Empresa emp){
        if(objempServicio.crearOactualizarEmpresa(emp)){
            return "redirect:/VerEmpresas";
        }
        return "redirect:/EditarEmpresa";
    }

    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Integer id){
        try {
            objempServicio.borrarEmpresa(id);
        } catch (Exception e){
            return "redirect:/VerEmpresas";
        }
        return "redirect:/VerEmpresas";
    }
}
