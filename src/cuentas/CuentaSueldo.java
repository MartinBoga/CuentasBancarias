package cuentas;

import banco.*;

public class CuentaSueldo extends Cuenta {
    public CuentaSueldo(String cbu, Cliente cliente, double saldoInicial) {
        super(cbu, cliente, saldoInicial);
    }

    @Override
    public void extraer(double monto) throws SaldoInsuficienteException {
        if (saldo >= monto) {
            saldo -= monto;
        } else {
            throw new SaldoInsuficienteException("saldo insuficiente");
        }
    }
}