public class Sierpinski {
    private Triangulo base;
    int subdivisiones;
    
    public Sierpinski() {
        this.base = new Triangulo(0,0);
        this.subdivisiones = 0;
    }
    
    public void InsertarSiguiente() {
        for(int i=0; i<Math.pow(3, subdivisiones); i=i+3){
            subdivisiones++;
            Triangulo T1 = new Triangulo(subdivisiones,i);
            T1.cabeza.siguiente_p = base.cabeza.siguiente_i;
            base.cabeza.siguiente_i = T1.cabeza;
            T1.primero.siguiente_p = base.primero.siguiente_i;
            base.primero.siguiente_i = T1.primero;
            T1.segundo.siguiente_p = base.segundo.siguiente_i;
            base.segundo.siguiente_i = T1.segundo;
        }
    }
}
