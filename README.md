Solution to a "Jindosh riddle" from the game Dishonored 2, using Prolog and Clojure (core.logic).

The riddle is just a variation of [Zebra puzzle](https://en.wikipedia.org/wiki/Zebra_Puzzle).

The game does a nice job of randomizing the puzzle for every player (so it is "walkthrough proof"), so this is just one possible instance of it.

![](img/riddle.jpg)

### Running Clojure version

```bash
    $ cd clojure
    $ lein run
```

### Running Prolog version

  Used SWI-Prolog 7.2.3:

```bash
    $ cd prolog
    $ prolog -c solution.pro
```
