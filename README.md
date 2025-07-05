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
![image](https://github.com/user-attachments/assets/0656532c-09d3-45d6-a56e-b53bbb960e5a)


# Aplication
- The order of the data flow is clockwise.
- There is a tail: the first universe of the level 0 and a head: the third universe of the last level. The head is conected to the tail.
- The universes of the level 0 are not directly connected but we save their position to divide the data structure in 3 parts: from tail to first third, from first third to second third and from second thirdo to head.
- Each universe has a direct conection with another universe of the same level, and aditional can has a direct conection with a universe of another level.
- The multiverse has two options to create: a complete triangle with 3 vertices or a triangle with one vertex (just one universe).
- When a triangle is added: it is created between 2 universes that dont belong to the same level of the triangle. If there are 3^n-1 triangles created, it starts a new level.
- When a universe is added: it tries to fullfill a triangle first, if the triangles are all full, it creates another triangle.
- The delete options are the same: delete a complete triangle or delete one vertex.

These are the conections:
![image](https://github.com/user-attachments/assets/7bb99066-a484-4f02-8012-b52f05ce7f9c)



