public class Triangulo {
    private int nivel;
    private int numero;
    private Vertice primero;
    private Vertice segundo;
    private Vertice tercero;
    
    public Triangulo(int nivel, int numero, int valor) {
        // Inicializar
        this.nivel = nivel;
        this.numero = numero;
        this.primero = new Vertice(valor);
        this.segundo = null;
        this.tercero = null;
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
        primero.setSiguienteVertice(segundo);
    }

    public void setTercero(Vertice tercero) {
        this.tercero = tercero;
        segundo.setSiguienteVertice(tercero);
        tercero.setSiguienteVertice(primero);
    }


}
