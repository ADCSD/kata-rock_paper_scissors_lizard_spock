# Desarrollo del requisito 8

## Requisito

!!! success "El lagarto se come el papel"

## Desarrollo

Nuevo requisito que podemos agrupar.

=== "TestRuleEngine"
    ``` Java linenums="1"
    @Test
    public void lizard_eats_paper() {

      invokeAndCheckResult(PlayMove.LIZARD, PlayMove.PAPER, PlayResult.WIN_PLAYER_ONE);
      invokeAndCheckResult(PlayMove.PAPER, PlayMove.LIZARD, PlayResult.WIN_PLAYER_TWO);

    }
    ```
=== "RuleEngine"
    ``` Java linenums="1"
    private boolean isPlayWinner(PlayMove moveOne, PlayMove moveTwo) {

      if (moveOne.equals(PlayMove.SCISSORS) && (moveTwo.equals(PlayMove.PAPER) || moveTwo.equals(PlayMove.LIZARD)))
        return true;

      if (moveOne.equals(PlayMove.PAPER) && moveTwo.equals(PlayMove.ROCK))
        return true;

      if (moveOne.equals(PlayMove.ROCK) && moveTwo.equals(PlayMove.LIZARD))
        return true;

      if (moveOne.equals(PlayMove.LIZARD) && (moveTwo.equals(PlayMove.SPOCK) || moveTwo.equals(PlayMove.PAPER)))
        return true;

      if (moveOne.equals(PlayMove.SPOCK) && moveTwo.equals(PlayMove.SCISSORS))
        return true;

      return false;

    }
    ```

Pues si, era lo que habíamos imaginado, vamos añadiendo reglas a los movimientos ya establecidos.