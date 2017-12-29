#!/usr/bin/env python2

import sys
(out_pais, out_peticiones) = (None, 0)
for line in sys.stdin:
    (pais, peticiones) = line.strip().split()
    if out_pais and out_pais != pais:
        print "%s\t%s" %(out_pais, out_peticiones)
        (out_pais, out_peticiones) = (pais, int(peticiones))
    else:
        (out_pais, out_peticiones) = (pais, out_peticiones + int(peticiones))
if out_pais:
    print "%s\t%s" %(out_pais, out_peticiones)
