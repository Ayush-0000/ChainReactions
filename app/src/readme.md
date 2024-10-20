
# Quantum Squares

## Overview

**Quantum Squares** is a turn-based strategy game played on a 5x5 grid. Players take turns adding quantum particles to squares in an effort to gain control of the grid. The challenge lies in balancing particle accumulation with the risk of square collapses, which can trigger chain reactions and shift control.

The objective of the game is to be the first player to control a set number of squares by filling them to their particle limit before they collapse. A square collapses when it reaches 4 particles, redistributing the particles to adjacent squares.

The game ends when one player controls 10 squares, or when no more valid moves are available. The player with the most controlled squares at the end of the game wins.

## Rules

1. **Grid Setup:**
   - The game is played on a 5x5 grid. Each square can hold up to 4 quantum particles.
   - Initially, all squares are empty.

2. **Turn Structure:**
   - On each player's turn, they can place 1 quantum particle into any empty or partially filled square and own that square.
   - A player can place particles in neutral squares or squares they already own.
   - A square becomes "controlled" by a player when they add the 4th particle to that square (just before it collapses). This gives the player 1 point.

3. **Collapse Mechanism:**
   - When a square reaches 4 particles, it collapses. Upon collapsing, the square redistributes its particles to the adjacent squares (up, down, left, right).
   - After collapsing, the square returns to a neutral state, losing its control. However, the player retains the point for having controlled it momentarily.
   - Ownership Change: On collapsing, if the adjacent square is owned by another player, the particle that moves in from the collapsing square will not change the ownership of the square but will be colored according to the player whose particle already occupies that square. This creates a dynamic where players must strategize their moves carefully to avoid strengthening their opponent's control.

4. **Chain Reactions:**
   - If an adjacent square also reaches 4 particles due to particle redistribution, it will collapse as well, potentially causing chain reactions.

5. **Game End:**
   - The game ends when a player controls 10 squares (gaining 10 points), or when no more moves are possible (all squares are full).
   - The player with the most controlled squares (points) at the end wins.

6. **Objective:**
   - Players must strategize to control squares, balance particle distribution, and manage collapses to ensure they dominate the grid.

## Step-by-Step Demo

**Initial Setup:**
- The grid is a 5x5 array of empty squares. Each square can hold up to 4 particles.

### Example Walkthrough:

1. Player 1 (Red) adds 1 particle to (2,2)
   - (P1 = 0, P2 = 0)
2. Player 2 (Blue) adds 1 particle to (1,2)
   - (P1 = 0, P2 = 0)
3. Player 1 adds another particle to (2,2)
   - (P1 = 0, P2 = 0)
4. Player 2 adds another particle to (1,2)
   - (P1 = 0, P2 = 0)
5. Player 1 adds a 3rd particle to (2,2)
   - (P1 = 0, P2 = 0)
6. Player 2 adds a 3rd particle to (1,2)
   - (P1 = 0, P2 = 0)
7. Player 1 adds a 4th particle to (2,2) – Collapse! (Player 1 gains a point)
   - (P1 = 1, P2 = 0)
8. Chain Reaction: Square (1,2) collapses (Player 2 gains a point)
   - (P1 = 1, P2 = 1)

## Victory Conditions

- The first player to control 10 squares (gaining 10 points) wins the game.
- If no more moves are possible, the player with the most controlled squares at the end is the winner.

## Hackathon Requirements

- Develop a functional **Quantum Squares** game that adheres to the specified rules.
- The game can be developed as an Android/iOS app or a web application using any technology stack of your choice.
- Implement a user interface that allows players to interact with the game and visualize the gameplay.
- Ensure the game is playable and offers a smooth user experience.

## Bonus Points

Consider adding extra features or game modes to enhance the gameplay, such as:
- Different grid sizes
- Power-ups or special particles
- Multiplayer mode
- AI opponents

(these are just suggestions, you can add a creative feature of your own as well)

- Implement a visually appealing and engaging design for the game.
- Optimize the game for performance and responsiveness.

## Some UI Resources

[Link to UI Resources](https://drive.google.com/drive/folders/14Mezts7XzQTImYVxLxO0Ccp9991c8IJ8?usp=sharing)

Remember: This is the minimum requirement for the hackathon. Participants are encouraged to go beyond the basic requirements and add their own unique touches to the game to stand out.
