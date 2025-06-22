# Exercise 5 on "How to think like a computer scientist", ch. 11.

import turtle

def main():
    screen = turtle.Screen()
    t = turtle.Turtle()
    while True:
        with open("drawing.txt", "r") as fileobj:
            line = fileobj.readline()
            if line == "UP":
                t.up()
            elif line =="DOWN":
                t.down()
            elif line!="":
                lst=line.split(" ")
                t.goto(lst[0], lst[1])
            else:
                break

    # Use t.up(), t.down() and t.goto(x, y)

    # Put your code here
    ...

    # Wait until window is closed
    screen.mainloop()


if __name__ == "__main__":
    main()

