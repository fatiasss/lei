# Este programa demonstra a leitura e utilização de dados de um ficheiro JSON
# com mensagens do Twitter.
# Modifique-o para resolver o problema proposto.

# O módulo json permite descodificar ficheiros no formato JSON.
# São ficheiros de texto que armazenam objetos compostos que podem incluir
# números, strings, listas e/ou dicionários.
import json


def main():
    # Abre o ficheiro e descodifica-o criando o objeto twits.
    with open("twitter.json", encoding="utf8") as f:
        twits = json.load(f)

    # Analise os resultados impressos para perceber a estrutura dos dados.
    print(type(twits))       # deve indicar que é uma lista!
    print(type(twits[0]))    # cada elemento da lista é um dicionário.
    print(twits[0].keys())   # mostra as chaves no primeiro elemento.

    # Cada elemento contém uma mensagem associada à chave "text":
    print(twits[0]["text"])

    # Algumas mensagens contêm hashtags:
    print(twits[880]["text"])

    textlst=[twits[x]["text"] for x in range(len(twits))]
    wordslst=[]
    wordsdict={}
    hashlst=[]
    hashdict={}
    for text in textlst:
        words=text.strip("\n")
        words=words.split(" ")
        for word in words:
            if word not in wordslst:
                wordslst.append(word)
            if word not in wordsdict:
                wordsdict[word] = 1
            else:
                wordsdict[word]+=1
    wordslst=sorted(wordslst, key=lambda t: wordsdict[t], reverse=True)
    print("WORDSLISTTTTT",wordslst)
    print(wordsdict)

    for text in textlst:
        words=text.strip("\n")
        words=words.split(" ")
        for word in words:
            if word!="":
                if word[0]=="#":
                    if word not in hashlst:
                        hashlst.append(word)
                    if word not in hashdict:
                        hashdict[word] = 1
                    else:
                        hashdict[word]+=1
    hashlst=sorted(hashlst, key=lambda t: hashdict[t], reverse=True)
    print("hashlist",hashlst)
    print(hashdict)
    mchashtotal= hashdict[hashlst[0]]
    base=18//mchashtotal
    print(mchashtotal)
    for hash in hashlst:
        plustr = "+"*hashdict[hash]*base
        print("{:<12} ({}) {:>12} ".format(hash,hashdict[hash], plustr))
if __name__ == "__main__":
    main()

