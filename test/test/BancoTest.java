package test;

//solo guardar dni titular en la clase cuenta, corregir el test
//en el test reemplazar por agregarcuenta y le pasamos una cuenta con el dni adentro
// cbu y dni son string

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import banco.*;
import cuentas.*;

import java.util.List;

public class BancoTest{
	private Cliente cliente1;
    private Cliente cliente2;
    private Cliente cliente3;
    private Cliente cliente4;
    private Cliente cliente5;
    private Cliente cliente6;
    private Cliente cliente7;
    private CuentaSueldo cuentaSueldo2;
    private CuentaSueldo cuentaSueldo3;
    private CuentaSueldo cuentaSueldo4;
    private CuentaSueldo cuentaSueldo5;
    private CuentaSueldo cuentaSueldo6;
    private CajaDeAhorros cajaDeAhorros2;
    private CajaDeAhorros cajaDeAhorros3;
    private CajaDeAhorros cajaDeAhorros4;
    private CajaDeAhorros cajaDeAhorros5;
    private CajaDeAhorros cajaDeAhorros6;
    private CuentaCorriente cuentaCorriente2;
    private CuentaCorriente cuentaCorriente3;
    private CuentaCorriente cuentaCorriente4;
    private CuentaCorriente cuentaCorriente5;
    private CuentaCorriente cuentaCorriente6;
    private Cuenta cuentaSueldo;
    private Cuenta cajaDeAhorros;
    private Cuenta cuentaCorriente;
    private Banco banco;

    @Before
    public void setUp() throws ClienteDuplicadoException {
        cliente1 = new Cliente("12345678", "Juan Perez");
        cliente2 = new Cliente("87654321", "Maria Lopez");
        cliente3 = new Cliente("22334455", "Carlos Gómez");
        cliente4 = new Cliente("33445566", "Laura Fernández");
        cliente5 = new Cliente("44556677", "Roberto Díaz");
        cliente6 = new Cliente("55667788", "Sofía Ruíz");
        cliente7 = new Cliente("55667788", "Sofía Ruíz");
     
        
        cuentaSueldo = new CuentaSueldo("CBU001", cliente1, 2000.0);
        cajaDeAhorros = new CajaDeAhorros("CBU002", cliente1, 10000.0);
        cuentaCorriente = new CuentaCorriente("CBU003", cliente2, 500.0,0);
        cuentaSueldo2 = new CuentaSueldo("CBU001", cliente3, 3000.0);  // CBU duplicado
        cuentaSueldo2 = new CuentaSueldo("CBU004", cliente3, 3000.0);
        cuentaSueldo3 = new CuentaSueldo("CBU005", cliente4, 2500.0);
        cuentaSueldo4 = new CuentaSueldo("CBU006", cliente5, 0.0);
        cuentaSueldo5 = new CuentaSueldo("CBU007", cliente1, 2800.0);
        cuentaSueldo6 = new CuentaSueldo("CBU008", cliente6, 3200.0); // Nueva cuenta sueldo para el nuevo cliente

        cajaDeAhorros3 = new CajaDeAhorros("CBU010", cliente4, 6000.0);
        cajaDeAhorros4 = new CajaDeAhorros("CBU010", cliente5, 7000.0); // CBU duplicado       
        cajaDeAhorros6 = new CajaDeAhorros("CBU013", cliente6, 9000.0); // Nueva caja de ahorros para el nuevo cliente

        cuentaCorriente2 = new CuentaCorriente("CBU014", cliente3, 1000.0,0);
        cuentaCorriente5 = new CuentaCorriente("CBU006", cliente2, 800.0,0);
        cuentaCorriente6 = new CuentaCorriente("CBU018", cliente6, 2000.0,0);
        
        banco = new Banco();
  
            banco.agregarCliente(cliente1);
            banco.agregarCliente(cliente2);
            banco.agregarCliente(cliente3);
            banco.agregarCliente(cliente4);
            banco.agregarCliente(cliente5);
            banco.agregarCliente(cliente6);
        try {
            banco.agregarCliente(cliente7);
		} catch (ClienteDuplicadoException e) {
			
		}

        /* reemplazar con agregarcuenta en vez de dardealtacuenta y pasamos la cuenta
        try {
        	CuentaSueldo cuentaSueldo = new CuentaSueldo("CBU001", cliente1, 2000.0);        	
        	//cuentaSueldo.setDni(cliente1);
            //banco.agregarCuenta(cuentaSueldo);
            banco.agregarCuenta(cajaDeAhorros);
            banco.agregarCuenta(cuentaCorriente);
            banco.agregarCuenta(cuentaSueldo2); // CBU duplicado
            banco.agregarCuenta(cuentaSueldo3);
            banco.agregarCuenta(cuentaSueldo4);
            banco.agregarCuenta(cuentaSueldo5);
            banco.agregarCuenta(cuentaSueldo6);

            
            banco.agregarCuenta(cajaDeAhorros2);
            banco.agregarCuenta(cajaDeAhorros3);
            banco.agregarCuenta(cajaDeAhorros4); // CBU duplicado
            banco.agregarCuenta(cajaDeAhorros5);
            banco.agregarCuenta(cajaDeAhorros6);

            banco.agregarCuenta(cuentaCorriente2);
            banco.agregarCuenta(cuentaCorriente3);
            banco.agregarCuenta(cuentaCorriente4);
            banco.agregarCuenta(cuentaCorriente5);
            banco.agregarCuenta(cuentaCorriente6);
        } catch (ClienteInexistenteException e) {
            fail("Configuración inicial fallida: " + e.getMessage());
        }
        assertEquals(6 , banco.getCantidadClientes());
        assertEquals(12 , banco.getCantidadCuentas());
        */
    }
    
