package com.Ciclo3.ProyectoArray.controller;

///a partri de este momento modificacmos el fullcontrller por el RESTcontroller Y RAMA controller_typeREST
//agregamos otro cambio antes de iniciar con el RESTcontroller
import com.Ciclo3.ProyectoArray.models.Empresa;
import com.Ciclo3.ProyectoArray.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
public class Controlador {
    @Autowired
    EmpresaService empresaService;

    //EMPRESAS
    @GetMapping("/enterprises") //Ver json de todas las empresas
    public List<Empresa> verEmpresas() {
        return empresaService.listarAllEmpresas();
    }

    @PostMapping("/enterprises") //Guardar el json del body como una nueva empresa o registro en nuestra bd
    public Empresa guardarEmpresa(@RequestBody Empresa emp){
        return this.empresaService.crearOactualizarEmpresa(emp);
    }

    @GetMapping(path = "enterprises/{id}")
    public Empresa empresaPorID(@PathVariable("id") Integer id){
        return this.empresaService.consultarEmpresaXId(id);
    }

    @PatchMapping("/enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa) {
        Empresa emp = empresaService.consultarEmpresaXId(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNIT(empresa.getNIT());
        return empresaService.crearOactualizarEmpresa(emp);
    }


    @DeleteMapping (path= "enterprises/{id}") //Eliminar registro de la bd
    public String DeleteEmpresa(@PathVariable("id") Integer id){
        boolean respuesta= this.empresaService.deleteEmpresa(id);
        if (respuesta){  //Si respuesta es true?
            return "Se elimino la empresa con id" +id;
        }
        else{
            return "No se pudo eliminar la empresa con id"+id;
        }
    }


    }
