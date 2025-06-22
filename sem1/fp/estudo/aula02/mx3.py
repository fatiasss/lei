def mx(x1,x2):
    if x1 > x2:
        return(x1)
    elif x1<x2:
        return(x2)
    else:
        return(x1)
    
def mx3(x1, x2, x3):

    if mx(x1,x2)>mx(x2,x3):
        return(mx(x1,x2))
    elif mx(x1,x2)<mx(x2,x3):
        return(mx(x2,x3))
    else:
        return x2
    
def mx4(x1, x2, x3, x4):
    if mx(x1,x2)>mx(x2,x3) and mx(x1,x2)>mx(x3,x4):
        return mx(x1,x2)
    elif mx(x1,x2)>mx(x2,x3) and mx(x1,x2)<mx(x3,x4):
        return(mx(x3,x4))
    elif mx(x2,x3)<mx(x3,x4):
        return mx(x3,x4)
    else:
        return mx(x2,x3)
    
def main():
    x1 = float(input("number? "))
    x2 = float(input("number? "))
    x3 = float(input("number? "))
    x4 = float(input("number? "))
    print(mx3(x1,x2,x3))
    print(mx4(x1,x2,x3,x4))
main()