

s = int(input("Quantos segundos demorou a chamada?"))
m = s//60
if m==0:
    print("A chamada custou 0.12 euros")
else:
    print("A chamada custou", (0.12/60)*s,"euros")
