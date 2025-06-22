def max2(x,y):
    if x>y:
        return x
    else:
        return y

def max3(x,y,z):
    if max(x,y) > max(y,z):
        return max(x,y)
    else:
        return max(y,z)



def main():
    x = float(input("x"))
    y = float(input("y"))
    z = float(input("z"))
    print(max2(x,y))
    print(max(x,y))
    print(max3(x,y,z))
    print(max(x,y,z))
