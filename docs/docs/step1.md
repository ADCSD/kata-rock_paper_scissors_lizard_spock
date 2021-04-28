# Desarrollo del requisito 1

## Requisito

!!! success "Dos jugadas iguales deben devolver un empate"

## Desarrollo

### Empezamos la codificación

Como siempre, en TDD, empezamos creando el test unitario que cubra el primer requisito. Vamos a crear un test que cubra todos los posibles empates del juego. Vamos a utilizar los `enum` que tenemos ya creados para mejorar la legibilidad. Para ello añadimos un test:

=== "TestRuleEngine"
``` Java linenums="1"
  @Test
  public void equals_move_result_draw() {

    RuleEngine ruleEngine = new RuleEngine();

    Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.ROCK, PlayMove.ROCK));
    Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.PAPER, PlayMove.PAPER));
    Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.SCISSORS, PlayMove.SCISSORS));
    Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.LIZARD, PlayMove.LIZARD));
    Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.SPOCK, PlayMove.SPOCK));

  }
```

!!! todo "Recuerda"
    Recuerda que los nombres de los tests deben ser descriptivos, para que no sea necesario leer el código del test para saber lo que realiza. Con leer el nombre del método debería ser suficiente para saber lo que hace.

Obviamente si ejecutamos el test, obtendremos un test fail en rojo ya que nuestro código no cumple con lo esperado por el test. El siguiente paso en TDD es realizar la implementación **mínima** que haga funcionar los tests.

=== "RuleEngine"
``` Java linenums="1"

  public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

    return PlayResult.DRAW;

  }

```

La implementación mínima que se me ocurre ahora mismo y que pasaría todos los tests actuales es esta. Ahora si ejecutamos, obtenemos una ejecución correcta en verde. El siguiente paso paso en TDD sería realizar el `Refactoring`. Aunque se podría aplicar alguna acción, vamos a dejarlo pasar hasta tener algo más de código, donde ya empecemos a detectar ciertos `smells`.

!!! tip "Cuidado - `Speculative Generality`"
    Uno de los `smells` más típicos que solemos cometer todos es el de `Speculative Generality`. Este `smell` nos indica que estamos construyendo más código fuente del estrictamente necesario. Esto nos pasa cuando nos estamos adelantando al futuro y estamos preparando el código para *"por si acaso"* nos hace falta luego. Es un esfuerzo que muy a menudo podemos ahorrarnos ya que ese *"por si acaso"* a menudo nunca llega o cambia de *aspecto*.

## Resultado final

Como de momento no vamos a hacer `Refactor`, el código que tenemos debería ser este:

=== "TestRuleEngine"
    ``` Java linenums="1" 
    public class TestRuleEngine {

      @Test
      public void equals_move_result_draw() {

        RuleEngine ruleEngine = new RuleEngine();

        Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.ROCK, PlayMove.ROCK));
        Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.PAPER, PlayMove.PAPER));
        Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.SCISSORS, PlayMove.SCISSORS));
        Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.LIZARD, PlayMove.LIZARD));
        Assert.assertEquals(PlayResult.DRAW, ruleEngine.play(PlayMove.SPOCK, PlayMove.SPOCK));

      }

    }
    ```
=== "RuleEngine"
    ``` Java linenums="1" 
    public class RuleEngine {

      public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

        return PlayResult.DRAW;

      }

    }
    ```

