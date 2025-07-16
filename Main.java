import java.util.random.RandomGenerator;

public class Main{
    /*
    Dios bendiga las buenas practicas.
    Se toma el 0 como null pq para hacerlo como la idea original tocaria hacer todas las combinaciones:
    1-0, 1-1, 1-2, 1-3, 2-0, 2-1, 2-2, 2-3, 3-0, 3-1, 3-2, 3-3.
    Esto pq si un vertice es null se jode todo jsajsad.
    */ 
    public static void main(String[] args){
        RandomGenerator numero_random = RandomGenerator.of("L64X128MixRandom");
        Sierpinski sierpinski = new Sierpinski(8, 52, 12);
        sierpinski.visualizar();
        System.out.println(' ');

        // Insertar triangulos.
        sierpinski.insertarTriangulo(67);
        for(int i = 0; i <= 10; i++){
            sierpinski.insertarTriangulo(numero_random.nextInt(1, 100));    
        }
        sierpinski.insertarTriangulo(67);
        sierpinski.visualizar();
        System.out.println(' ');

        // Insertar vertices.
        sierpinski.insertarVertice(45);
        for(int i = 0; i <= 25; i++){
            sierpinski.insertarVertice(numero_random.nextInt(1, 100));    
        }
        sierpinski.visualizar();
        System.out.println(' ');

        // Busqueda, aqui es donde se detalla la forma
        //sierpinski.busqueda();
        sierpinski.visualizar();
        System.out.println(' ');

    }
}
