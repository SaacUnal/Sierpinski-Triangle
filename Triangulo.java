public class Triangulo {
    Vertice cabeza;
    Vertice primero;
    Vertice segundo;
    int nivel;
    

    public Triangulo(int nivel, int vertice_inicial) {
        this.cabeza = new Vertice(vertice_inicial);
        this.primero = new Vertice(vertice_inicial+1);
        this.segundo = new Vertice(vertice_inicial+2);
        cabeza.siguiente_i = primero;
        primero.siguiente_i = segundo;
        segundo.siguiente_i = cabeza;
        this.nivel = nivel;
    }
}
