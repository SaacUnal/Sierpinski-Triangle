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
        SierpinskiSimple sierpinski = new SierpinskiSimple(8, 52, 12);
        sierpinski.visualizar();
        System.out.println(' ');

        // Insertar triangulos.
        sierpinski.insertarTriangulo(67);
        for(int i = 0; i < 7; i++){
            sierpinski.insertarTriangulo(numero_random.nextInt(1, 100));  
        }
        sierpinski.insertarTriangulo(99);
        sierpinski.visualizar();
        System.out.println(' ');

        // Insertar vertices.
        sierpinski.insertarVertice(45);
        for(int i = 0; i < 30; i++){
            sierpinski.insertarVertice(numero_random.nextInt(1, 100));    
        }
        sierpinski.visualizar();
        System.out.println(' ');

        // Busqueda
        int vertice_buscar = numero_random.nextInt(1, 100);
        System.out.println("Buscando el vertice: " + vertice_buscar);
        Vertice vertice_encontrado = sierpinski.busquedaVertice(vertice_buscar);
        System.out.println(vertice_encontrado);
        System.out.println(' ');

        // Eliminar triangulos.
        sierpinski.eliminarTriangulo(0, 0);
        sierpinski.eliminarTriangulo(1, 0);
        sierpinski.eliminarTriangulo(1, 1);
        sierpinski.eliminarTriangulo(2, 0);
        sierpinski.eliminarTriangulo(2, 1);
        sierpinski.eliminarTriangulo(3, 0);
        int nivel_eliminar = (numero_random.nextInt(3, 4));
        int numero_eliminar = (numero_random.nextInt(0, (int)((Math.pow(3, (nivel_eliminar - 1)))- 1)));
        System.out.println("Eliminando el triangulo: nivel=" + nivel_eliminar + ", numero=" + numero_eliminar);
        sierpinski.eliminarTriangulo(nivel_eliminar, numero_eliminar);
        sierpinski.visualizar();
        System.out.println(' ');

        // Eliminar vertices.
        sierpinski.eliminarVertice(45);
        sierpinski.eliminarVertice(99);
        int vertice_eliminar = numero_random.nextInt(1, 100);
        System.out.println("Eliminando el vertice: " + vertice_eliminar);
        sierpinski.eliminarVertice(vertice_eliminar);
        sierpinski.visualizar();
        System.out.println(' ');

        // Crear los triangulos restantes.
        for(int i = 0; i < 30; i++){
            int n = numero_random.nextInt(1, 100);
            sierpinski.insertarTriangulo(n);  // Aca me salen unos mensajes de error no se pq sjadjsda, pero no afecta.
        }
        sierpinski.visualizar();
        System.out.println(' ');

    }
}
