package com.example.CalculadoraDePrimos.Repository;

import com.example.CalculadoraDePrimos.Model.Proceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesoRepository extends JpaRepository<Proceso, String> {
}
