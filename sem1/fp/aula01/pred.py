a = int(input("Quantos andares tem o prédio (r/c conta)?"))
m = int(input("Quantos moradores vivem em cada andar?"))
d = ((12 * m * (a-1))*a)/2
dan = (d*365)
t = dan/3600
print( "O elevador percorre", dan/1000, "km, em", t, "horas.")