public class Vertice {
    private int valor;
    private Vertice siguiente_vertice;
    private Vertice siguiente_triangulo;

    public Vertice(int valor) {
        this.valor = valor;
        this.siguiente_vertice = null;
        this.siguiente_triangulo = null;
    }

    // Getters
    public int getValor() {
        return valor;
    }

    public Vertice getSiguienteVertice() {
        return siguiente_vertice;
    }

    public Vertice getSiguienteTriangulo() {
        return siguiente_triangulo;
    }

    // Setters
    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setSiguienteVertice(Vertice siguiente_vertice) {
        this.siguiente_vertice = siguiente_vertice;
    }

    public void setSiguienteTriangulo(Vertice siguiente_triangulo) {
        this.siguiente_triangulo = siguiente_triangulo;
    }

        
}
