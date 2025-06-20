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

public class BancoTest {
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

        //reemplazar con agregarcuenta en vez de dardealtacuenta y pasamos la cuenta
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
        
    }
    
    //1
    @Test
    public void queSePuedaExtraer1000PesosDeUnaCuentaSueldoConSaldoIgualA2000Pesos() throws SaldoInsuficienteException {
        Cliente cliente = new Cliente("12345678", "Juan Pérez");
        CuentaSueldo cuenta = new CuentaSueldo("CBU123", cliente, 2000.0);

        cuenta.extraer(1000.0);

        assertEquals(1000.0, cuenta.getSaldo(), 0.01);
    }
    
    //2
    
    @Test
    public void queNoSePuedaExtraer2500PesosDeUnaCuentaSueldoConSaldoIgualA2000Pesos() {
        CuentaSueldo cuenta = new CuentaSueldo("CBU999", cliente1, 2000);

        assertThrows(Exception.class, () -> {
            cuenta.extraer(2500);
        });

        assertEquals(2000, cuenta.getSaldo(), 0.01);
    }
    
    //3
    
    @Test
    public void queAlRealizar6ExtraccionesDe1000EnUnaCajaDeAhorroConSaldoInicialDe10000SuSaldoFinalSea3900() {
   
        CajaDeAhorros cuenta = new CajaDeAhorros("CBU456", cliente1, 10000);

        for (int i = 0; i < 6; i++) {
            cuenta.extraer(1000);  // la 6ta tiene recargo de 100
        }

        assertEquals(3900, cuenta.getSaldo(), 0.01);
    }
    
    //4
    @Test
    public void queAlRealizar7ExtraccionesDe1000EnUnaCajaDeAhorroConSaldoInicialDe7000SoloLaUltimaLanceExcepcionSaldoInsuficiente() {

        CajaDeAhorros cuenta = new CajaDeAhorros("CBU777", cliente1, 7000);

        // extracciones valid
        for (int i = 0; i < 6; i++) {
            cuenta.extraer(1000); // la 6ta extrac ya incluye recargo 1100 si empieza justo ahi
        }

        assertThrows(RuntimeException.class, () -> {
            cuenta.extraer(1000); // la 7ma no tiene saldo suficiente
        });

        // validar q saldo desp de 6 extrac sea 900
        assertEquals(900, cuenta.getSaldo(), 0.01);
    }
    
    //5
    @Test
    public void queSeCobreRecargoAlRealizarUnaExtraccionMayorAlSaldoEnUnaCuentaCorriente() {
        CuentaCorriente cuenta = new CuentaCorriente("CBU123", cliente1, 100, 150); // saldo: 100, descubierto: 150
        cuenta.extraer(200); // se extrae 200: 100 del saldo, 100 del descubierto → fee: 5

        assertEquals(-105, cuenta.getSaldo(), 0.01); // 100 (descubierto) + 5 (fee)
    }
    
    //6
    @Test
    public void queAlIntentarDarDeAltaUnaCuentaAUnClienteInexistenteLanceExcepcion() {
        Banco banco = new Banco(); // banco sin clientes
        CuentaSueldo cuenta = new CuentaSueldo("CBU000", cliente1, 5000); // cliente con DNI 11111111 no existe
        assertThrows(RuntimeException.class, () -> {
            banco.agregarCuenta(cuenta);
        });
    }
    
    //7
    /*
    @Test
    public void queAlBuscarUnaCuentaPorCBUErroneoLanceExcepcion() {
        // Arrange
        Banco banco = new Banco(); // banco sin ctas
        /* crear excepcion: public Cuenta obtenerCuentaPorCBU(String cbu) {
    if (!cuentas.containsKey(cbu)) {
        throw new RuntimeException("Cuenta no encontrada con ese CBU.");
    }
    return cuentas.get(cbu);
}
        String cbuInvalido = "CBU_INEXISTENTE";
        assertThrows(RuntimeException.class, () -> {
            banco.obtenerCuentaPorCBU(cbuInvalido);
        });
    }*/
   
    //8
    @Test
    public void queSeObtengaElListadoDeClientesOrdenadosPorDni() {
        Banco banco = new Banco();
        Cliente c1 = new Cliente("99999999", "Juan Perez");
        Cliente c2 = new Cliente("11111111", "Ana Gomez");
        Cliente c3 = new Cliente("55555555", "Luis Torres");

        banco.agregarCliente(c1);
        banco.agregarCliente(c2);
        banco.agregarCliente(c3);

        List<Cliente> clientesOrdenados = banco.obtenerClientesOrdenadosPorDni();
        
        assertEquals("11111111", clientesOrdenados.get(0).getDni());
        assertEquals("55555555", clientesOrdenados.get(1).getDni());
        assertEquals("99999999", clientesOrdenados.get(2).getDni());
    }
    
    //9
    @Test
    public void queSeObtengaUnListadoDeTodasLasCuentasOrdenadoPorSaldo() {
        Banco banco = new Banco();

        Cuenta c1 = new CuentaSueldo("CBU001", cliente1, 3000);
        Cuenta c2 = new CajaDeAhorros("CBU002", cliente2, 1000);
        Cuenta c3 = new CuentaCorriente("CBU003", cliente3, 5000, 1000);

        banco.agregarCliente(new Cliente("11111111", "Cliente 1"));
        banco.agregarCliente(new Cliente("22222222", "Cliente 2"));
        banco.agregarCliente(new Cliente("33333333", "Cliente 3"));

        banco.agregarCuenta(c1);
        banco.agregarCuenta(c2);
        banco.agregarCuenta(c3);

        List<Cuenta> cuentasOrdenadas = banco.obtenerCuentasOrdenadasPorSaldo();

        assertEquals("CBU002", cuentasOrdenadas.get(0).getCBU()); // saldo 1000
        assertEquals("CBU001", cuentasOrdenadas.get(1).getCBU()); // saldo 3000
        assertEquals("CBU003", cuentasOrdenadas.get(2).getCBU()); // saldo 5000
    }
    
    //10
    @Test
    public void queSeObtengaUnListadoDeCuentasCorrientesOrdenadoPorSaldo() {
        Banco banco = new Banco();

        CuentaCorriente cc1 = new CuentaCorriente("CBU001", cliente1, 3000, 1000);
        CuentaCorriente cc2 = new CuentaCorriente("CBU002", cliente2, 1000, 500);
        CuentaSueldo cs = new CuentaSueldo("CBU003", cliente3, 5000);
        CajaDeAhorros ca = new CajaDeAhorros("CBU004", cliente4, 2000);

        banco.agregarCliente(new Cliente("11111111", "Cliente 1"));
        banco.agregarCliente(new Cliente("22222222", "Cliente 2"));
        banco.agregarCliente(new Cliente("33333333", "Cliente 3"));
        banco.agregarCliente(new Cliente("44444444", "Cliente 4"));

        banco.agregarCuenta(cc1);
        banco.agregarCuenta(cc2);
        banco.agregarCuenta(cs);
        banco.agregarCuenta(ca);

        List<CuentaCorriente> cuentasCorrientes = banco.obtenerCuentasCorrientesOrdenadasPorSaldo();

        assertEquals("CBU002", cuentasCorrientes.get(0).getCBU());
        assertEquals("CBU001", cuentasCorrientes.get(1).getCBU());
        assertEquals(2, cuentasCorrientes.size());
    }
    
    //11
    /*
    @Test
    public void queSeObtengaUnListadoDeCuentasCorrientesDeudorasOrdenadoPorSaldoDeudor() {
        Banco banco = new Banco();

        CuentaCorriente cc1 = new CuentaCorriente("CBU001", cliente1, -500, 1000);  // deuda 500
        CuentaCorriente cc2 = new CuentaCorriente("CBU002", cliente2, -2000, 1500); // deuda 2000
        CuentaCorriente cc3 = new CuentaCorriente("CBU003", cliente3, 1000, 500);   // no deuda
        CuentaSueldo cs = new CuentaSueldo("CBU004", cliente4, 3000);

        banco.agregarCliente(new Cliente("11111111", "Cliente 1"));
        banco.agregarCliente(new Cliente("22222222", "Cliente 2"));
        banco.agregarCliente(new Cliente("33333333", "Cliente 3"));
        banco.agregarCliente(new Cliente("44444444", "Cliente 4"));

        banco.agregarCuenta(cc1);
        banco.agregarCuenta(cc2);
        banco.agregarCuenta(cc3);
        banco.agregarCuenta(cs);

        List<CuentaCorriente> cuentasDeudoras = banco.obtenerCuentasCorrientesDeudorasOrdenadasPorSaldoDeudor();

        assertEquals(2, cuentasDeudoras.size());
        assertEquals("CBU002", cuentasDeudoras.get(0).getCBU()); // mayor deuda primero (-2000)
        assertEquals("CBU001", cuentasDeudoras.get(1).getCBU()); // segunda deuda (-500)
    }
    */
}