# Luca Tkemura Piccoli
# Exercício 1 - Conjuntos

x = 0
with open('Conjuntos.txt', 'r') as f:
    while True:
        line = f.readline()
        if not line:
            break
        if x == 0:
            if "U" in line:
                mode = "União"
            if "I" in line:
                mode = "Interceção"
            if "D" in line:
                mode = "Diferença"
            if "C" in line:
                mode = "Produto cartesiano"
            x = 1
        elif x == 1:
            r = ""
            x = 0
            x1 = 0
            i = 0
            j = 0
            z = line.strip()
            line = f.readline()
            o = " " + line.strip() + ", " + z + ","
            while i < len(line.strip()) - j + 2:
                if o[i] != ",":
                    x += 1
                elif mode == "Produto cartesiano":
                    y = -len(z) - 2
                    while y < 0:
                        if o[y] != ",":
                            x1 += 1
                        else:
                            r = r + " (" + o[i - x + 1:i + 1] + o[y - x1:y] + ");"
                            x1 = 0
                        y += 1
                    x = 0
                elif o.count(o[i - x:i + 1]) > 1:
                    if mode != "Interceção":
                        temp = o[i - x:i + 1]
                        j += len(temp)
                        o = o[:i - x] + "" + o[i + 1:]
                        if mode == "Diferença":
                            o = o[:o.find(temp)] + "" + o[o.find(temp) + len(temp):]
                        i -= x + 1
                    x = 0
                else:
                    if mode == "Interceção":
                        temp = o[i - x:i + 1]
                        j += len(temp)
                        o = o[:i - x] + "" + o[i + 1:]
                        i -= x + 1
                    x = 0
                i += 1
            if mode == "Interceção":
                o = o[:-len(z) - 2]
            elif mode == "Produto cartesiano":
                o = r
            o = o[1:-1]
            print(mode + ": conjunto 1 {" + z + "}, conjunto 2 {" + line.strip() + "}. Resultado: {" + o + "}")
