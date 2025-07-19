public class TrianguloSimple {
    private int nivel;
    private int numero;
    private TrianguloSimple siguiente;
    private TrianguloSimple izquierda;
    private TrianguloSimple arriba;
    private TrianguloSimple derecha;
    private Vertice primero;
    private Vertice segundo;
    private Vertice tercero;
    
    public TrianguloSimple(int nivel, int numero, int valor) {
        // Inicializar
        this.nivel = nivel;
        this.numero = numero;
        this.primero = new Vertice(valor);
        this.segundo = new Vertice(0);
        this.tercero = new Vertice(0);
        this.siguiente = null;
        this.izquierda = null;
        this.arriba = null;
        this.derecha = null;
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

    public TrianguloSimple getSiguiente(){
        return siguiente;
    }

    public TrianguloSimple getIzquierda() {
        return izquierda;
    }

    public TrianguloSimple getArriba() {
        return arriba;
    }

    public TrianguloSimple getDerecha() {
        return derecha;
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

    public void setSiguiente(TrianguloSimple siguiente){
        this.siguiente = siguiente;
    }

    public void setIzquierda(TrianguloSimple izquierda) {
        this.izquierda = izquierda;
    }

    public void setArriba(TrianguloSimple arriba) {
        this.arriba = arriba;
    }

    public void setDerecha(TrianguloSimple derecha) {
        this.derecha = derecha;
    }

    @Override
    public String toString() {
        return  "Triangulo{"+
                "\nnivel=" + nivel +
                ", numero=" + numero +
                ", primero=" + primero.getValor() +
                ", segundo=" + segundo.getValor() +
                ", tercero=" + tercero.getValor() +
                ", \nizquierda=" + (izquierda != null ? izquierda.getNivel() + "-" + izquierda.getNumero() : "null") +
                ", arriba=" + (arriba != null ? arriba.getNivel() + "-" + arriba.getNumero() : "null") +
                ", derecha=" + (derecha != null ? derecha.getNivel() + "-" + derecha.getNumero() : "null") + '}';
    }

}
