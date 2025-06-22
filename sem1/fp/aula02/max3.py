# coding: utf-8
# This program finds the maximum of two numbers.
# It uses the built-in max function.
# Can you do it with a conditional statement (if / if-else) instead?

x1 = float(input("number? "))
x2 = float(input("number? "))
x3 = float(input("number? "))
x4 = float(input("number? "))
# Use a conditional statement instead of max function!
if x1 > x2:
    if x1 > x3:
        if x1> x4:
            print("Max", x1)
        else:
            print("Max", x4)
    elif x3> x4:
        print("Max", x3)
    else:
        print("Max", x4)
elif x2> x3:
    if x2> x4:
        print("Max", x2)
    else:
        print("Max", x4)
elif x3>x4:
    print("Max", x3)
else:
    print("Max", x4)


