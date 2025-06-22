# You throw a dart that hits coordinates (x, y) on a dartboard.
# Create a program that gives you the score.
# See:
#   https://en.wikipedia.org/wiki/Darts#Dartboard
#   https://www.dimensions.com/element/dartboard
import math

print("Enter the coordinates in mm from the center of the board.")
x = float(input("x? "))
y = float(input("y? "))
r2 = x**2 + y**2
pi = math.pi
# Points of the sectors, clockwise from the top
# Example: the sector right from the center is POINTS[5] == 6.
POINTS = (20, 1, 18, 4, 13, 6, 10, 15, 2, 17, 3, 19, 7, 16, 8, 11, 14, 9, 12, 5)
RAD =(12.7, 32, 99, 107, 162,170)


if y> math.tan((-pi/20))*x and y<=math.tan((pi/20))*x:
    sec = POINTS[5]
elif y> math.tan((-pi/20)-(pi/10))*x and y<=math.tan((-pi/20))*x:
    sec = POINTS[6]
elif y> math.tan((-pi/20)-(pi/10)*2)*x and y<=math.tan((-pi/20)-(pi/10))*x:
    sec = POINTS[7]
elif y> math.tan((-pi/20)-(pi/10)*3)*x and y<=math.tan((-pi/20)-(pi/10)*2)*x:
    sec = POINTS[8]
elif y> math.tan((-pi/20)-(pi/10)*4)*x and y<=math.tan((-pi/20)-(pi/10)*3)*x:
    sec = POINTS[9]
elif y> math.tan((-pi/20)-(pi/10)*5)*x and y<=math.tan((-pi/20)-(pi/10)*4)*x:
    sec = POINTS[10]
elif y> math.tan((-pi/20)-(pi/10)*6)*x and y<=math.tan((-pi/20)-(pi/10)*5)*x:
    sec = POINTS[11]
elif y> math.tan((-pi/20)-(pi/10)*7)*x and y<=math.tan((-pi/20)-(pi/10)*6)*x:
    sec = POINTS[12]
elif y> math.tan((-pi/20)-(pi/10)*8)*x and y<=math.tan((-pi/20)-(pi/10)*7)*x:
    sec = POINTS[13]
elif y> math.tan((-pi/20)-(pi/10)*9)*x and y<=math.tan((-pi/20)-(pi/10)*8)*x:
    sec = POINTS[14]
elif y> math.tan((-pi/20)-(pi/10)*10)*x and y<=math.tan((-pi/20)-(pi/10)*9)*x:
    sec = POINTS[15]
elif y> math.tan((-pi/20)-(pi/10)*11)*x and y<=math.tan((-pi/20)-(pi/10)*10)*x:
    sec = POINTS[16]
elif y> math.tan((-pi/20)-(pi/10)*12)*x and y<=math.tan((-pi/20)-(pi/10)*11)*x:
    sec = POINTS[17]
elif y> math.tan((-pi/20)-(pi/10)*13)*x and y<=math.tan((-pi/20)-(pi/10)*12)*x:
    sec = POINTS[18]
elif y> math.tan((-pi/20)-(pi/10)*14)*x and y<=math.tan((-pi/20)-(pi/10)*13)*x:
    sec = POINTS[19]
elif y> math.tan((-pi/20)-(pi/10)*15)*x and y<=math.tan((-pi/20)-(pi/10)*14)*x:
    sec = POINTS[20]
elif y> math.tan((-pi/20)-(pi/10)*16)*x and y<=math.tan((-pi/20)-(pi/10)*15)*x:
    sec = POINTS[0]
elif y> math.tan((-pi/20)-(pi/10)*17)*x and y<=math.tan((-pi/20)-(pi/10)*16)*x:
    sec = POINTS[1]
elif y> math.tan((-pi/20)-(pi/10)*18)*x and y<=math.tan((-pi/20)-(pi/10)*17)*x:
    sec = POINTS[2]
elif y> math.tan((-pi/20)-(pi/10)*19)*x and y<=math.tan((-pi/20)-(pi/10)*18)*x:
    sec = POINTS[3]
elif y> math.tan((-pi/20)-(pi/10)*20)*x and y<=math.tan((-pi/20)-(pi/10)*19)*x:
    sec = POINTS[4]

if r2<=(12.7/2)**2:
    score = 50
elif r2<=(32/2)**2:
    score = 25
elif r2<=((107-8)**2):
    mult = 1
    score = sec*mult
elif r2<=(107**2):
    mult = 3
    score = sec*mult
elif r2<=((170-8)**2):
    mult = 1
    score = sec*mult
elif r2<=(170**2):
    mult = 2
    score = sec*mult
else:
    score = 0

print(score)
