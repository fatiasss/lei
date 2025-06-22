def countl(filename):
    letters=dict()
    with open(filename, "r") as fileobj:
        while True:
            line = fileobj.readline()
            if line=="":break
            for char in str(line):
                if char not in letters:
                    letters[char]=1
                else:
                    letters[char]+=1
    for item in sorted(letters, key=lambda x: letters[x], reverse=False):
        print(item, letters[item])

def main():
    filename=str(input("File?"))
    countl(filename)

main()
        
