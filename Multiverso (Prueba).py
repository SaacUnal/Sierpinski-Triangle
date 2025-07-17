import matplotlib.pyplot as plt
import numpy as np
import os

class Vertice:
    def __init__(self, valor):
        self.valor = valor
        self.siguiente_i = None  # Enlace interno dentro del triángulo
        self.siguiente_p = None  # Enlace de conexión con otro triángulo

    def __repr__(self):
        return f"V{self.valor}"

class Triangulo:
    def __init__(self, nivel, vertice_inicial):
        self.cabeza = Vertice(vertice_inicial)
        self.primero = Vertice(vertice_inicial + 1)
        self.segundo = Vertice(vertice_inicial + 2)
        self.nivel = nivel

        # Conectar los vértices internamente
        self.cabeza.siguiente_i = self.primero
        self.primero.siguiente_i = self.segundo
        self.segundo.siguiente_i = self.cabeza

    def vertices(self):
        return [self.cabeza, self.primero, self.segundo]

class Sierpinski:
    def __init__(self):
        self.base = Triangulo(0, 0)
        self.triangulos = [self.base]
        self.subdivisiones = 1
        self.vertice_id_counter = 3

    def insertar_siguiente(self):
        cantidad = 3 ** self.subdivisiones
        for _ in range(0, cantidad, 3):
            T1 = Triangulo(self.subdivisiones, self.vertice_id_counter)
            self.vertice_id_counter += 3
            # Conectar nuevos vértices con los anteriores (siguiente_p)
            T1.cabeza.siguiente_p = self.base.cabeza.siguiente_i
            self.base.cabeza.siguiente_i = T1.cabeza
            T1.primero.siguiente_p = self.base.primero.siguiente_i
            self.base.primero.siguiente_i = T1.primero
            T1.segundo.siguiente_p = self.base.segundo.siguiente_i
            self.base.segundo.siguiente_i = T1.segundo
            self.triangulos.append(T1)
        self.subdivisiones += 1

    def eliminar_ultimo(self):
        if self.subdivisiones > 1:
            # Calcular cuántos triángulos se agregaron en el último nivel
            # Para subdivisiones=1: se agrega 1 triángulo
            # Para subdivisiones=2: se agregan 3 triángulos  
            # Para subdivisiones=3: se agregan 9 triángulos
            # Patrón: 3^(subdivisiones-1) / 3 = 3^(subdivisiones-2) para subdivisiones > 1
            if self.subdivisiones == 2:
                triangulos_a_eliminar = 1  # Primer nivel agregó 1 triángulo
            else:
                triangulos_a_eliminar = 3 ** (self.subdivisiones - 2)
            
            # Eliminar esa cantidad de triángulos del final
            for _ in range(triangulos_a_eliminar):
                if len(self.triangulos) > 1:
                    self.triangulos.pop()
                    self.vertice_id_counter -= 3
            
            self.subdivisiones -= 1

    def agregar_universos(self, cantidad):
        for _ in range(cantidad):
            self.insertar_siguiente()

    def eliminar_universos(self, cantidad):
        for _ in range(cantidad):
            self.eliminar_ultimo()

    def total_triangulos(self):
        return len(self.triangulos) - 1  # Excluir el triángulo base
    
    def nivel_actual(self):
        return self.subdivisiones - 1
    
    def info_debug(self):
        return {
            'subdivisiones': self.subdivisiones,
            'total_triangulos_lista': len(self.triangulos),
            'triangulos_sin_base': self.total_triangulos(),
            'vertice_counter': self.vertice_id_counter
        }  

