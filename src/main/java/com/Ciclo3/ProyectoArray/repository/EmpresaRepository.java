package com.Ciclo3.ProyectoArray.repository;

import com.Ciclo3.ProyectoArray.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
}
