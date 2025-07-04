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
This data structure consists in triangles that are conected between their vertices, and levels that are conected by one vertex of the triangle after the level 3:
(0 has 3 conections, 1 has 3 conections, 2 has 2 conections and after 3 it has 1 conection except the corner triangles that has 2 conections).
After the level 0, each level has 3^n universes maximum.

This is how the basic triangle works:

![image](https://github.com/user-attachments/assets/0843d112-bd0c-489a-9ced-d43c17bf9f70)

And this is how a compound triangle works (1 is the level 0, 2 is the level 1 and so on):
![image](https://github.com/user-attachments/assets/56039b75-92d3-443a-9287-7a8bd3d71ef1)

# Aplication
Now in the code 
- There is a tail: the first universe of the level 0 and a head: the universe number 3*2^n-1 of the last universe.
- The multiverse has two options to create: a complete triangle with 3 vertices or a triangle with one vertex or just one universe.
- The delete options are the same: delete a complete triangle or delete one vertex.
- The


