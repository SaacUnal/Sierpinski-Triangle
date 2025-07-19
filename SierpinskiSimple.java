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

    // INSERTAR----------------------------------------------------------------------------------------
    public void insertarTriangulo(int valor){
        int datos[] = this.posibleTriangulo();
        int nivel = datos[0], numero = datos[1];
        TrianguloSimple triangulo_nuevo = new TrianguloSimple (nivel, numero, valor);
        // Cuando se inserta un triangulo que se habia eliminado.
        if((this.head.getNivel() == nivel && this.head.getNumero() > numero)||(this.head.getNivel() > nivel)){
            this.conexionesTriangulos(triangulo_nuevo);
            TrianguloSimple triangulo_anterior = this.tail;
            int datos_posibles[] = this.siguientePosibleTriangulo(triangulo_anterior.getNivel(), triangulo_anterior.getNumero());
            boolean op = true;
            while(op == true){
                if((datos_posibles[0] == triangulo_nuevo.getNivel()) && (datos_posibles[1] == triangulo_nuevo.getNumero())){
                    op = false;
                }
                else{
                    triangulo_anterior = triangulo_anterior.getSiguiente();
                    datos_posibles = this.siguientePosibleTriangulo(triangulo_anterior.getNivel(), triangulo_anterior.getNumero());
                }
            }
            triangulo_nuevo.setSiguiente(triangulo_anterior.getSiguiente());
            triangulo_anterior.setSiguiente(triangulo_nuevo);
        }
        else{
            this.head.setSiguiente(triangulo_nuevo);
            this.head = triangulo_nuevo;
            this.head.setSiguiente(this.tail);
            this.conexionesTriangulos(triangulo_nuevo);
        }
        
    }

    public int[] posibleTriangulo(){
        TrianguloSimple triangulo_actual = this.tail;
        int nivel_ultimo = triangulo_actual.getNivel();
        int numero_ultimo = triangulo_actual.getNumero();

        int datos[] = this.siguientePosibleTriangulo(nivel_ultimo, numero_ultimo);
        int nivel_posible = datos[0];
        int numero_posible = datos[1];

        // Si hay un triangulo eliminado antes.
        while(triangulo_actual != head){
            if((triangulo_actual.getSiguiente().getNivel() != nivel_posible) || (triangulo_actual.getSiguiente().getNumero() != numero_posible)){
                return datos;
            }
            else{
                triangulo_actual = triangulo_actual.getSiguiente();
                nivel_ultimo = triangulo_actual.getNivel();
                numero_ultimo = triangulo_actual.getNumero();
            }
            datos = this.siguientePosibleTriangulo(nivel_ultimo, numero_ultimo);
            nivel_posible = datos[0];
            numero_posible = datos[1];
        }
        return datos;
    }

    public int[] siguientePosibleTriangulo(int nivel_ultimo, int numero_ultimo){
        if(((numero_ultimo + 1) < (int)(Math.pow(3, nivel_ultimo - 1))) && (nivel_ultimo > 0)){
            int datos[] = {nivel_ultimo, numero_ultimo+1};
            return datos;
        }
        // Se añade otro nivel si se alcanzo el limite.
        int datos[] = {nivel_ultimo + 1, 0};
        return datos; 
    }

    public void conexionesTriangulos(TrianguloSimple triangulo_nuevo){
        TrianguloSimple triangulo_anterior = this.tail.getSiguiente();
        if((triangulo_anterior.getNivel() == 0) || (triangulo_anterior == triangulo_nuevo)){
            return;
        }
        triangulo_anterior = this.busquedaTriangulo(((triangulo_nuevo.getNivel())- 1), (triangulo_nuevo.getNumero()/ 3));
        // Si el triangulo ya habia sido creado.
        if(triangulo_nuevo != this.head){
            // Conexiones propias
            triangulo_nuevo.setIzquierda(this.busquedaTriangulo((triangulo_nuevo.getNivel())+ 1, triangulo_nuevo.getNumero()* 3));
            triangulo_nuevo.setArriba(this.busquedaTriangulo((triangulo_nuevo.getNivel())+ 1, (triangulo_nuevo.getNumero()* 3) + 1));
            triangulo_nuevo.setDerecha(this.busquedaTriangulo((triangulo_nuevo.getNivel())+ 1, (triangulo_nuevo.getNumero()* 3) + 2));
            switch (triangulo_nuevo.getNumero() % 3) {
                case 0: triangulo_anterior.setIzquierda(triangulo_nuevo);
                break;
                case 1: triangulo_anterior.setArriba(triangulo_nuevo);
                break;
                case 2: triangulo_anterior.setDerecha(triangulo_nuevo);
                break;
            }
            return;
        }
        
        if(triangulo_anterior.getIzquierda() == null){
            triangulo_anterior.setIzquierda(triangulo_nuevo);
        }
        else if(triangulo_anterior.getArriba() == null){
            triangulo_anterior.setArriba(triangulo_nuevo);
        }
        else{
            triangulo_anterior.setDerecha(triangulo_nuevo);
        }   
    }

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

    // ELIMINAR -----------------------------------------------------------------------------------
    public void eliminarTriangulo(int nivel, int numero){
        TrianguloSimple triangulo_eliminar = this.busquedaTriangulo(nivel, numero);
        if(triangulo_eliminar == null){
            System.out.println("El triangulo no se puede eliminar, no existe.");
            return;
        }
        TrianguloSimple triangulo_anterior = null;
        TrianguloSimple triangulo_actual = this.tail;
        boolean op = true;
        while(op == true){
            if(triangulo_actual.getSiguiente() == triangulo_eliminar){
                triangulo_anterior = triangulo_actual;
                op = false;
            }
            else if(triangulo_actual.getSiguiente() != this.tail){
                triangulo_actual = triangulo_actual.getSiguiente();
            }
            else{
                op = false;  
            } 
        }

        if(triangulo_eliminar == this.tail){
            System.out.println("La base no se puede eliminar.");
        }
        else if(triangulo_eliminar != null){
            // Arreglar las conexiones.
            triangulo_anterior.setSiguiente(triangulo_eliminar.getSiguiente());

            triangulo_eliminar = null;
        }   
    }

    public void eliminarVertice(int valor){
        Vertice vertice_a_eliminar = this.busquedaVertice(valor);
        if(vertice_a_eliminar != null){
            vertice_a_eliminar.setValor(0);
        } 
        else {
            System.out.println("El vertice no se puede eliminar, no existe.");
        }
    }
        
    // BUSQUEDA ------------------------------------------------------------------------------
    public TrianguloSimple busquedaTriangulo(int nivel, int numero){
        TrianguloSimple triangulo_actual = this.tail;
        if(nivel == 0 && numero == 0){
            return this.tail; 
        }
        else if(nivel == this.head.getNivel() && numero == this.head.getNumero()){
            return this.head;
        }
        while(triangulo_actual.getSiguiente() != this.tail){
            if((triangulo_actual.getNivel() == nivel) && (triangulo_actual.getNumero() == numero)){
                return triangulo_actual;
            }
            triangulo_actual = triangulo_actual.getSiguiente();
            }
        System.out.println("El triangulo no existe. ");
        return null;    
        }

    public Vertice busquedaVertice(int valor){
        TrianguloSimple triangulo_actual = this.tail;
        Vertice vertice_actual = null;

        boolean op = true;
        while(op == true){
            vertice_actual = triangulo_actual.getPrimero();
            for (int i = 0; i <= 3; i++){
                if(vertice_actual.getValor() == valor){
                    return vertice_actual;
                }
                vertice_actual = vertice_actual.getSiguienteVertice();
            }  
            if(triangulo_actual.getSiguiente() != this.tail){
                triangulo_actual = triangulo_actual.getSiguiente();
            }
            else{
                op = false;  
            } 
        }
        System.out.println("El vertice no existe. ");
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

