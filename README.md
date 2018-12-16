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


![enter image description here](https://lh3.googleusercontent.com/R8EpHY0k843ZCjDoNyUUXMYlvTGxhrhxB19A6oM1fXGxFzgrcaP_sswtUfrp89xJPlmLgyR3NQ3g)


El botón de compilar como su nombre lo indica compila el pseudocódigo, si esta escrito incorrectamente según la gramática, el Parser mandara un error en la línea de comandos y en la interfaz gráfica el pseudocódigo se coloreara de color rojo indicando error:


![enter image description here](https://lh3.googleusercontent.com/HvZvow8937i3H7lPpD-J4igTTItjJKfSl4pVSv-hwfaQoXow3rTV4YQHIMSX0EI5iLC2q3hTAlC9)


De manera contraria, si el código esta escrito correctamente este será coloreado de azul indicando que ya se puede ejecutar las instrucciones.


![enter image description here](https://lh3.googleusercontent.com/DYkXJxyhfp_V6uTAY36wFr2ekYzYLPP_7sfABvRaabtQg3qUxMuJrvQ4_UcAX7OdL1WTowVsMMi4)



Al dar click en Ejecutar y si el código esta compilado, el pokemon comenzara a realizar las acciones especificadas en el pseudocódigo.


![enter image description here](https://lh3.googleusercontent.com/yeh50NjtFm0pHA85sgU8nmZnPfcX3fct-hq5LcIIwsaBrUxrfWB4XFRshZk1Os06DI0PWZZneQbN)
