# Multiverse of DataStructures
For this proyect the objective is to make a unique and original type of data structure. 
This is going to be called a multiverse, the idea is to make posible to travel betwen universes following a set of rules.

# Rules
1. A universe has a maximum of 6 direct conections with other universes.
2. Theres no way back when traveling. So the conections are unilateral.
3. The multiverse has a minimum of 36 universes.
4. These universes can be created and destroyed.

# Sierpinski Triangle
We propose a data structure that reassembles a Sierpinski triangle, an example of a fractal.
This data structure consists in triangles that are conected between their vertices, and levels that are conected by triangles:



This is how the basic triangle works:
<img width="785" height="281" alt="image" src="https://github.com/user-attachments/assets/1f31db3d-52bc-4139-b99b-add8503b4476" />


And this is how a compound triangle works:
<img width="2605" height="2305" alt="image" src="https://github.com/user-attachments/assets/d4d4e486-4e74-4ee6-8a4f-e3b1f4c89124" />


# Aplication
Data:
- The order of the data flow is clockwise.
- There is a tail: The level 0 and a head: the last universe of the last level. The head is conected to the tail.
- The triangles are divided in (First corner, first edge, apex, second edge, second corner, base and inner).

<img width="2661" height="421" alt="image" src="https://github.com/user-attachments/assets/cb3a5eb4-24e0-45a1-a3d8-4c4bad4bf332" />

Conections:
- Each universe has a direct conection with the next universe of the same level, and 3 conections between their vertices.
- Level 0 and 1 have 6 conections, 3 inside them and 3 between them. They behave almost the same.
- Level 2 has 6 conections, the corners have 3 inside it, 2 between triangles and 1 between the same level.
- Level 3 has between 6 and 5 conections, the edge triangles have 6 and the other have 3 inside them, 1 between triangles and 1 between the same level.
- After the level 3, each triangle can have 6, 5 or 4 conections, the inner triangles have 3 conections inside them and 1 between the same level. (thats why there are special cases in the code)

<img width="2661" height="421" alt="image" src="https://github.com/user-attachments/assets/9f462488-6f36-4e0d-bf7a-695b53123bb0" />


Functions:
- The multiverse can create a complete triangle with 3 vertices but only the first has a value, the rest are empty.
- When a triangle is added: The conections between itself and the conections within the big triangle are fixed.
- When a vertex is added: It tries to fullfill a triangle first, if all triangles are all full, it creates another triangle.
- Theres a function to search a vertex, this is how the multiverse takes form.



