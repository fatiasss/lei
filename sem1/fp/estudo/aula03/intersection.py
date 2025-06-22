
def intersection(a, b, c, d):
    """Return the intersection of intervals [a, b[ and [c, d[."""
    assert a <= b
    assert c <= d
    if a<d:
        if c<b:
            return (c, min(b,d))
        else:
            return (0,0)
    else:
        if a<c:
            return (a, min(b,c))
        else:
            return(0, 0)


def testIntersection(a, b, c, d, x, y):
    """Call intersection, print result and check against expected result."""
    print(f"intersection({a}, {b}, {c}, {d})", end=" ")
    (e, f) = intersection(a, b, c, d)
    check = "OK" if (e, f) == (x, y) else "FAIL"
    print(f"--> ({e}, {f}) {check}")


def main():
    testIntersection(1, 6, 4, 8,  4, 6)
    testIntersection(1, 6, 3, 5,  3, 5)
    # Acrescente mais casos de teste...
    ...


main()

