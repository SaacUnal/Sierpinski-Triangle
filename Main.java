public class Main{
    public static void main(String[] args){
        Sierpinski sierpinski = new Sierpinski(8, 52, 12);
        sierpinski.visualizar();

        // Insertar triangulos.
        sierpinski.insertarTriangulo(67);
        sierpinski.visualizar();

        // Insertar vertices.
        sierpinski.insertarVertice(45);
        sierpinski.visualizar();

        // Busqueda
        //sierpinski.busqueda();
        sierpinski.visualizar();

    }
}
