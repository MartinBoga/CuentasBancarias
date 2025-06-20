package cuentas;

import banco.*;

public abstract class Cuenta implements Comparable<Cuenta> {
    protected String cbu;
    protected Cliente cliente;
    protected double saldo;

    public Cuenta(String cbu, Cliente cliente, double saldoInicial) {
        this.cbu = cbu;
        this.cliente = cliente;
        this.saldo = saldoInicial;
    }

    // metodos
    public void depositar(double monto) {
        saldo += monto;
    }

    public abstract void extraer(double monto) throws SaldoInsuficienteException;;

    public String getCBU() {
        return cbu;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return cliente;
    }

    public String getDni() {
        return cliente.getDni();
    }

    // orden x cbu
    @Override
    public int compareTo(Cuenta otra) {
        return this.cbu.compareTo(otra.cbu);
    }
}