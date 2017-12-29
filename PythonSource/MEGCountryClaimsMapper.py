#!/usr/bin/env python2

import sys
(pais,peticiones ) = ("Sin determinar", 0)
for line in sys.stdin:
    valores = line.split(",")
    if valores[0] != "\"PATENT\"":
        pais = (valores[4].replace("\"","").encode('utf-8'))
        if valores[8] == "": peticiones = 0
        else: peticiones = int(valores[8])
        (pais, peticiones) = (pais, peticiones)
        print "%s\t%s" %(pais, peticiones)
