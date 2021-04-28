# Desarrollo del requisito 2

## Requisito

!!! success "Las tijeras cortan el papel"

## Desarrollo

Se trata de un requisito simple, pero debemos tener en cuenta todas las casuísticas del test. Es decir debemos probar que las tijeras vencen al papel y que el papel es vencido por las tijeras, de esta forma tenemos todas las combinaciones.

=== "TestRuleEngine"
    ``` Java linenums="1"
    @Test
    public void scissors_cuts_paper() {

      RuleEngine ruleEngine = new RuleEngine();

      Assert.assertEquals(PlayResult.WIN_PLAYER_ONE, ruleEngine.play(PlayMove.SCISSORS, PlayMove.PAPER));
      Assert.assertEquals(PlayResult.WIN_PLAYER_TWO, ruleEngine.play(PlayMove.PAPER, PlayMove.SCISSORS));

    }
    ```
=== "RuleEngine"
    ``` Java linenums="1" 
    public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

      PlayResult result = null;

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.PAPER)) {
        result = PlayResult.WIN_PLAYER_ONE;
      } else if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.SCISSORS)) {
        result = PlayResult.WIN_PLAYER_TWO;
      } else {
        result = PlayResult.DRAW;
      }

      return result;

    }
    ```

Algo nos preocupa en este código, no lo vemos del todo claro y el cuerpo nos pide hacer un poco de `Refactor`.

=== "RuleEngine"
    ``` Java hl_lines="7 9" linenums="1"
    public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

      PlayResult result = null;

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.PAPER)) {
        result = PlayResult.WIN_PLAYER_ONE;
      } else if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.SCISSORS)) { /*(1)*/
        result = PlayResult.WIN_PLAYER_TWO;
      } else {                                                                              /*(1)*/
        result = PlayResult.DRAW;
      }

      return result;

    }
    ```
=== "RuleEngine-Ref1"
    ``` Java hl_lines="5 9 13" linenums="1"
    public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

      PlayResult result = null;

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.PAPER)) {      /*(2)*/
        result = PlayResult.WIN_PLAYER_ONE;
      } 
      
      else if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.SCISSORS)) { /*(2)*/
        result = PlayResult.WIN_PLAYER_TWO;
      } 
      
      else {                                                                              /*(2)*/
        result = PlayResult.DRAW;
      }

      return result;

    }
    ```
=== "RuleEngine-Ref2"
    ``` Java linenums="1"
    public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.PAPER))
        return PlayResult.WIN_PLAYER_ONE;

      if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.SCISSORS))
        return PlayResult.WIN_PLAYER_TWO;

      return PlayResult.DRAW;

    }
    ```

??? tip inline end "Refactoring"
    ***(1)*** A veces para aumentar la legibilidad del código nos basta con hacer algo tan tonto como `darle aire` al código, `dejarle que respire`. Unos cuantos saltos de línea y hacemos varios bloques más legibles y menos apelotonados. Al cerebro le gusta más leer varios párrafos cortos en vez de un único párrafo muy largo.

    ***(2)*** Algo nos dice que esas clausulas `if-else` no son muy correctas. Todas son excluyentes y en cada una se asigna un valor diferente que luego será devuelto. Es el típico caso de uso de una `Clausula de Guarda`. Podemos devolver con un return el resultado sin tener que llegar al final del método. Aumenta la legibilidad porque ayudamos al cerebro a no ir concatenando condiciones y negaciones. Ej:
    
    + Línea 5, si se cumple la condición, hago X
    + Línea 7, si no se cumple la condición 5 y se cumple la condición, hago Y
    + Línea 9, si no se cumple la condición 5 y tampoco se cumple la condición 7, hago Z

    Mucho mejor si metemos `clausulas de guarda` que vayan `liberando` al cerebro de la pesada carga de tener que recordar condiciones.


Algo nos dice que no va a ser fácil quitarnos esos `if` y que, además, van a ir apareciendo muchos más según avancemos en la implementación. De momento lo dejaremos pasar. 

Tampoco debemos olvidar que los Test son parte del código y se merecen tener un `Refactoring`, si nos fijamos en la clase de jUnit vemos que tenemos bastante código duplicado.


=== "TestRuleEngine"
    ``` Java hl_lines="4 17" linenums="1"
    @Test
    public void equals_move_result_draw() {

      RuleEngine ruleEngine = new RuleEngine();  /*(1)*/

      Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.ROCK, PlayMove.ROCK));
      Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.PAPER, PlayMove.PAPER));
      Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.SCISSORS, PlayMove.SCISSORS));
      Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.LIZARD, PlayMove.LIZARD));
      Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.SPOCK, PlayMove.SPOCK));

    }

    @Test
    public void scissors_cuts_paper() {

      RuleEngine ruleEngine = new RuleEngine();  /*(1)*/

      Assert.assertEquals(PlayResult.WIN_PLAYER_ONE, ruleEngine.play(PlayMove.SCISSORS, PlayMove.PAPER));
      Assert.assertEquals(PlayResult.WIN_PLAYER_TWO, ruleEngine.play(PlayMove.PAPER, PlayMove.SCISSORS));

    }
    ```
