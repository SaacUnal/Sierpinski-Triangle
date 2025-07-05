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
This data structure consists in triangles that are conected between their vertices, and levels that are conected by a certain number of vertices of the triangle:

level 0 and 1 have 3 conections, level 2 has 2 conections and after level 3 each triangle has 1 conection except the corner triangles that have 2. Level 0 and 1 behave almost the same.

After the level 0, each level has 3^n universes maximum.

This is how the basic triangle works:

![image](https://github.com/user-attachments/assets/0843d112-bd0c-489a-9ced-d43c17bf9f70)

And this is how a compound triangle works:
![image](https://github.com/user-attachments/assets/2c27a213-5ea2-4967-83e9-d83d093b0676)


# Aplication
Now in the code.
- There is a tail: the first universe of the level 0 and a head: the universe number 3*2^n-1 of the last universe.
- The multiverse has two options to create: a complete triangle with 3 vertices or a triangle with one vertex or just one universe.
- The delete options are the same: delete a complete triangle or delete one vertex.
- The order of the data flow is clockwise.
- Each universe 
- Since the first universe until the universe number 3*2^n-1 of each level (except 0), it has a conection with another level.
- 


