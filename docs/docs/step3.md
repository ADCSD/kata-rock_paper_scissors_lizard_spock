# Desarrollo del requisito 3

## Requisito

!!! success "El papel recubre la piedra"

## Desarrollo

Este requisito, igual que el anterior, es bastante sencillo.

=== "TestRuleEngine"
    ``` Java linenums="1"
    @Test
    public void paper_cover_rock() {

      invokeAndCheckResult(PlayMove.PAPER, PlayMove.ROCK, PlayResult.WIN_PLAYER_ONE);
      invokeAndCheckResult(PlayMove.ROCK, PlayMove.PAPER, PlayResult.WIN_PLAYER_TWO);

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

      return PlayResult.DRAW;

    }
    ```

Vemos con impotencia como van creciendo los `if` y no *sabemos* como resolverlos. A simple vista no podemos `Refactorizar` mucho más. De momento lo dejamos pasar y al final veremos como podemos solucionarlo.

