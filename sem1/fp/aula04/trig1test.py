import math


def sin_a(x):
    
    """Return approximation to sin x."""
    # sin x = x - x³/3! + x⁵/5! - x⁷/7! + ...
    f = 0
    for numb in range (1, x+1):
        f += (((-1)**numb)/(math.factorial((numb*2)+1)))*(x)**((2*numb)+1)
    return(f)


def main():
    x=int(input("X?"))
    print(sin_a(x))
main()