# Desarrollo del requisito 10

## Requisito

!!! success "Spock vaporiza la piedra"

## Desarrollo

Nuevo requisito que podemos agrupar.

=== "TestRuleEngine"
    ``` Java linenums="1"
    @Test
    public void spock_vaporizes_rock() {

      invokeAndCheckResult(PlayMove.SPOCK, PlayMove.ROCK, PlayResult.WIN_PLAYER_ONE);
      invokeAndCheckResult(PlayMove.ROCK, PlayMove.SPOCK, PlayResult.WIN_PLAYER_TWO);

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

      if (moveOne.equals(PlayMove.SPOCK) && (moveTwo.equals(PlayMove.SCISSORS) || moveTwo.equals(PlayMove.ROCK)))
        return true;

      return false;

    }
    ```

Vamos a por el último y clásico requisito.