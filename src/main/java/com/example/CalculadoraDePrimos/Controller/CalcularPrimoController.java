package com.example.CalculadoraDePrimos.Controller;

import com.example.CalculadoraDePrimos.Model.Proceso;
import com.example.CalculadoraDePrimos.Service.CalcularPrimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


public class CalcularPrimoController {

    @Autowired
    CalcularPrimoService calcularPrimoService;

    @GetMapping("/servicio/{numero_cifras}")
    public String generarPrimo(@PathVariable int numero_cifras) {
        int id = calcularPrimoService.generarProceso(numero_cifras);
        return "<h2>Proceso generado con id #" + id + "</h2><p>Estado: Calculando...</p>" +
                "<p>Consultar resultado: <a href=\"/servicio/status/" + id + "\" target=\"_blank\">/servicio/status/" + id + "</a></p>";
    }

    @GetMapping("/servicio/status/{id}")
    public String verProceso(@PathVariable int id) {
        Optional<Proceso> procesoOpt = calcularPrimoService.obtenerProceso(id);
        if (procesoOpt.isPresent()) {
            Proceso proceso = procesoOpt.get();
            String resultado = proceso.getResultado() != null ? proceso.getResultado().toString() : "";
            return "<h2>Proceso #" + proceso.getId() + "</h2>" +
                    "<p>Estado: " + proceso.getEstado() + "</p>" +
                    "<p>Resultado: " + resultado + "</p>";
        } else {
            return "<h2>Proceso no encontrado.</h2>";
        }
    }

    @GetMapping("/servicio/status")
    public String listarProcesos() {
        return calcularPrimoService.listarProcesos();
    }
}
