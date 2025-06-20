# CuentasBancarias
Un banco que gestiona cuentas bancarias y clientes.

Banco
Un banco gestiona cuentas bancarias y clientes. Un cliente puede tener más de una
cuenta en el banco. Cualquier persona que quiera abrir una cuenta, primero debe
registrarse como cliente en el banco. El orden natural de la lista de clientes es por
DNI. El orden natural de la lista de cuentas es por CBU. A través del banco deben
poder realizarse los siguientes movimientos: depositar, retirar, transferir. Cada
operación deberá impactar sobre los saldos de las cuentas afectadas a la misma.

Cuentas bancarias
Su identificador único es la Clave Bancaria Uniforme (CBU). Una Cuenta posee un
saldo y el DNI del titular. Se puede agregar dinero a la Cuenta, incrementando el
saldo. También se puede retirar dinero de la Cuenta, decrementando dicho saldo.
- Cuenta Sueldo: Es el tipo de cuenta más simple, ya que se rige por la premisa
de que en tanto y en cuanto se tenga tanto o más dinero en cuenta del que se
quiere extraer, la operación se debe efectuar correctamente.
- Caja de Ahorros: Similar a la anterior, pero se pide que luego de la quinta
extracción de dinero se cobre un costo adicional por extracción de $ 100.
- Cuenta Corriente: La más compleja de las cuentas, ésta permite establecer
una cantidad de dinero a girar en descubierto. Es por ello que cada vez que se
desee extraer dinero, no sólo se considera el que se posee, sino el límite
adicional que el banco estará brindando. Por supuesto esto no es gratis, ya
que el banco nos cobrará un 5% como comisión sobre todo el monto en
descubierto consumido en la operación. Por ejemplo, si tuviéramos $ 100 en
la cuenta, y quisiéramos retirar $ 200 (con un descubierto de $ 150),
podremos hacerlo. Pasaremos a deber al banco $ 105 en total: los $ 100 que
nos cubrió, más el 5% adicional sobre el descubierto otorgado.

Tests obligatorios:
1. queSePuedaExtraer1000PesosDeUnaCuentaSueldoConSaldoIgualA2000Pesos 5%
2. queNoSePuedaExtraer2500PesosDeUnaCuentaSueldoConSaldoIgualA2000Pesos 5%
3. queAlRealizar6ExtraccionesDe1000EnUnaCajaDeAhorroConSaldoInicialDe10000SuSaldoFinalSea3900 10%
4. queAlRealizar7ExtraccionesDe1000EnUnaCajaDeAhorroConSaldoInicialDe7000SoloLaUltimaLanceExcepcionSaldoInsuficiente 10%
5. queSeCobreRecargoAlRealizarUnaExtraccionMayorAlSaldoEnUnaCuentaCorriente 10%
6. queAlIntentarDarDeAltaUnaCuentaAUnClienteInexistenteLanceExcepcion 10%
7. queAlBuscarUnaCuentaPorCBUErroneoLanceExcepcion 10%
8. queSeObtengaElListadoDeClientesOrdenadosPorDni 10%
9. queSeObtengaUnListadoDeTodasLasCuentasOrdenadoPorSaldo 10%
10. queSeObtengaUnListadoDeCuentasCorrientesOrdenadoPorSaldo 10%
11. queSeObtengaUnListadoDeCuentasCorrientesDeudorasOrdenadoPorSaldoDeudor 10%
