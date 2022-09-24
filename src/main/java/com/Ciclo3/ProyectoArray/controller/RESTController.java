package com.Ciclo3.ProyectoArray.controller;

import com.Ciclo3.ProyectoArray.models.MovimientoDinero;
import com.Ciclo3.ProyectoArray.repository.MovimientosRepository;
import com.Ciclo3.ProyectoArray.services.EmpleadoService;
import com.Ciclo3.ProyectoArray.services.EmpresaService;
import com.Ciclo3.ProyectoArray.services.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class RESTController {

    @Autowired
    EmpresaService empresaService;
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    MovimientosService movimientosService;

    @Autowired
    MovimientosRepository movimientosRepositor;


    /*INICIO MOVIMIENTO DINERO***************************************************************************************
     *************************************************************************************************
     * **********************************************************************************************
     */
    @GetMapping("/GrafAllMovEmpresas") //Consultar todos los movimientos
    public List<MovimientoDinero> grafMovimientos(){
        return movimientosService.getAllMovimientos();
    }


    @GetMapping("/GrafEmpleado/{id}/Mov") //Consultar movimientos por id del empleado
    public ArrayList<MovimientoDinero> grafMovEmpleado(@PathVariable("id") Integer id){
        return movimientosService.obtenerPorEmpleado(id);
    }

    @GetMapping("/GrafEmpresa/{id}/Mov") //Consultar movimientos que pertenecen a una empresa por el id de la empresa
    public ArrayList<MovimientoDinero> grafMovEmpresa(@PathVariable("id") Integer id){
        return movimientosService.obtenerPorEmpresa(id);
    }
    /*FINAL MOVIMIENTO DINERO***************************************************************************************
     *************************************************************************************************
     * **********************************************************************************************
     */


}
