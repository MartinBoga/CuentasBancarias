package banco;

import java.util.*;
import cuentas.*;

public class Banco {

	public String sucursal;
	
    private List<Cliente> clientes = new ArrayList<>();
    private List<Cuenta> cuentas = new ArrayList<>();

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    //metodos
    public void agregarCuenta(Cuenta cuenta) {
        if (!existeCliente(cuenta.getDni())) {
            throw new RuntimeException("el cliente no existe");
        }
        cuentas.add(cuenta);
    }

    private boolean existeCliente(String dni) {
        return clientes.stream().anyMatch(c -> c.getDni().equals(dni));
    }

    public Cuenta buscarCuentaPorCbu(String cbu) {
        return cuentas.stream()
            .filter(c -> c.getCBU().equals(cbu))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("CBU no encontrado"));
    }

    public void depositar(String cbu, double monto) {
        buscarCuentaPorCbu(cbu).depositar(monto);
    }

    public void extraer(String cbu, double monto) throws SaldoInsuficienteException {
        buscarCuentaPorCbu(cbu).extraer(monto);
    }

    public void transferir(String cbuOrigen, String cbuDestino, double monto) throws SaldoInsuficienteException {
        Cuenta origen = buscarCuentaPorCbu(cbuOrigen);
        Cuenta destino = buscarCuentaPorCbu(cbuDestino);
        origen.extraer(monto);
        destino.depositar(monto);
    }

    public List<Cliente> obtenerClientesOrdenadosPorDni() {
        List<Cliente> copia = new ArrayList<>(clientes);
        Collections.sort(copia);
        return copia;
    }

    public List<Cuenta> obtenerCuentasOrdenadasPorSaldo() {
        List<Cuenta> copia = new ArrayList<>(cuentas);
        Collections.sort(copia);
        return copia;
    }

    public List<CuentaCorriente> obtenerCuentasCorrientesOrdenadasPorSaldo() {
        return cuentas.stream()
            .filter(c -> c instanceof CuentaCorriente)
            .map(c -> (CuentaCorriente) c)
            .sorted()
            .toList();
    }

    public List<CuentaCorriente> obtenerCuentasCorrientesDeudorasOrdenadas() {
        return cuentas.stream()
            .filter(c -> c instanceof CuentaCorriente)
            .map(c -> (CuentaCorriente) c)
            .filter(c -> c.getSaldo() < 0)
            .sorted()
            .toList();
    }

    // getters setters
	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
    
   
    /* test 9 public List<Cuenta> obtenerCuentasOrdenadasPorSaldo() {
    List<Cuenta> lista = new ArrayList<>(cuentas.values());
    lista.sort(Comparator.comparingDouble(Cuenta::getSaldo));
    return lista;
    }
}

     * 
     * */
    
    /*
     * test 10 
     * 
     * public List<CuentaCorriente> obtenerCuentasCorrientesOrdenadasPorSaldo() {
    return cuentas.values().stream()
        .filter(c -> c instanceof CuentaCorriente)
        .map(c -> (CuentaCorriente) c)
        .sorted(Comparator.comparingDouble(Cuenta::getSaldo))
        .toList();
}

     * 
     * */
    
    /*
     * test 11
     * 
     * public List<CuentaCorriente> obtenerCuentasCorrientesDeudorasOrdenadasPorSaldoDeudor() {
    return cuentas.values().stream()
        .filter(c -> c instanceof CuentaCorriente)
        .map(c -> (CuentaCorriente) c)
        .filter(c -> c.getSaldo() < 0)
        .sorted(Comparator.comparingDouble(Cuenta::getSaldo))
        .toList();
}

     * */
    
    
}


