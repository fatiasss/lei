import os
print(os.getcwd())
for file in os.listdir(os.getcwd()):
    print(file,"tem", os.stat(file).st_size, "bytes")