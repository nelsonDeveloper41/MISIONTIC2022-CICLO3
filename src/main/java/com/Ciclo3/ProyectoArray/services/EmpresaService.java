package com.Ciclo3.ProyectoArray.services;

import com.Ciclo3.ProyectoArray.models.Empresa;
import com.Ciclo3.ProyectoArray.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired //Conectamos esta clase con el repository de Empresa
    EmpresaRepository empresaRepository;  //Creamos un objeto llamdo empresaRepository, para poder usar los metodos del JpaRepository

    //Metodo que retorna la lista de empresas estos metodos se heredaron de JpaRepository
    public List<Empresa> listarAllEmpresas(){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa)); //Recorremos el iterable que regresa el metoso findAll de Jpa y los guardamos en una lista
        return empresaList;
    }

    //Metodo que me trae un objeto de tipo Empresa cuando cuento con el id de la misma
    public Empresa consultarEmpresaXId(Integer id){
        return empresaRepository.findById(id).get();
    }

    //Metodo para guardar o actualizar objetos de tipo Empresa
    public boolean crearOactualizarEmpresa(Empresa empresa){
        Empresa emp=empresaRepository.save(empresa);
        if (empresaRepository.findById(emp.getId())!=null){
            return true;
        }
        return false;
    }

    //Metodo para eliminar empresas registradas teniendo el id
    public boolean deleteEmpresa(Integer id){
        empresaRepository.deleteById(id);  //Eliminar

        if (empresaRepository.findById(id)!=null){  //Verificacion del servicio eliminacion
            return true;
        }
        return false;
    }
}
