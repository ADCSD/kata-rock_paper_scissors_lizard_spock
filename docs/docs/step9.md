# Desarrollo del requisito 9

## Requisito

!!! success "El papel desaprueba a Spock"

## Desarrollo

Nuevo requisito que podemos agrupar.

=== "TestRuleEngine"
    ``` Java linenums="1"
    @Test
    public void paper_disproves_spock() {

      invokeAndCheckResult(PlayMove.PAPER, PlayMove.SPOCK, PlayResult.WIN_PLAYER_ONE);
      invokeAndCheckResult(PlayMove.SPOCK, PlayMove.PAPER, PlayResult.WIN_PLAYER_TWO);

    }
    ```
=== "RuleEngine"
    ``` Java linenums="1"
    private boolean isPlayWinner(PlayMove moveOne, PlayMove moveTwo) {

      if (moveOne.equals(PlayMove.SCISSORS) && (moveTwo.equals(PlayMove.PAPER) || moveTwo.equals(PlayMove.LIZARD)))
        return true;

      if (moveOne.equals(PlayMove.PAPER) && (moveTwo.equals(PlayMove.ROCK) || moveTwo.equals(PlayMove.SPOCK)))
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

Otro requisito más, ya faltan menos.