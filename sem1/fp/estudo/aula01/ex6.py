secin = int(input("Segundos?"))
h= secin//3600
m= (secin - h*3600)//60
s= int(secin - h*3600 - m*60)

print("{:02d}:{:02d}:{:02d}".format(h, m, s))