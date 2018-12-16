# Documentación de Proyecto - Pokempilador
El traductor que realice consiste en un intérprete el cual por medio de la gramática colocada más adelante ejecutara cada uno de los ataques y movimientos de manera gráfica, siempre y cuando se cumpla con las reglas establecidas a continuación:

\<PROGRAMA> -> elegir-pokemon \<ORDENES> regresar-pokemon  
\<ORDENES> -> \<ORDEN> | \<SI> | \<REPETIR>  
\<ORDEN> -> \<MOVIMIENTO>|cargar-poder\<ATAQUE>  
\<ATAQUE> -> ataque | \<BOLAENERGIA> | \<BOLAFUEGO> | \<RAYO>  
\<MOVIMIENTO> -> brincar  
\<SI>-> si \<COMPARACION> entonces \<ORDENES> FIN-SI  
\<REPETIR> -> repetir \<NUMERO> veces \<ORDENES> fin repetir  
\<COMPARACION> -> ataco | se-movio

El intérprete simulara las ordenes que da un “entrenador pokemon” a su pokemon, de manera que estas podrán ordenarse mediante palabras del lenguaje natural que serán escritas por medio de un JTextArea en la interfaz gráfica del programa.

Se contara con órdenes de repetición en la cual el entrenador indicara el número de veces que se repetirán las acciones dentro de su alcance. Además de órdenes de selección donde el pokemon tomara decisiones en base a la acción previa que realizo.

Como fue mencionado anteriormente la interfaz gráfica cuenta con un JTextArea para ingresar el pseudocódigo, además de 2 botones los cuales son compilar y ejecutar.