    //1
    @Test
    public void queSePuedaExtraer1000PesosDeUnaCuentaSueldoConSaldoIgualA2000Pesos() throws SaldoInsuficienteException {
        cuentaSueldo.extraer(1000);
        assertEquals(1000, cuentaSueldo.getSaldo(), 0.01);
    }
    
    //2
    @Test
    public void queNoSePuedaExtraer2500PesosDeUnaCuentaSueldoConSaldoIgualA2000Pesos() throws SaldoInsuficienteException {
        assertThrows(SaldoInsuficienteException.class, () -> {
            cuentaSueldo.extraer(2500);
        });
        assertEquals(2000, cuentaSueldo.getSaldo(), 0.01);
    }
    
    //3
    @Test
    public void queAlRealizar6ExtraccionesDe1000EnUnaCajaDeAhorroConSaldoInicialDe10000SuSaldoFinalSea3900() throws SaldoInsuficienteException {
        for (int i = 0; i < 6; i++) {
            cajaDeAhorros.extraer(1000);  // la 6ta tiene recargo de 100
        }
        assertEquals(3900, cajaDeAhorros.getSaldo(), 0.01);
    }
    
    //4
    @Test
    public void queAlRealizar7ExtraccionesDe1000EnUnaCajaDeAhorroConSaldoInicialDe7000SoloLaUltimaLanceExcepcionSaldoInsuficiente() throws SaldoInsuficienteException {
        // extrac valids
        for (int i = 0; i < 6; i++) {
            cajaDeAhorros4.extraer(1000); // la 6ta extrac ya incluye recargo 1100 si empieza justo ahi
        }
        // validar q saldo desp de 6 extrac sea 900
        assertEquals(900, cajaDeAhorros4.getSaldo(), 0.01);
        assertThrows(SaldoInsuficienteException.class, () -> {
            cajaDeAhorros4.extraer(1000); // la 7ma no tiene saldo suficiente
        });
    }
    
    //5
    @Test
    public void queSeCobreRecargoAlRealizarUnaExtraccionMayorAlSaldoEnUnaCuentaCorriente() {
        CuentaCorriente cuenta = new CuentaCorriente("123", cliente1, 100, 150);
        cuenta.extraer(200);
        assertEquals(-105, cuenta.getSaldo(), 0.01);
    }
    
    //6
    @Test
    public void queAlIntentarDarDeAltaUnaCuentaAUnClienteInexistenteLanceExcepcion() {
        Banco banco = new Banco();
        Cliente noExiste = new Cliente("1203920", "Martin Boga"); // creo cliente q no existe
        CuentaSueldo cuenta = new CuentaSueldo("CBU000", noExiste, 5000); // y su cuenta q no existe
        assertThrows(ClienteInexistenteException.class, () -> {
            banco.agregarCuenta(cuenta);
        });
    }
    
    //7
    @Test
    public void queAlBuscarUnaCuentaPorCBUErroneoLanceExcepcion() {
        Banco banco = new Banco();
        String cbuInvalido = "12093029310293019230910320139";
        assertThrows(RuntimeException.class, () -> {
            banco.buscarCuentaPorCbu(cbuInvalido);
        });
    }
   
    //8
    @Test
    public void queSeObtengaElListadoDeClientesOrdenadosPorDni() throws ClienteDuplicadoException {
        Banco banco = new Banco();
        Cliente c1= new Cliente("999", "Juan Perez");
        Cliente c2 = new Cliente("111", "Maria Lopez");
        Cliente c3 = new Cliente("555", "Carlos Gomez");
        
        banco.agregarCliente(c1);
        banco.agregarCliente(c2);
        banco.agregarCliente(c3);

        List<Cliente> clientesOrdenados = banco.obtenerClientesOrdenadosPorDni();
        
        assertEquals("111", clientesOrdenados.get(0).getDni());
        assertEquals("555", clientesOrdenados.get(1).getDni());
        assertEquals("999", clientesOrdenados.get(2).getDni());
    }
    
