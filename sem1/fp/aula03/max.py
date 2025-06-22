def maximum(x,y):
    if x>y:
        max=x
    elif y>x:
        max=y
    else:
        max=x
    return max

def max3(x, y, z):
    if maximum(x,y)>maximum(y,z):
        max = maximum(x,y)
    elif maximum(x,y)<maximum(y,z):
        max = maximum(y,z)
    else:
        max = y
    return max

def main():
    n1=float(input("First number?"))
    n2=float(input("Second number?"))
    n3=float(input("Third number?"))
    print(maximum(n1,n2), "is larger number of the first two", )
    print(max3(n1,n2,n3), "is larger number of the three", )
main()