=== "TestRuleEngine-Ref1"
    ``` Java hl_lines="6-10 17-18" linenums="1"
    RuleEngine ruleEngine = new RuleEngine();

    @Test
    public void equals_move_result_draw() {

      Assert.assertEquals(PlayResult.DRAW, this.ruleEngine.play(PlayMove.ROCK, PlayMove.ROCK));                 /*(2)*/
      Assert.assertEquals(PlayResult.DRAW, this.ruleEngine.play(PlayMove.PAPER, PlayMove.PAPER));               /*(2)*/
      Assert.assertEquals(PlayResult.DRAW, this.ruleEngine.play(PlayMove.SCISSORS, PlayMove.SCISSORS));         /*(2)*/
      Assert.assertEquals(PlayResult.DRAW, this.ruleEngine.play(PlayMove.LIZARD, PlayMove.LIZARD));             /*(2)*/
      Assert.assertEquals(PlayResult.DRAW, this.ruleEngine.play(PlayMove.SPOCK, PlayMove.SPOCK));               /*(2)*/

    }

    @Test
    public void scissors_cuts_paper() {

      Assert.assertEquals(PlayResult.WIN_PLAYER_ONE, this.ruleEngine.play(PlayMove.SCISSORS, PlayMove.PAPER));  /*(2)*/
      Assert.assertEquals(PlayResult.WIN_PLAYER_TWO, this.ruleEngine.play(PlayMove.PAPER, PlayMove.SCISSORS));  /*(2)*/

    }
    ```
=== "TestRuleEngine-Ref2"
    ``` Java linenums="1"
    RuleEngine ruleEngine = new RuleEngine();

    @Test
    public void equals_move_result_draw() {

      invokeAndCheckResult(PlayMove.ROCK, PlayMove.ROCK, PlayResult.DRAW);
      invokeAndCheckResult(PlayMove.PAPER, PlayMove.PAPER, PlayResult.DRAW);
      invokeAndCheckResult(PlayMove.SCISSORS, PlayMove.SCISSORS, PlayResult.DRAW);
      invokeAndCheckResult(PlayMove.LIZARD, PlayMove.LIZARD, PlayResult.DRAW);
      invokeAndCheckResult(PlayMove.SPOCK, PlayMove.SPOCK, PlayResult.DRAW);

    }

    @Test
    public void scissors_cuts_paper() {

      invokeAndCheckResult(PlayMove.SCISSORS, PlayMove.PAPER, PlayResult.WIN_PLAYER_ONE);
      invokeAndCheckResult(PlayMove.PAPER, PlayMove.SCISSORS, PlayResult.WIN_PLAYER_TWO);

    }

    private void invokeAndCheckResult(PlayMove playerOne, PlayMove playerTwo, PlayResult expectedResult) {

      Assert.assertEquals(expectedResult, this.ruleEngine.play(playerOne, playerTwo));
    }
    ```

??? tip inline end "Refactoring"
    ***(1)*** Ambas líneas son exactamente iguales, podemos extraer la variable a una global para toda la clase o utilizar algún tipo de patrón `Builder`.

    ***(2)*** Prácticamente se hace lo mismo en todo el bloque de líneas, salvo que se le pasan dos entradas diferentes y se obtiene una salida diferente. Quizá podríamos encapsularlo todo eso en un método que fuera más legible.



## Resultado final

Al finalizar obtenemos el siguiente código:


=== "TestRuleEngine"
    ``` Java linenums="1" 
    public class TestRuleEngine {

      RuleEngine ruleEngine = new RuleEngine();

      @Test
      public void equals_move_result_draw() {

        invokeAndCheckResult(PlayMove.ROCK, PlayMove.ROCK, PlayResult.DRAW);
        invokeAndCheckResult(PlayMove.PAPER, PlayMove.PAPER, PlayResult.DRAW);
        invokeAndCheckResult(PlayMove.SCISSORS, PlayMove.SCISSORS, PlayResult.DRAW);
        invokeAndCheckResult(PlayMove.LIZARD, PlayMove.LIZARD, PlayResult.DRAW);
        invokeAndCheckResult(PlayMove.SPOCK, PlayMove.SPOCK, PlayResult.DRAW);

      }

      @Test
      public void scissors_cuts_paper() {

        invokeAndCheckResult(PlayMove.SCISSORS, PlayMove.PAPER, PlayResult.WIN_PLAYER_ONE);
        invokeAndCheckResult(PlayMove.PAPER, PlayMove.SCISSORS, PlayResult.WIN_PLAYER_TWO);

      }

      private void invokeAndCheckResult(PlayMove playerOne, PlayMove playerTwo, PlayResult expectedResult) {

        Assert.assertEquals(expectedResult, this.ruleEngine.play(playerOne, playerTwo));
      }
    }
    ```
=== "RuleEngine"
    ``` Java linenums="1" 
    public class RuleEngine {

      public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

        if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.PAPER))
          return PlayResult.WIN_PLAYER_ONE;

        if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.SCISSORS))
          return PlayResult.WIN_PLAYER_TWO;

        return PlayResult.DRAW;

      }

    }
    ```

