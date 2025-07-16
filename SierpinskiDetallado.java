public class SierpinskiDetallado {
    private Triangulo head;
    private Triangulo tail;
    
    public SierpinskiDetallado(int valor_1, int valor_2, int valor_3){
        Triangulo triangulo_base = new Triangulo(0,0, valor_1);
        this.head = triangulo_base;
        this.tail = triangulo_base;
        this.insertarVertice(valor_2);
        this.insertarVertice(valor_3);
    }

    // INSERTAR TRIANGULO ----------------------------------------------------------------------------------------
    public void insertarTriangulo(int valor){
        int datos[] = this.trianguloPosible();
        int nivel = datos[0], numero = datos[1];
        Triangulo triangulo_nuevo = new Triangulo (nivel, numero, valor);

        // Conexion entre triangulos
        this.head.setSiguiente(triangulo_nuevo);
        this.head = triangulo_nuevo;

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
    }

    public int[] trianguloPosible(){
        // Se añade otro nivel si se alcanzo el limite
        Triangulo triangulo_ultimo = this.head;
        int nivel_ultimo = triangulo_ultimo.getNivel();
        int numero_ultimo = triangulo_ultimo.getNumero();
        if(((numero_ultimo + 1) < (int) Math.pow(3, nivel_ultimo)) && (nivel_ultimo > 0)){
            int datos[] = {nivel_ultimo, numero_ultimo+1};
            return datos;
        }
        int datos[] = {nivel_ultimo + 1, 0};
        return datos; 
    }

    // CONEXIONES -------------------------------
    public void conexionesTriangulosNivel1(Triangulo triangulo_actual){
        triangulo_actual.getPrimero().setSiguienteTriangulo(this.tail.getSegundo());
        triangulo_actual.getSegundo().setSiguienteTriangulo(this.tail.getTercero());
        triangulo_actual.getTercero().setSiguienteTriangulo(this.tail.getPrimero());
        this.tail.getPrimero().setSiguienteTriangulo(triangulo_actual.getPrimero());
        this.tail.getSegundo().setSiguienteTriangulo(triangulo_actual.getSegundo());
        this.tail.getTercero().setSiguienteTriangulo(triangulo_actual.getTercero());
    }

    public void conexionesTriangulosNivel2(Triangulo 
    
    triangulo){
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
        if(numero == 0){ // Primera esquina
            this.esquina1(triangulo);
        }

        else if(numero < Math.pow(2,(nivel-1))){ // Primera arista
            this.arista1(triangulo);
        }

        else if(numero == Math.pow(2,(nivel-1))){ // Apice
            this.apice(triangulo);
        }

        else if(numero < Math.pow(2,(nivel-1))){ // Segunda arista
            this.arista2(triangulo);
        }

        else if(numero == (Math.pow(2,(nivel))-1)){ // Segunda esquina
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
        Triangulo esquina1_anterior = this.trianguloBusqueda(esquina1_actual.getNivel()-1, 0);

        // Nunca cambian.
        esquina1_actual.getTercero().setSiguienteTriangulo(this.tail.getPrimero());
        this.tail.getPrimero().setSiguienteTriangulo(esquina1_actual.getPrimero());

        esquina1_anterior.getTercero().setSiguienteTriangulo(esquina1_actual.getTercero());
        esquina1_actual.getPrimero().setSiguienteTriangulo(esquina1_anterior.getPrimero());
    }
    
    public void apice(Triangulo apice_actual){
        Triangulo apice_anterior = this.trianguloBusqueda(apice_actual.getNivel() - 1, (int) Math.pow(2, apice_actual.getNivel() - 2));

        // Nunca cambian
        apice_actual.getPrimero().setSiguienteTriangulo(this.tail.getSegundo());
        this.tail.getSegundo().setSiguienteTriangulo(apice_actual.getSegundo());

        apice_anterior.getPrimero().setSiguienteTriangulo(apice_actual.getPrimero());
        apice_actual.getSegundo().setSiguienteTriangulo(apice_anterior.getSegundo());
    }

    public void esquina2(Triangulo esquina2_actual){
        Triangulo esquina2_anterior = this.trianguloBusqueda(esquina2_actual.getNivel() - 1, (int) (Math.pow(2, (esquina2_actual.getNivel() - 1)) - 1));

        // Nunca cambian
        esquina2_actual.getSegundo().setSiguienteTriangulo(this.tail.getTercero());
        this.tail.getTercero().setSiguienteTriangulo(esquina2_actual.getSegundo());

        esquina2_anterior.getSegundo().setSiguienteTriangulo(esquina2_actual.getPrimero());
        esquina2_actual.getSegundo().setSiguienteTriangulo(esquina2_anterior.getSegundo());
    }

    public void arista1(Triangulo triangulo_actual){
        Triangulo triangulo_anterior = this.trianguloBusqueda(0, 0);
        Triangulo triangulo_siguiente = this.trianguloBusqueda(0, 0);

        triangulo_anterior.getPrimero().setSiguienteTriangulo(triangulo_actual.getPrimero());
        triangulo_actual.getPrimero().setSiguienteTriangulo(triangulo_siguiente.getPrimero());
    }

    public void arista2(Triangulo triangulo_actual){
        Triangulo triangulo_anterior = this.trianguloBusqueda(0, 0);
        Triangulo triangulo_siguiente = this.trianguloBusqueda(0, 0);

        triangulo_anterior.getSegundo().setSiguienteTriangulo(triangulo_actual.getSegundo());
        triangulo_actual.getSegundo().setSiguienteTriangulo(triangulo_siguiente.getSegundo());
    }

    public void base(Triangulo triangulo_actual){
        Triangulo triangulo_anterior = this.trianguloBusqueda(0, 0);
        Triangulo triangulo_siguiente = this.trianguloBusqueda(0, 0);

        triangulo_anterior.getTercero().setSiguienteTriangulo(triangulo_actual.getTercero());
        triangulo_actual.getTercero().setSiguienteTriangulo(triangulo_siguiente.getTercero());
    }

    // INSERTAR VERTICE---------------------------------------------------------------------------
    public void insertarVertice(int valor){
        boolean op = true; // Sin usar ni un break como me aconseja el papu Jonathan.
        Triangulo triangulo_actual = this.tail;
        while(op == true){
            if(triangulo_actual.getTercero() == null){
                if(triangulo_actual.getSegundo() == null){
                    triangulo_actual.setSegundo(new Vertice(valor));
                    op = false;
                }
                else{
                    triangulo_actual.setTercero(new Vertice(valor));
                    op = false;
                }
            }   

            if(triangulo_actual.getSiguiente() != null){
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
    public Triangulo trianguloBusqueda(int nivel, int numero){
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
    /* 
    public VerticeBusqueda(int valor){
        triangulo_actual = this.base;
        vertice_actual =  this.base.primero;
        while(op == True){
            for (int i = 0; i < 3; i++){
                if(vertice_actual.valor == valor){
                    return vertice_actual;
                }
                vertice_actual = vertice_actual.siguiente_vertice;
            }  
            triangulo_actual = vertice_actual.siguiente_triangulo;
        }
    }
    */

    // VISUALIZAR (por mientras) ---------------------------------------------------
    public void visualizar(){
        Triangulo triangulo_actual = this.tail;
        boolean op = true;
        while(op == true){
            System.out.printf("Nivel: %d, Numero: %d, Primero: %d, Segundo: %d, tercero: %d,\n",
            triangulo_actual.getNivel(), triangulo_actual.getNumero(), triangulo_actual.getPrimero().getValor(),  
            triangulo_actual.getSegundo().getValor(), triangulo_actual.getTercero().getValor());
            if(triangulo_actual.getSiguiente() != null){
                triangulo_actual = triangulo_actual.getSiguiente();
            }
            else{
                op = false;  
            } 
        }
    }
}
