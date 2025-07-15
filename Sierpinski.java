import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class Sierpinski {
    private ArrayList<Triangulo> triangulos;
    private Triangulo base;
    private Vertice head;
    private Triangulo tail;
    
    public Sierpinski(int valor_1, int valor_2, int valor_3){
        this.triangulos = new ArrayList<>();
        this.base = new Triangulo(0,0, valor_1);
        this.triangulos.add(base);
        this.insertarVertice(valor_2);
        this.insertarVertice(valor_3);
        this.head = this.base.getPrimero();

    }

    // INSERTAR TRIANGULO ----------------------------------------------------------------------------------------
    public void insertarTriangulo(int valor){
        int datos[] = this.trianguloPosible();
        int nivel = datos[0], numero = datos[1];
        Triangulo nuevo_triangulo = new Triangulo (nivel, numero, valor);
        this.triangulos.add(nuevo_triangulo);

        // Arreglar las conexiones
        switch(nivel){
            // Casos especiales
            case 1: this.conexionesTriangulosNivel1(nuevo_triangulo);
            break;
            case 2: this.conexionesTriangulosNivel2(nuevo_triangulo);
            break;
            case 3: this.conexionesTriangulosNivel3(nuevo_triangulo);
            break;
            default:this.conexionesTriangulos();
            break;
        }  
    }

    public int[] trianguloPosible(){
        // Se añade otro nivel si se alcanzo el limite
        Triangulo triangulo_ultimo = this.triangulos.get(this.triangulos.size() - 1);
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
    public void conexionesTriangulosNivel1(Triangulo triangulo){
        Triangulo triangulo_actual = triangulo;
        triangulo_actual.getPrimero().setSiguienteTriangulo(this.base.getSegundo());
        triangulo_actual.getSegundo().setSiguienteTriangulo(this.base.getTercero());
        triangulo_actual.getTercero().setSiguienteTriangulo(this.base.getPrimero());
        this.base.getPrimero().setSiguienteTriangulo(triangulo_actual.getPrimero());
        this.base.getSegundo().setSiguienteTriangulo(triangulo_actual.getSegundo());
        this.base.getTercero().setSiguienteTriangulo(triangulo_actual.getTercero());
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
    
    public void esquina1(Triangulo esquina1_actual){
        // Hago esto por mientras, me da un poquito de pereza adjjasd.
        for(Triangulo triangulo : this.triangulos){
            if((triangulo.getNivel() == (esquina1_actual.getNivel()-1) && (triangulo.getNumero() == 0))){
                Triangulo esquina1_anterior = triangulo;
            }
        }

        // Nunca cambian.
        esquina1_actual.getTercero().setSiguienteTriangulo(this.base.getPrimero());
        this.base.getPrimero().setSiguienteTriangulo(esquina1_actual.getPrimero());

        esquina1_anterior.getTercero().setSiguienteTriangulo(esquina1_actual.getTercero());
        esquina1_actual.getPrimero().setSiguienteTriangulo(esquina1_anterior.getPrimero());
    }
    
    public void apice(Triangulo apice_actual){
        for(Triangulo triangulo : this.triangulos){
            if((triangulo.getNivel() == (apice_actual.getNivel() - 1)) && (triangulo.getNumero() < Math.pow(2, triangulo.getNivel() - 1))){
                Triangulo apice_anterior = triangulo;
            }
        }

        // Nunca cambian
        apice_actual.getPrimero().setSiguienteTriangulo(this.base.getSegundo());
        this.base.getSegundo().setSiguienteTriangulo(apice_actual.getSegundo());

        apice_anterior.getPrimero().setSiguienteTriangulo(apice_actual.getPrimero());
        apice_actual.getSegundo().setSiguienteTriangulo(apice_anterior.getSegundo());
    }

    public void esquina2(Triangulo esquina2_actual){
        for(Triangulo triangulo : this.triangulos){
            if((triangulo.getNivel() == (esquina2_actual.getNivel() - 1)) && (triangulo.getNumero() == Math.pow(2, triangulo.getNivel() - 1))){
                Triangulo esquina2_anterior = triangulo;
            }
        }

        // Nunca cambian
        esquina2_actual.getSegundo().setSiguienteTriangulo(this.base.getTercero());
        this.base.getTercero().setSiguienteTriangulo(esquina2_actual.getSegundo());

        esquina2_anterior.getSegundo().setSiguienteTriangulo(esquina2_actual.getPrimero());
        esquina2_actual.getSegundo().setSiguienteTriangulo(esquina2_anterior.getSegundo());
    }

    public void arista1(Triangulo triangulo_actual){
        Triangulo triangulo_anterior = this.triangulos[];
        Triangulo triangulo_siguiente = this.triangulos[];

        triangulo_anterior.getPrimero().setSiguienteTriangulo(triangulo_actual.getPrimero());
        triangulo_actual.getPrimero().setSiguienteTriangulo(triangulo_siguiente.getPrimero());
    }

    public void arista2(Triangulo triangulo_actual){
        Triangulo triangulo_anterior = this.triangulos[];
        Triangulo triangulo_siguiente = this.triangulos[];

        triangulo_anterior.getSegundo().setSiguienteTriangulo(triangulo_actual.getSegundo());
        triangulo_actual.getSegundo().setSiguienteTriangulo(triangulo_siguiente.getSegundo());
    }

    public void base(Triangulo triangulo_actual){
        Triangulo triangulo_anterior = this.triangulos[];
        Triangulo triangulo_siguiente = this.triangulos[];

        triangulo_anterior.getTercero().setSiguienteTriangulo(triangulo_actual.getTercero());
        triangulo_actual.getTercero().setSiguienteTriangulo(triangulo_siguiente.getTercero());
    }

    // INSERTAR VERTICE---------------------------------------------------------------------------
    public boolean insertarVertice(int valor){
        for(Triangulo triangulo_actual : this.triangulos){
            if(triangulo_actual.getTercero() == null){
                if(triangulo_actual.getSegundo() == null){
                    triangulo_actual.setSegundo(new Vertice(valor));
                    return true; // Para no poner un break.
                }
                triangulo_actual.setTercero(new Vertice(valor));
                return true;
            }
        }

        // Si todos los triangulos estan llenos se crea uno nuevo.
        this.insertarTriangulo(valor);
        return true;
    }

    // BUSQUEDA ------------------------------------------------------------------------------
    /* 
    public Vertice busqueda(int valor){
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
        for(Triangulo triangulo_actual : this.triangulos){
            System.out.printf("Nivel: %d, Numero: %d, Primero: %d, Segundo: %d, tercero: %d,\n",
            triangulo_actual.getNivel(), triangulo_actual.getNumero(), triangulo_actual.getPrimero().getValor(),  
            triangulo_actual.getSegundo().getValor(), triangulo_actual.getTercero().getValor());
        }
    }
}
