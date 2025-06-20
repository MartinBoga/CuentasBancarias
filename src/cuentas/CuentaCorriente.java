package cuentas;

import banco.*;

public class CuentaCorriente extends Cuenta {

    private double limiteDescubierto;

    public CuentaCorriente(String cbu, Cliente cliente, double saldoInicial, double limiteDescubierto) {
        super(cbu, cliente, saldoInicial);
        this.limiteDescubierto = limiteDescubierto;
    }

    @Override
    public void extraer(double monto) {
        double disponible = saldo + limiteDescubierto;

        if (monto <= disponible) {
            double descubiertoUsado = Math.max(0, monto - saldo);
            double comision = descubiertoUsado * 0.05;

            saldo -= monto;
            saldo -= comision;
        } else {
            throw new RuntimeException("saldo insuficiente");
        }
    }

    public double getLimiteDescubierto() {
        return limiteDescubierto;
    }
}
