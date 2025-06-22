
def primesUpTo(n):
    numbers={a for a in range(2,n+1)}
    primes={a for a in range(2,n+1)}
    for divisor in numbers:
        for number in range(divisor**2, n+1):
            if number%divisor==0 and number in primes:
                primes.remove(number)
    return primes
    
def main():
    # Testing:
    s = primesUpTo(1000)
    print(s)

    # Do some checks:
    assert primesUpTo(30) == {2,3,5,7,11,13,17,19,23,29}
    assert len(primesUpTo(1000)) == 168
    assert len(primesUpTo(7918)) == 999
    assert len(primesUpTo(7919)) == 1000
    print("All tests passed!")

if __name__ == "__main__":
    main()

