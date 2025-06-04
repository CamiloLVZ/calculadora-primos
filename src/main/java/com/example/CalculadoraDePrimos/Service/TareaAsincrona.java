package com.example.CalculadoraDePrimos.Service;

import com.example.CalculadoraDePrimos.Model.Proceso;
import com.example.CalculadoraDePrimos.Repository.ProcesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class TareaAsincrona {
    @Autowired
    private ProcesoRepository procesoRepository;

    @Async
    public void calcularEnSegundoPlano(Proceso proceso) {
        BigInteger primo = calcularPrimo(proceso.getNumeroCifras());

        if (primo.equals(BigInteger.ZERO)) {
            proceso.setEstado("No encontrado");
        } else {
            proceso.setEstado("Completado");
        }

        proceso.setResultado(primo);
        procesoRepository.save(proceso);
    }

    public BigInteger calcularPrimo(int numero_cifras) {

        if (numero_cifras <= 0) return BigInteger.ZERO;

        BigInteger inicio = BigInteger.TEN.pow(numero_cifras - 1);
        BigInteger fin = BigInteger.TEN.pow(numero_cifras).subtract(BigInteger.ONE);

        boolean esDivisible;

        for (BigInteger posible_primo = inicio;
             posible_primo.compareTo(fin) <= 0;
             posible_primo = posible_primo.add(BigInteger.ONE)) {

            esDivisible = false;

            for (BigInteger divisor = BigInteger.valueOf(2);
                 divisor.compareTo(posible_primo) < 0;
                 divisor = divisor.add(BigInteger.ONE)) {

                if (posible_primo.mod(divisor).equals(BigInteger.ZERO)) {
                    esDivisible = true;
                    break;
                }
            }
            if (!esDivisible) return posible_primo;
        }
        return BigInteger.ZERO;
    }
}
