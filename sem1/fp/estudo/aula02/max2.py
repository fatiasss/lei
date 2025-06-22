# coding: utf-8
# This program finds the maximum of two numbers.
# It uses the built-in max function.
# Can you do it with a conditional statement (if / if-else) instead?

x1 = float(input("number? "))
x2 = float(input("number? "))

# Use a conditional statement instead of max function!
def mx(x1,x2):
    if x1 > x2:
        return(x1)
    elif x1<x2:
        return(x2)
    else:
        return("the numbers are equal")

print("Maximum:", mx(x1,x2))

