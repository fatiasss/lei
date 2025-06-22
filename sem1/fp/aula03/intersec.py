def intersects(a, b, c, d):
    assert a<b
    assert c<d
    if a<c:
        if b<c:
            it=False
        else:
            it=True
    else:
        if d>=a:
            it=True
        else:
            it=False
    return it

def main():
    n1= float(input("Beginning of the first interval?"))
    n2= float(input("End of the first interval?"))
    n3= float(input("Beginning of the second interval?"))
    n4= float(input("End of the second interval?"))
    print("It s", intersects(n1, n2, n3, n4), "that the intervals intersect")

main()

