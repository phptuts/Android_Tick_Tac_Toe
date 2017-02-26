package com.example.noahglaser.ticktactoe;


public class Board {

    private Player[][] spaces = new Player[3][3];

    /**
     * Determines if the space is taken
     * @param x
     * @param y
     * @return
     */
    public boolean canMove(int x, int y) {
        return null == this.spaces[x][y];
    }

    /**
     * Returns whether the game is tied
     * @return
     */
    public boolean noWinner() {
        for (int y = 0; y < 3; y += 1) {
            for (int x = 0; x < 3; x += 1) {
                if (this.spaces[x][y] == null) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Takes a up a space
     * @param player
     * @param x
     * @param y
     */
    public void move(Player player, int x, int y) {
        this.spaces[x][y] = player;
    }

    /**
     * Resets the game
     */
    public void reset() {
        this.spaces = new Player[3][3];
    }

    /**
     * Determines if that player has won
     * @param player
     * @return
     */
    public boolean hasPlayerWon(Player player) {
        return this.playerHasDiagonalRow(player) || this.playerHasVerticalRow(player) || this.playerHasHorizontalRow(player);
    }

    /**
     * Determines if the player has a 3 in a row vertically
     * @param player
     * @return
     */
    private boolean playerHasVerticalRow(Player player) {

        for (int i = 0; i < 3; i += 1) {
            if (this.spaces[i][0] == player && this.spaces[i][1] == player && this.spaces[i][2] == player) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines if the player has 3 in a row horizontally
     * @param player
     * @return
     */
    private boolean playerHasHorizontalRow(Player player) {
        for (int i = 0; i < 3; i += 1) {
            if (this.spaces[0][i] == player && this.spaces[1][i] == player && this.spaces[2][i] == player) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines if the player has diagonal row
     * @param player
     * @return
     */
    private boolean playerHasDiagonalRow(Player player) {
        return (this.spaces[0][0] == player && this.spaces[1][1] == player && this.spaces[2][2] == player) ||
                (this.spaces[2][0] == player && this.spaces[1][1] == player && this.spaces[0][2] == player);
    }
}
