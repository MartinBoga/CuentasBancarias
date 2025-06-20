package cuentas;

import banco.*;

public class CajaDeAhorros extends Cuenta {

    private int cantidadExtracciones = 0;

    public CajaDeAhorros(String cbu, Cliente cliente, double saldoInicial) {
        super(cbu, cliente, saldoInicial);
    }

    @Override
    public void extraer(double monto) throws SaldoInsuficienteException {
        double montoTotal = monto;
        cantidadExtracciones++;

        if (cantidadExtracciones > 5) {
            montoTotal += 100;
        }

	        if (saldo >= montoTotal) {
	            saldo -= montoTotal;
	        } else {
	            throw new SaldoInsuficienteException("saldo insuficiente");
	        }
    }
    
    public int getCantidadExtracciones() {
        return cantidadExtracciones;
    }
}
