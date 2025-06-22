def prime(n):
    d =2
    while d<n:
        if (n%d)==0: #so n is divisible by i 
            return("Not prime")
            break
        d+=1
    return("prime")

def main():
    for i in range(0, 101):
        print(i, "-", prime(i))

main()