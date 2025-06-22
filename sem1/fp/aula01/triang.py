import math
x = float(input("Primeiro cateto?"))
y = float(input("Segundo cateto?"))
h = math.hypot(x, y)
ang = math.acos(x/h)
print("O comprimento da primeira hipotnusa é", h, "e o ângulo entre o primeiro cateto e esta é de", ang, "radianos")
