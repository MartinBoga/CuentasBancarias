package banco; 

public class ClienteInexistenteException extends Exception {

    public ClienteInexistenteException(String mensaje) {
        super(mensaje);
    }
}
