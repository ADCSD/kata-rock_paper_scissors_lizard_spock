# Desarrollo del requisito 5

## Requisito

!!! success "El lagarto envenena a Spock"

## Desarrollo

De nuevo, un requisito muy sencillo.

=== "TestRuleEngine"
    ``` Java linenums="1"
    @Test
    public void lizard_poisons_spock() {

      invokeAndCheckResult(PlayMove.LIZARD, PlayMove.SPOCK, PlayResult.WIN_PLAYER_ONE);
      invokeAndCheckResult(PlayMove.SPOCK, PlayMove.LIZARD, PlayResult.WIN_PLAYER_TWO);

    }
    ```
=== "RuleEngine"
    ``` Java linenums="1"
    public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.PAPER))
        return PlayResult.WIN_PLAYER_ONE;

      if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.SCISSORS))
        return PlayResult.WIN_PLAYER_TWO;

      if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.ROCK))
        return PlayResult.WIN_PLAYER_ONE;

      if (playerOne.equals(PlayMove.ROCK) && playerTwo.equals(PlayMove.PAPER))
        return PlayResult.WIN_PLAYER_TWO;

      if (playerOne.equals(PlayMove.ROCK) && playerTwo.equals(PlayMove.LIZARD))
        return PlayResult.WIN_PLAYER_ONE;

      if (playerOne.equals(PlayMove.LIZARD) && playerTwo.equals(PlayMove.ROCK))
        return PlayResult.WIN_PLAYER_TWO;

      if (playerOne.equals(PlayMove.LIZARD) && playerTwo.equals(PlayMove.SPOCK))
        return PlayResult.WIN_PLAYER_ONE;

      if (playerOne.equals(PlayMove.SPOCK) && playerTwo.equals(PlayMove.LIZARD))
        return PlayResult.WIN_PLAYER_TWO;

      return PlayResult.DRAW;

    }
    ```

El método no parece tener fin, podemos empezar a llorar no sabemos como parar esto.

