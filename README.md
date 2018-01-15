# Mastermind
A mobile game inspired by a classic board game where the player needs to figure out a sequence of colours in a limited amount of tries.

## Features

- The game saves the name of the top 5 players. This information is saved using Android Shared Preferences.

- The score is based on the time that the player spent to figure out the sequence.

- After each "try" the game gives the player a feedback about his current sequence. The feedback is made in a form of black or white pieces.
  - The black piece means that one colour is in the right position
  - The white piece means that one colour is correct, but it is in the wrong position
  - The absence of pieces means that one colour is wrong
  
- The feedback pieces are shuffled, in other words, a black piece in the first position doesn't mean that your FIRST colour is the correct one.
