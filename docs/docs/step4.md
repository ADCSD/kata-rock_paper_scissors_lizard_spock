# Desarrollo del requisito 4

## Requisito

!!! success "La piedra aplasta al lagargo"

## Desarrollo

Este requisito, igual que el anterior, es bastante sencillo.

=== "TestRuleEngine"
    ``` Java linenums="1"
    @Test
    public void rock_crushes_lizard() {

      invokeAndCheckResult(PlayMove.ROCK, PlayMove.LIZARD, PlayResult.WIN_PLAYER_ONE);
      invokeAndCheckResult(PlayMove.LIZARD, PlayMove.ROCK, PlayResult.WIN_PLAYER_TWO);

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

      return PlayResult.DRAW;

    }
    ```

Y sigue creciendo el método, y nos vamos incomodando cada vez más.

