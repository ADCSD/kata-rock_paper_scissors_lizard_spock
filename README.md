# Kata Rock Paper Scissors Lizard Spock

Esta Kata está basada en el juego modificado que aparece en la serie The Big Bang Theory. Se trata del clásico juego *Piedra, papel o tijera*, pero modificado con dos nuevos movimientos para aumentar el número de posibilidades en el juego.

Es una Kata sencilla de implementar y que ofrece la posibilidad de refactorizar el *switch statement* que nos irá apareciendo a lo largo de la implementación. 

## Consejos

+ Haremos solo una tarea a la vez. Tenemos que aprender a trabajar de forma incremental.
+ Para esta kata solo vamos a testear las entradas correctas. Por agilidad en la session.
+ Llegaremos hasta donde nos de tiempo. No se trata de terminarlo, sino de aprender durante el proceso.
+ Programaremos entre todos. No existe una solución correcta y una incorrecta, se puede hacer de muchas formas.

No te olvides de pasar los tests a cada cambio que se efectue del código, para verificar que funciona todo correctamente y que no se ha roto nada más.

## Reglas

Las reglas son sencillas, al igual que el juego original, dos jugadores muestran a la vez un movimiento de entre 5 posibles (Piedra, Papel, Tijeras, Lagarto, Spock), si los movimientos son iguales el resultado de la partida es empate, pero si los movimientos se calculará la victoria en función de las siguientes reglas:


+ `Tijeras` cortan el `Papel`
+ `Papel` recubre la `Piedra`
+ `Piedra` aplasta al `Lagarto`
+ `Lagarto` envenena a `Spock`
+ `Spock` rompe las `Tijeras`
+ `Tijeras` decapitan al `Lagarto`
+ `Lagarto` se come el `Papel`
+ `Papel` desaprueba a `Spock`
+ `Spock` vaporiza la `Piedra`
+ `Piedra` aplasta `Tijeras`

Se pide hacer un algoritmo que dados dos movimientos calcule cual será el resultado de la partida.

## Fuente

La kata ha sido extraida de [The Big Bang Theory](https://bigbangtheory.fandom.com/wiki/Rock,_Paper,_Scissors,_Lizard,_Spock)