    //9
    @Test
    public void queSeObtengaUnListadoDeTodasLasCuentasOrdenadoPorSaldo() throws ClienteInexistenteException, ClienteDuplicadoException {
        Banco banco2 = new Banco();
        Cliente cliente1 = new Cliente("111", "cliente 1");
        Cliente cliente2 = new Cliente("222", "cliente 2");
        Cliente cliente3 = new Cliente("333", "cliente 3");
        
        banco2.agregarCliente(cliente1);
        banco2.agregarCliente(cliente2);
        banco2.agregarCliente(cliente3);

        Cuenta c1 = new CuentaSueldo("1001", cliente1, 3000);
        Cuenta c2 = new CajaDeAhorros("2002", cliente2, 1000);
        Cuenta c3 = new CuentaCorriente("3003", cliente3, 5000, 1000);
        
        banco2.agregarCuenta(c1);
        banco2.agregarCuenta(c2);
        banco2.agregarCuenta(c3);

        List<Cuenta> cuentasOrdenadas = banco2.obtenerCuentasOrdenadasPorSaldo();

        assertEquals("2002", cuentasOrdenadas.get(0).getCBU()); // saldo 1000
        assertEquals("1001", cuentasOrdenadas.get(1).getCBU()); // saldo 3000
        assertEquals("3003", cuentasOrdenadas.get(2).getCBU()); // saldo 5000
    }
    
    //10
    @Test
    public void queSeObtengaUnListadoDeCuentasCorrientesOrdenadoPorSaldo() throws ClienteInexistenteException, ClienteDuplicadoException {
        Banco banco3 = new Banco();
        Cliente cliente1 = new Cliente("1", "cliente 1");
        Cliente cliente2 = new Cliente("2", "cliente 2");
        Cliente cliente3 = new Cliente("3", "cliente 3");
        Cliente cliente4 = new Cliente("4", "cliente 4");
        banco3.agregarCliente(cliente1);
        banco3.agregarCliente(cliente2);
        banco3.agregarCliente(cliente3);
        banco3.agregarCliente(cliente4);
        
        CuentaCorriente cc1 = new CuentaCorriente("1", cliente1, 3000, 1000);
        CuentaCorriente cc2 = new CuentaCorriente("2", cliente2, 1000, 500);
        CuentaSueldo cs = new CuentaSueldo("3", cliente3, 5000);
        CajaDeAhorros ca = new CajaDeAhorros("4", cliente4, 2000);
        banco3.agregarCuenta(cc1);
        banco3.agregarCuenta(cc2);
        banco3.agregarCuenta(cs);
        banco3.agregarCuenta(ca);

        List<CuentaCorriente> cuentasCorrientes = banco3.obtenerCuentasCorrientesOrdenadasPorSaldo();

        assertEquals("2", cuentasCorrientes.get(0).getCBU());
        assertEquals("1", cuentasCorrientes.get(1).getCBU());
        assertEquals(2, cuentasCorrientes.size());
    }
    
    //11
    @Test
    public void queSeObtengaUnListadoDeCuentasCorrientesDeudorasOrdenadoPorSaldoDeudor() throws ClienteDuplicadoException, ClienteInexistenteException {
        Banco banco4 = new Banco();

        CuentaCorriente cc1 = new CuentaCorriente("1", cliente1, -1000, 1000);  // deuda -1k
        CuentaCorriente cc2 = new CuentaCorriente("2", cliente2, -2000, 1500); // deuda -2k
        CuentaCorriente cc3 = new CuentaCorriente("3", cliente3, 1000, 500);   // sin deuda
        CuentaSueldo cs = new CuentaSueldo("4", cliente4, 3000); // es otra cta no cc, no la deberia tomar

        banco4.agregarCliente(cliente1);
        banco4.agregarCliente(cliente2);
        banco4.agregarCliente(cliente3);
        banco4.agregarCliente(cliente4);

        banco4.agregarCuenta(cc1);
        banco4.agregarCuenta(cc2);
        banco4.agregarCuenta(cc3);
        banco4.agregarCuenta(cs);

        List<CuentaCorriente> cuentasDeudoras = banco4.obtenerCuentasCorrientesDeudorasOrdenadasPorSaldoDeudor();

        assertEquals(2, cuentasDeudoras.size());
        assertEquals("2", cuentasDeudoras.get(0).getCBU()); 
        assertEquals("1", cuentasDeudoras.get(1).getCBU());
    }
}