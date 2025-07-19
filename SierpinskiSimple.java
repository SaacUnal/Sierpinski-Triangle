public class SierpinskiSimple {
    private TrianguloSimple head;
    private TrianguloSimple tail;
    
    public SierpinskiSimple(int valor_1, int valor_2, int valor_3){
        TrianguloSimple triangulo_base = new TrianguloSimple(0,0, valor_1);
        this.head = triangulo_base;
        this.tail = triangulo_base;
        this.head.setSiguiente(this.tail);
        this.insertarVertice(valor_2);
        this.insertarVertice(valor_3);
    }

    // INSERTAR TRIANGULO ----------------------------------------------------------------------------------------
    public void insertarTriangulo(int valor){
        int datos[] = this.posibleTriangulo();
        int nivel = datos[0], numero = datos[1];
        TrianguloSimple triangulo_nuevo = new TrianguloSimple (nivel, numero, valor);
        this.head.setSiguiente(triangulo_nuevo);
        this.conexionesTriangulos(triangulo_nuevo);
        this.head = triangulo_nuevo;
        this.head.setSiguiente(this.tail);
    }

    public int[] posibleTriangulo(){
        // Se añade otro nivel si se alcanzo el limite
        TrianguloSimple triangulo_actual = this.tail;
        while(triangulo_actual.getSiguiente() != tail){
            if(triangulo_actual.getSiguiente() == null){
                break;
            }
            else{
                triangulo_actual = triangulo_actual.getSiguiente();
            }
        }
        int nivel_ultimo = triangulo_actual.getNivel();
        int numero_ultimo = triangulo_actual.getNumero();
        if(((numero_ultimo + 1) < (int)Math.pow(3, nivel_ultimo - 1)) && (nivel_ultimo > 0)){
            int datos[] = {nivel_ultimo, numero_ultimo+1};
            return datos;
        }
        int datos[] = {nivel_ultimo + 1, 0};
        return datos; 
    }

    public void conexionesTriangulos(TrianguloSimple triangulo_nuevo){
        TrianguloSimple triangulo_actual = this.tail.getSiguiente();
        boolean op = true;
        if((triangulo_actual.getNivel() == 0) || (triangulo_actual == triangulo_nuevo)){
            op = false;
        }
        while(op == true){
            if(triangulo_actual.getIzquierda() == null){
                triangulo_actual.setIzquierda(triangulo_nuevo);
                op = false;
            }
            else if(triangulo_actual.getArriba() == null){
                triangulo_actual.setArriba(triangulo_nuevo);
                op = false;
            }
            else if(triangulo_actual.getDerecha() == null){
                triangulo_actual.setDerecha(triangulo_nuevo);
                op = false;
            }  
            else{
                triangulo_actual = triangulo_actual.getSiguiente();
            }
        }
    }

    // INSERTAR VERTICE---------------------------------------------------------------------------
    public void insertarVertice(int valor){
        boolean op = true;
        TrianguloSimple triangulo_actual = this.tail;
        while(op == true){
            if(triangulo_actual.getTercero().getValor() == 0){
                if(triangulo_actual.getSegundo().getValor() == 0){
                    triangulo_actual.getSegundo().setValor(valor);
                    op = false;
                }
                else{
                    triangulo_actual.getTercero().setValor(valor);
                    op = false;
                }
            }   
            else if(triangulo_actual != this.head){
                triangulo_actual = triangulo_actual.getSiguiente();
            }
            else{
                // Si todos los triangulos estan llenos se crea uno nuevo.
                this.insertarTriangulo(valor);
                op = false;
            } 
        }
    }

    // ELIMINAR TRIANGULO -----------------------------------------------------------------------------------
    public void eliminarTriangulo(int nivel, int numero){
        TrianguloSimple triangulo_a_eliminar = this.busquedaTriangulo(nivel, numero);
        if(triangulo_a_eliminar != null){
            // Elimina el triangulo y sus vertices.
            triangulo_a_eliminar.setPrimero(new Vertice(0));
            triangulo_a_eliminar.setSegundo(new Vertice(0));
            triangulo_a_eliminar.setTercero(new Vertice(0));
            // Elimina las conexiones con otros triangulos.
            triangulo_a_eliminar.setIzquierda(null);
            triangulo_a_eliminar.setArriba(null);
            triangulo_a_eliminar.setDerecha(null);
        } 
        else {
            System.out.println("El triangulo no existe.");
        }

        
    }

    public void eliminarVertice(int valor){
        Vertice vertice_a_eliminar = this.busquedaVertice(valor);
        if(vertice_a_eliminar != null){
            vertice_a_eliminar.setValor(0);
        } 
        else {
            System.out.println("El vertice no existe.");
        }
    }
        
    // BUSQUEDA ------------------------------------------------------------------------------
    public TrianguloSimple busquedaTriangulo(int nivel, int numero){
        TrianguloSimple triangulo_actual = this.tail;
        while(triangulo_actual.getSiguiente() != null){
            if((triangulo_actual.getNivel() == nivel) && (triangulo_actual.getNumero() == numero)){
                return triangulo_actual;
            }
            triangulo_actual = triangulo_actual.getSiguiente();
            }
        System.out.println("No existe ese triangulo. ");
        return this.head;       
        }

    public Vertice busquedaVertice(int valor){
        TrianguloSimple triangulo_actual = this.tail;
        Vertice vertice_actual = null;

        boolean op = true;
        while(op == true){
            vertice_actual = triangulo_actual.getPrimero();
            System.out.printf("Primero: %d \n",vertice_actual.getValor());
            for (int i = 0; i <= 3; i++){
                if(vertice_actual.getValor() == valor){
                    return vertice_actual;
                }
                System.out.printf("Vertice: %d, Valor: %d \n", i%3, vertice_actual.getValor());
                vertice_actual = vertice_actual.getSiguienteVertice();
            }  
            if(triangulo_actual.getSiguiente() != this.tail){
                triangulo_actual = triangulo_actual.getSiguiente();
            }
            else{
                op = false;  
            } 
        }
        System.out.println("No existe ese vertice. ");
        return vertice_actual;  
    }

    // VISUALIZAR (por mientras) ---------------------------------------------------
    public void visualizar(){
        TrianguloSimple triangulo_actual = this.tail;
        boolean op = true;
        while(op == true){
            System.out.println(triangulo_actual);
            if(triangulo_actual.getSiguiente() != this.tail){
                triangulo_actual = triangulo_actual.getSiguiente();
            }
            else{
                op = false;  
            } 
        }
    }
}   

