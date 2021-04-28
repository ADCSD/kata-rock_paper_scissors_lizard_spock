# Desarrollo del requisito 7

## Requisito

!!! success "Las tijeras decapitan al lagarto"

## Desarrollo

Para este nuevo requisito, ya sabemos que debemos añadir la condición en un método concreto.

=== "TestRuleEngine"
    ``` Java linenums="1"
    @Test
    public void scissors_decapitates_lizard() {

      invokeAndCheckResult(PlayMove.SCISSORS, PlayMove.LIZARD, PlayResult.WIN_PLAYER_ONE);
      invokeAndCheckResult(PlayMove.LIZARD, PlayMove.SCISSORS, PlayResult.WIN_PLAYER_TWO);

    }
    ```
=== "RuleEngine"
    ``` Java linenums="1"
    private boolean isPlayWinner(PlayMove moveOne, PlayMove moveTwo) {

      if (moveOne.equals(PlayMove.SCISSORS) && moveTwo.equals(PlayMove.PAPER))
        return true;

      if (moveOne.equals(PlayMove.PAPER) && moveTwo.equals(PlayMove.ROCK))
        return true;

      if (moveOne.equals(PlayMove.ROCK) && moveTwo.equals(PlayMove.LIZARD))
        return true;

      if (moveOne.equals(PlayMove.LIZARD) && moveTwo.equals(PlayMove.SPOCK))
        return true;

      if (moveOne.equals(PlayMove.SPOCK) && moveTwo.equals(PlayMove.SCISSORS))
        return true;

      if (moveOne.equals(PlayMove.SCISSORS) && moveTwo.equals(PlayMove.LIZARD))
        return true;

      return false;

    }
    ```

Lo añadimos como una regla más y ya funciona el test. Pero dentro de ese código, tenemos algo de código duplicado, que podemos refactorizar.


=== "RuleEngine"
    ``` Java hl_lines="3 18" linenums="1"
    private boolean isPlayWinner(PlayMove moveOne, PlayMove moveTwo) {

      if (moveOne.equals(PlayMove.SCISSORS) && moveTwo.equals(PlayMove.PAPER))  /*(1)*/
        return true;

      if (moveOne.equals(PlayMove.PAPER) && moveTwo.equals(PlayMove.ROCK))
        return true;

      if (moveOne.equals(PlayMove.ROCK) && moveTwo.equals(PlayMove.LIZARD))
        return true;

      if (moveOne.equals(PlayMove.LIZARD) && moveTwo.equals(PlayMove.SPOCK))
        return true;

      if (moveOne.equals(PlayMove.SPOCK) && moveTwo.equals(PlayMove.SCISSORS))
        return true;

      if (moveOne.equals(PlayMove.SCISSORS) && moveTwo.equals(PlayMove.LIZARD)) /*(1)*/
        return true;

      return false;

    }
    ```
=== "RuleEngine-Ref1"
    ``` Java linenums="1"
    private boolean isPlayWinner(PlayMove moveOne, PlayMove moveTwo) {

      if (moveOne.equals(PlayMove.SCISSORS) && (moveTwo.equals(PlayMove.PAPER) || moveTwo.equals(PlayMove.LIZARD)))
        return true;

      if (moveOne.equals(PlayMove.PAPER) && moveTwo.equals(PlayMove.ROCK))
        return true;

      if (moveOne.equals(PlayMove.ROCK) && moveTwo.equals(PlayMove.LIZARD))
        return true;

      if (moveOne.equals(PlayMove.LIZARD) && moveTwo.equals(PlayMove.SPOCK))
        return true;

      if (moveOne.equals(PlayMove.SPOCK) && moveTwo.equals(PlayMove.SCISSORS))
        return true;

      return false;

    }
    ```

??? tip inline end "Refactoring"
    ***(1)*** La condición primera en ambas sentencias `if` es la misma, así que podríamos agruparlo en una sola línea. 


Algo nos dice que los futuros requisitos van a ir añadiendo condiciones dobles a todos los movimientos.