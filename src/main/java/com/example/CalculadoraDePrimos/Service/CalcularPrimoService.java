package com.example.CalculadoraDePrimos.Service;

import com.example.CalculadoraDePrimos.Model.Proceso;
import com.example.CalculadoraDePrimos.Repository.ProcesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CalcularPrimoService {

    private final AtomicInteger id_actual = new AtomicInteger(1);

    @Autowired
    private ProcesoRepository procesoRepository;

    @Autowired
    private TareaAsincrona tareaAsincrona;

    public int generarProceso(int numero_cifras) {
        int id = id_actual.getAndIncrement();
        Proceso proceso = new Proceso(id, numero_cifras);
        proceso.setEstado("Calculando");
        procesoRepository.save(proceso);

        tareaAsincrona.calcularEnSegundoPlano(proceso); // llamado asíncrono correcto
        return id;
    }

    public Optional<Proceso> obtenerProceso(int id) {
        return procesoRepository.findById(String.valueOf(id));
    }

    public String listarProcesos() {
        List<Proceso> procesos = procesoRepository.findAll();

        StringBuilder html = new StringBuilder("<h2>Lista de procesos</h2><ul>");
        for (Proceso p : procesos) {
            html.append("<li>")
                    .append("ID: ").append(p.getId())
                    .append(" | Cifras: ").append(p.getNumeroCifras())
                    .append(" | Estado: ").append(p.getEstado())
                    .append(" | Resultado: ").append(p.getResultado() != null ? p.getResultado() : "")
                    .append("</li>");
        }
        html.append("</ul>");
        return html.toString();
    }

}
