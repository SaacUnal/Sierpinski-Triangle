import matplotlib.pyplot as plt
import numpy as np

class Vertice:
    def __init__(self, valor):
        self.valor = valor
        self.siguiente_i = None
        self.siguiente_p = None

    def __repr__(self):
        return f"V{self.valor}"

class Triangulo:
    def __init__(self, nivel, vertice_inicial):
        self.cabeza = Vertice(vertice_inicial)
        self.primero = Vertice(vertice_inicial + 1)
        self.segundo = Vertice(vertice_inicial + 2)
        self.nivel = nivel
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
            T1.cabeza.siguiente_p = self.base.cabeza.siguiente_i
            self.base.cabeza.siguiente_i = T1.cabeza
            T1.primero.siguiente_p = self.base.primero.siguiente_i
            self.base.primero.siguiente_i = T1.primero
            T1.segundo.siguiente_p = self.base.segundo.siguiente_i
            self.base.segundo.siguiente_i = T1.segundo
            self.triangulos.append(T1)
        self.subdivisiones += 1

    def eliminar_ultimo(self):
        if len(self.triangulos) > 1:
            self.triangulos.pop()
            self.vertice_id_counter -= 3

    def total_triangulos(self):
        return len(self.triangulos)

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
            for pos, vert in zip(coords, v):
                ax.text(pos[0], pos[1], str(vert), ha='center', va='center', fontsize=6, color='blue')
                if vert.siguiente_i:
                    dest = next((p for p, ve in zip(coords, v) if ve == vert.siguiente_i), None)
                    if dest:
                        ax.annotate("", xy=dest, xytext=pos,
                                    arrowprops=dict(arrowstyle="->", color="green", lw=1))
                if vert.siguiente_p:
                    ax.annotate("", xy=(pos[0], pos[1] - 0.02), xytext=(pos[0], pos[1] - 0.1),
                                arrowprops=dict(arrowstyle="->", color="red", lw=1, linestyle="dotted"))
    else:
        half = size / 2
        height = size * np.sqrt(3) / 2 / 2
        draw_sierpinski_comp(ax, x, y, half, depth - 1, triangulos, idx_tracker)
        draw_sierpinski_comp(ax, x + half / 2, y + height, half, depth - 1, triangulos, idx_tracker)
        draw_sierpinski_comp(ax, x + half, y, half, depth - 1, triangulos, idx_tracker)


s = Sierpinski()
s.insertar_siguiente()
s.insertar_siguiente()
s.insertar_siguiente()
s.eliminar_ultimo()

fig, ax = plt.subplots(figsize=(10, 10))
draw_sierpinski_comp(ax, 0, 0, 1, depth=2, triangulos=s.triangulos, idx_tracker=[0])
ax.set_aspect('equal')
ax.axis('off')
plt.title(
    f"Multiverso Sierpinski [Total triángulos (Universos): {s.total_triangulos()}]\n"
    "Verde: Conexiones internas de un universo\nRojo: Punto de conexión entre universos"
)
plt.show()
