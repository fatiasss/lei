def time(s):
    d=s//86400
    h=(s%86400)//3600
    m=((s%86400)%3600)//60
    s=((s%86400)%3600)%60
    if d!=0:
        print("{} dias, {} horas, {} minutos, {} segundos".format(d,h,m,s))
    elif h!=0:
        print("{} horas, {} minutos, {} segundos".format(h,m,s))
    elif m!=0:
        print("{} minutos, {} segundos".format(m,s))
    else:
        print("{} segundos".format(s))
def main():
    i =int(input("seg"))
    time(i)

main()