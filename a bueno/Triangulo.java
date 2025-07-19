public class Triangulo {
    private int nivel;
    private int numero;
    private Triangulo siguiente;
    private Vertice primero;
    private Vertice segundo;
    private Vertice tercero;
    
    public Triangulo(int nivel, int numero, int valor) {
        // Inicializar
        this.nivel = nivel;
        this.numero = numero;
        this.primero = new Vertice(valor);
        this.segundo = new Vertice(0);
        this.tercero = new Vertice(0);
        this.siguiente = null;
        this.primero.setSiguienteVertice(segundo);
        this.segundo.setSiguienteVertice(tercero);
        this.tercero.setSiguienteVertice(primero);
    }
    
    // Getters
    public int getNivel() {
        return nivel;
    }

    public int getNumero() {
        return numero;
    }

    public Vertice getPrimero() {
        return primero;
    }

    public Vertice getSegundo() {
        return segundo;
    }

    public Vertice getTercero() {
        return tercero;
    }

    public Triangulo getSiguiente(){
        return siguiente;
    }

    // Setters
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPrimero(Vertice primero) {
        this.primero = primero;
    }

    public void setSegundo(Vertice segundo) {
        this.segundo = segundo;
    }

    public void setTercero(Vertice tercero) {
        this.tercero = tercero;
    }

    public void setSiguiente(Triangulo siguiente){
        this.siguiente = siguiente;
    }


}
