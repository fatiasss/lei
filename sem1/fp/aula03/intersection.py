
def intersection(a, b, c, d):
    assert a<=b
    assert c<=d
    if a<c:
        if b<c:
            it=False
            e = 0
            f = 0
        elif b<d:
            it=True
            e = c
            f = b
        else:
            it=True
            e = c
            f = d
    else:
        if d>=a:
            if d<=b:
                it=True
                e= a
                f= d
            else:
                it=True
                e= a
                f= b
        else:
            it=False
            e= 0 
            f= 0
    return (e,f)




def testIntersection(a, b, c, d, x, y):
    """Call intersection, print result and check against expected result."""
    print(f"intersection({a}, {b}, {c}, {d})", end=" ")
    (e, f) = intersection(a, b, c, d)
    check = "OK" if (e, f) == (x, y) else "FAIL"
    print(f"--> ({e}, {f}) {check}")


def main():
    testIntersection(1, 6, 4, 8,  4, 6)
    testIntersection(1, 6, 3, 5,  3, 5)
    testIntersection(0, 10, 11, 12,  0, 0)
    testIntersection(1, 6, 3, 5,  3, 5)
    # Acrescente mais casos de teste...
    ...


main()

