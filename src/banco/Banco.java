package banco;

import java.util.*;
import cuentas.*;

public class Banco {
	public String sucursal;
    private List<Cliente> clientes = new ArrayList<>();
    private List<Cuenta> cuentas = new ArrayList<>();

    //metodos
    public void agregarCliente(Cliente cliente) throws ClienteDuplicadoException {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getDni().equals(cliente.getDni())) {
                throw new ClienteDuplicadoException("cliente duplicado");
            }
        }
        clientes.add(cliente);
    }

    private boolean existeCliente(String dni) {
        for (Cliente c : clientes) {
            if (c.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }
    
    public void agregarCuenta(Cuenta cuenta) throws ClienteInexistenteException {
        if (!existeCliente(cuenta.getDni())) {
            throw new ClienteInexistenteException("cliente inexistente");
        }
        cuentas.add(cuenta);
    }

    public Cuenta buscarCuentaPorCbu(String cbu) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getCBU().equals(cbu)) {
                return cuenta;
            }
        }
        throw new RuntimeException("no se encontro el CBU");
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

    //metodo para el test 8
    public List<Cliente> obtenerClientesOrdenadosPorDni() {
        List<Cliente> copia = new ArrayList<>(clientes);
        Collections.sort(copia);
        return copia;
    }

    //metodo para el test 9
    public List<Cuenta> obtenerCuentasOrdenadasPorSaldo() {
        List<Cuenta> copia = new ArrayList<>(cuentas);
        Collections.sort(copia);
        return copia;
    }

    //metodo para test 10
    public List<CuentaCorriente> obtenerCuentasCorrientesOrdenadasPorSaldo() {
        List<CuentaCorriente> lista = new ArrayList<>();
        for (Cuenta c : cuentas) {
            if (c instanceof CuentaCorriente) {
                lista.add((CuentaCorriente) c);
            }
        }
        Collections.sort(lista);
        return lista;
    }

    //test 11
    public List<CuentaCorriente> obtenerCuentasCorrientesDeudorasOrdenadasPorSaldoDeudor() {
        List<CuentaCorriente> deudoras = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            if (cuenta instanceof CuentaCorriente) {
                CuentaCorriente cc = (CuentaCorriente) cuenta;
                if (cc.getSaldo() < 0) {
                    deudoras.add(cc);
                }
            }
        }
        Collections.sort(deudoras);
        return deudoras;
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
    
	public int getCantidadClientes() {
	    return clientes.size();
	}

	public int getCantidadCuentas() {
	    return cuentas.size();
	}
}