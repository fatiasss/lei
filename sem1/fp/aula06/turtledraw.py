# Exercise 5 on "How to think like a computer scientist", ch. 11.

import turtle

def main():
    screen = turtle.Screen()
    t = turtle.Turtle()

    # Use t.up(), t.down() and t.goto(x, y)

    # Put your code here
    with open("drawing.txt", "r") as fileobj:
        while True:
            line= fileobj.readline()
            if line.strip("\n")=="": break
            if line.strip("\n")=="UP":
                t.up()
            elif line.strip("\n")=="DOWN":
                t.down()
            else:
                coords = line.strip("\n").split(" ")
                t.goto(int(coords[0]), int(coords[1]))



    # Wait until window is closed
    screen.mainloop()


if __name__ == "__main__":
    main()

