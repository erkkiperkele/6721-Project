# 6721-Project: Polarity game
=============================

Possible states board weight
----------------------------

                            2
                        3   3   3
                    4   6   6   6   4
                4   7   8   8   8   7   4
            4   7   8   9   X   9   8   7   4
        2   5   6   7   8   8   8   7   6   5   2
    1   2   2   3   4   4   4   4   4   3   2   2   1


Possible blocking states board weight
-------------------------------------

                            0
                        1   0   1
                    1   1   4   1   1
                1   1   4   4   4   1   1
            1   1   4   4   4   4   4   1   1
        0   0   2   2   2   2   2   2   2   0   0
    0   0   2   2   2   2   2   2   2   2   2   0   0



States
------

            .                    .
        .   .           OR       .   .
    .   .   .                    .   .   .

Note: Only 1 possibility to win on each state.


Blocking state
--------------

    .       o                   o       .
    .   .               OR          .   .
    o   .   .                   .   .   o
    
Winning
-------

Need at least 2 adjacent positions 
(one in Horizontal/Vertical axis and one in diagonal right or left axis)

    x   x   x
    x   X   x
    x   x   x

Need at least 5 of those 20 positions to win.    

    x   x   .   x   x
    x   x   x   x   x
    .   x   X   x   .
    x   x   x   x   x
    x   x   .   x   x 
    
    1   2   .   x   x
    x   3   4   x   x
    .   x   5   x   .
    x   x   x   x   x
    x   x   .   x   x 


Some properties
--------------------------------

* Positions: h^2                          -> 49 positions
* Number of states: 2(h-2)^2              -> 50 states
* Width: h x 2 -1