def draw_sierpinski_comp(ax, x, y, size, depth, triangulos, idx_tracker):
    if depth == 0:
        if idx_tracker[0] < len(triangulos):
            tri = triangulos[idx_tracker[0]]
            v = tri.vertices()
            idx_tracker[0] += 1
            coords = [
                (x, y),
                (x + size / 2, y + size * np.sqrt(3) / 2),
                (x + size, y)
            ]
            triangle = plt.Polygon(coords, edgecolor='black', fill=False)
            ax.add_patch(triangle)

            # Dibujar vértices y conexiones
            for pos, vert in zip(coords, v):
                ax.text(pos[0], pos[1], str(vert), ha='center', va='center', fontsize=6, color='blue')

                # Conexiones internas (verde)
                if vert.siguiente_i:
                    dest = next((p for p, ve in zip(coords, v) if ve == vert.siguiente_i), None)
                    if dest:
                        ax.annotate("", xy=dest, xytext=pos,
                                    arrowprops=dict(arrowstyle="->", color="green", lw=1))

                # Conexiones de portal (rojo punteado)
                if vert.siguiente_p:
                    ax.annotate("", xy=(pos[0], pos[1] - 0.02), xytext=(pos[0], pos[1] - 0.1),
                                arrowprops=dict(arrowstyle="->", color="red", lw=1, linestyle="dotted"))
    else:
        # Subdivisión en 3 partes
        half = size / 2
        height = size * np.sqrt(3) / 2 / 2
        draw_sierpinski_comp(ax, x, y, half, depth - 1, triangulos, idx_tracker)
        draw_sierpinski_comp(ax, x + half / 2, y + height, half, depth - 1, triangulos, idx_tracker)
        draw_sierpinski_comp(ax, x + half, y, half, depth - 1, triangulos, idx_tracker)

# -------- USO --------

# Crear el multiverso
s = Sierpinski()
niveles_totales = 0  # Variable para recordar los niveles creados

def cls():
    os.system('cls' if os.name == 'nt' else 'clear')

cls()  # Limpiar la consola (opcional, para una mejor visualización)
print("\n=== MULTIVERSO SIERPINSKI ===")
print("\nEstado inicial: 0 niveles creados")

# Input de usuario para agregar o eliminar universos
while True:
    print(f"\n📊 Estado actual: {niveles_totales} niveles | {s.total_triangulos()} universos | {len(s.triangulos)-1} triángulos totales")
    accion = input("¿Qué deseas hacer? (agregar/eliminar/visualizar/salir): ").lower()
    
    if accion == "agregar":
        cantidad = int(input("¿Cuántos niveles deseas agregar? "))
        s.agregar_universos(cantidad)
        niveles_totales += cantidad
        print(f"✓ Agregados {cantidad} niveles. Total actual: {niveles_totales} niveles")
        
    elif accion == "eliminar":
        cantidad = int(input("¿Cuántos niveles deseas eliminar? "))
        # Validar que no se eliminen más niveles de los que existen
        if cantidad > niveles_totales:
            print(f"\n⚠️  No puedes eliminar {cantidad} niveles. Solo tienes {niveles_totales} niveles.")
            continue
        s.eliminar_universos(cantidad)
        niveles_totales -= cantidad
        print(f"✓ Eliminados {cantidad} niveles. Total actual: {niveles_totales} niveles")
        
    elif accion == "visualizar":
        if niveles_totales > 0:
            # Dibujar el resultado
            fig, ax = plt.subplots(figsize=(10, 10))
            draw_sierpinski_comp(ax, 0, 0, 1, depth=niveles_totales-1, triangulos=s.triangulos, idx_tracker=[0])
            ax.set_aspect('equal')
            ax.axis('off')
            plt.title(
                f"Multiverso Sierpinski [Niveles: {niveles_totales}, Total triángulos (Universos): {s.total_triangulos()}]\n"
                "Verde: Conexiones internas de un universo\nRojo: Punto de conexión entre universos"
            )
            plt.show()
        else:
            print("\n⚠️ No hay niveles para visualizar. Agrega algunos niveles primero.")
            
    elif accion == "salir":
        print("\n¡Hasta luego! Tu multiverso tenía", niveles_totales, "niveles al final.\n")
        break
        
    else:
        print("\n❌ Acción no válida. Por favor elige: agregar, eliminar, visualizar o salir.")
