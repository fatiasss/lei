secs = int(input("Segundos?"))
h = secs//3600
m = (secs - (h*3600))//60
s = (secs - (h*3600) - (m*60))
print("{:02d}:{:02d}:{:02d}".format(h, m, s))