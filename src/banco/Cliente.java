package banco;

public class Cliente implements Comparable<Cliente> {
    public String dni;
    public String nombre;

    public Cliente(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    @Override
    public int compareTo(Cliente otro) {
    	 return this.dni.compareTo(otro.dni);
    }
    
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }
}