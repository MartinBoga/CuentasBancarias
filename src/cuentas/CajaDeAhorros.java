package cuentas;

import banco.*;

public class CajaDeAhorros extends Cuenta {

    private int cantidadExtracciones = 0;

    public CajaDeAhorros(String cbu, Cliente cliente, double saldoInicial) {
        super(cbu, cliente, saldoInicial);
    }

    @Override
    public void extraer(double monto) {
        double recargo = (cantidadExtracciones >= 5) ? 100 : 0;
        double total = monto + recargo;

        if (saldo >= total) {
            saldo -= total;
            cantidadExtracciones++;
        } else {
            throw new RuntimeException("saldo insuficiente");
        }
    }

    public int getCantidadExtracciones() {
        return cantidadExtracciones;
    }
}
