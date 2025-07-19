public class Sierpinski {
    private Triangulo head;
    private Triangulo tail;
    
    public Sierpinski(int valor_1, int valor_2, int valor_3){
        Triangulo triangulo_base = new Triangulo(0,0, valor_1);
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
        Triangulo triangulo_nuevo = new Triangulo (nivel, numero, valor);
        this.head.setSiguiente(triangulo_nuevo);

        // Conexiones entre vertices
        switch(nivel){
            // Casos especiales
            case 1: this.conexionesTriangulosNivel1(triangulo_nuevo);
            break;
            case 2: this.conexionesTriangulosNivel2(triangulo_nuevo);
            break;
            case 3: this.conexionesTriangulosNivel3(triangulo_nuevo);
            break;
            default: this.conexionesTriangulos();
            break;
        }  
        this.head = triangulo_nuevo;
        this.head.setSiguiente(this.tail);
    }

    public int[] posibleTriangulo(){
        // Se añade otro nivel si se alcanzo el limite
        Triangulo triangulo_ultimo = this.head;
        int nivel_ultimo = triangulo_ultimo.getNivel();
        int numero_ultimo = triangulo_ultimo.getNumero();
        if(((numero_ultimo + 1) < (int)Math.pow(3, nivel_ultimo - 1)) && (nivel_ultimo > 0)){
            int datos[] = {nivel_ultimo, numero_ultimo+1};
            return datos;
        }
        int datos[] = {nivel_ultimo + 1, 0};
        return datos; 
    }

    // CONEXIONES -------------------------------
    public void conexionesTriangulosNivel1(Triangulo triangulo_actual){
        this.tail.getPrimero().setSiguienteTriangulo(triangulo_actual.getPrimero());
        triangulo_actual.getPrimero().setSiguienteTriangulo(this.tail.getSegundo());
        this.tail.getSegundo().setSiguienteTriangulo(triangulo_actual.getSegundo());
        triangulo_actual.getSegundo().setSiguienteTriangulo(this.tail.getTercero());
        this.tail.getTercero().setSiguienteTriangulo(triangulo_actual.getTercero());
        triangulo_actual.getTercero().setSiguienteTriangulo(this.tail.getPrimero());  
    }

    public void conexionesTriangulosNivel2(Triangulo triangulo){
        int numero = triangulo.getNumero();
        switch(numero){
            case 0: this.esquina1(triangulo);
            break;
            case 1: this.apice(triangulo);
            break;
            case 2: this.esquina2(triangulo);
            break;
        }
    }

    public void conexionesTriangulosNivel3(Triangulo triangulo){
        int numero = triangulo.getNumero();
        int nivel = triangulo.getNivel();
        if(numero == 0){                            // Primera esquina
            this.esquina1(triangulo);
        }

        else if(numero < (int)((Math.pow(3, (nivel - 1))- 1)/ 2)){ // Primera arista
            this.arista1(triangulo);
        }

        else if(numero == (int)((Math.pow(3, (nivel - 1))- 1)/ 2)){ // Apice
            this.apice(triangulo);
        }

        else if(numero < (int)(Math.pow(3, (nivel - 1))- 1)){ // Segunda arista
            this.arista2(triangulo);
        }

        else if(numero == (int)(Math.pow(3, (nivel - 1))- 1)){ // Segunda esquina
            this.esquina2(triangulo);
        }

        else{ // Base
            this.base(triangulo);
        }
    }

    public void conexionesTriangulos(){

    }
    
    // TIPOS DE CONEXIONES ------------------------------------------------
    public void esquina1(Triangulo esquina1_actual){
        Triangulo esquina1_anterior = this.busquedaTriangulo(esquina1_actual.getNivel() - 1, 0);

        // Nunca cambian.
        esquina1_actual.getTercero().setSiguienteTriangulo(this.tail.getPrimero());
        this.tail.getPrimero().setSiguienteTriangulo(esquina1_actual.getPrimero());

        esquina1_anterior.getTercero().setSiguienteTriangulo(esquina1_actual.getTercero());
        esquina1_actual.getPrimero().setSiguienteTriangulo(esquina1_anterior.getPrimero());
    }
    
    public void apice(Triangulo apice_actual){
        Triangulo apice_anterior = this.busquedaTriangulo(apice_actual.getNivel() - 1, (int)((Math.pow(3, (apice_actual.getNivel() - 2))- 1)/ 2));

        // Nunca cambian
        apice_actual.getPrimero().setSiguienteTriangulo(this.tail.getSegundo());
        this.tail.getSegundo().setSiguienteTriangulo(apice_actual.getSegundo());

        apice_anterior.getPrimero().setSiguienteTriangulo(apice_actual.getPrimero());
        apice_actual.getSegundo().setSiguienteTriangulo(apice_anterior.getSegundo());
    }

    public void esquina2(Triangulo esquina2_actual){
        Triangulo esquina2_anterior = this.busquedaTriangulo(esquina2_actual.getNivel() - 1, (int)(Math.pow(3, (esquina2_actual.getNivel() - 2))- 1));

        // Nunca cambian
        esquina2_actual.getSegundo().setSiguienteTriangulo(this.tail.getTercero());
        this.tail.getTercero().setSiguienteTriangulo(esquina2_actual.getTercero());

        esquina2_anterior.getSegundo().setSiguienteTriangulo(esquina2_actual.getSegundo());
        esquina2_actual.getTercero().setSiguienteTriangulo(esquina2_anterior.getTercero());
    }

    // HOLA ------
    public void arista1(Triangulo triangulo_actual){
        // this.busquedaTriangulo(triangulo_actual.getNivel(), triangulo_actual.getNumero() - 1) Arreglar esto
        Vertice vertice_triangulo_anterior = this.head.getPrimero().getSiguienteTriangulo();

        triangulo_actual.getPrimero().setSiguienteTriangulo(vertice_triangulo_anterior.getSiguienteTriangulo());
        vertice_triangulo_anterior.setSiguienteTriangulo(triangulo_actual.getPrimero());
        
    }

    public void arista2(Triangulo triangulo_actual){
        Vertice vertice_triangulo_anterior = this.head.getSegundo().getSiguienteTriangulo();

        triangulo_actual.getSegundo().setSiguienteTriangulo(vertice_triangulo_anterior.getSiguienteTriangulo());
        vertice_triangulo_anterior.setSiguienteTriangulo(triangulo_actual.getSegundo());
        
    }

    public void base(Triangulo triangulo_actual){
        Vertice vertice_triangulo_anterior = this.head.getTercero().getSiguienteTriangulo();

        triangulo_actual.getTercero().setSiguienteTriangulo(vertice_triangulo_anterior.getSiguienteTriangulo());
        vertice_triangulo_anterior.setSiguienteTriangulo(triangulo_actual.getTercero());
        
    }

    // INSERTAR VERTICE---------------------------------------------------------------------------
    public void insertarVertice(int valor){
        boolean op = true;
        Triangulo triangulo_actual = this.tail;
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

    // BUSQUEDA ------------------------------------------------------------------------------
    public Triangulo busquedaTriangulo(int nivel, int numero){
        Triangulo triangulo_actual = this.tail;
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
        Vertice triangulo_primer_vertice = this.tail.getPrimero();
        Vertice vertice_actual = null;
        boolean op = true;
        while(op == true){
            vertice_actual = triangulo_primer_vertice;
            System.out.printf("Primero: %d \n",triangulo_primer_vertice.getValor());
            for (int i = 0; i <= 3; i++){
                if(vertice_actual.getValor() == valor){
                    return vertice_actual;
                }
                System.out.printf("Vertice: %d, Valor: %d \n", i%3, vertice_actual.getValor());
                vertice_actual = vertice_actual.getSiguienteVertice();
            }  
            if(triangulo_primer_vertice.getSiguienteTriangulo() != this.tail.getPrimero()){
                triangulo_primer_vertice = triangulo_primer_vertice.getSiguienteTriangulo();
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
        Triangulo triangulo_actual = this.tail;
        boolean op = true;
        while(op == true){
            this.datosTriangulo(triangulo_actual);
            if(triangulo_actual.getSiguiente() != this.tail){
                triangulo_actual = triangulo_actual.getSiguiente();
            }
            else{
                op = false;  
            } 
        }
    }

    // Muy por mientras
    public void datosTriangulo(Triangulo triangulo){
        System.out.printf("Nivel: %d, Numero: %d. \n" + 
                        "Primero: %d, Primero siguiente vertice: %d. \n" + 
                        "Segundo: %d, Segundo siguiente vertice: %d. \n" + 
                        "Tercero: %d, Tercero siguiente vertice: %d. \n",
        triangulo.getNivel(), triangulo.getNumero(), 
        triangulo.getPrimero().getValor(), triangulo.getPrimero().getSiguienteVertice().getValor(), 
        triangulo.getSegundo().getValor(), triangulo.getSegundo().getSiguienteVertice().getValor(),
        triangulo.getTercero().getValor(), triangulo.getTercero().getSiguienteVertice().getValor());
    }

    public void datosTriangulo2(Triangulo triangulo){
        System.out.printf("Nivel: %d, Numero: %d. \\n" + //
                        "Primero: %d, Primero siguiente vertice: %d, Primero siguiente triangulo: %d. \\n" + //
                        "Segundo: %d, Segundo siguiente vertice: %d, Segundo siguiente triangulo: %d. \\n" + //
                        "Tercero: %d, Tercero siguiente vertice: %d, Tercero siguiente triangulo: %d \n",
        triangulo.getNivel(), triangulo.getNumero(), 
        triangulo.getPrimero().getValor(), triangulo.getPrimero().getSiguienteVertice().getValor(), 
        //triangulo.getPrimero().getSiguienteTriangulo().getValor(), 
        triangulo.getSegundo().getValor(), triangulo.getSegundo().getSiguienteVertice().getValor(),
        triangulo.getTercero().getValor(), triangulo.getTercero().getSiguienteVertice().getValor());
    }
}
