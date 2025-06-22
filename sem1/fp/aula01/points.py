import math


# This program reads the coordinates of two points (x1,y1) and (x2,y2).

x1 = float(input("x1? "))
y1 = float(input("y1? "))
x2 = float(input("x2? "))
y2 = float(input("y2? "))

# create tuples from coordinates
p1 = (x1, y1)
p2 = (x2, y2)


#Calculate distance
d = math.sqrt ( (p1[0] - p2[0])**2 + (p1[1] - p2[1])**2 )

print("Point1:", p1)
print("Point2:", p2)
print("Distance", d)

# Compute and print the distance between the points!
# ...

