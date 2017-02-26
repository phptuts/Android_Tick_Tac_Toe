package com.example.noahglaser.ticktactoe;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Player player1;

    private Player player2;

    private Board board;

    private ImageView boardImage;

    private ImageView[][] pieces = new ImageView[3][3];

    private int turns = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.player1 = new Player(R.drawable.red);
        this.player2 = new Player(R.drawable.yellow);
        this.board = new Board();

        this.boardImage = (ImageView) findViewById(R.id.board);

        this.boardImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int x = getXPosition(event.getX());
                int y = getYPosition(event.getY());
                move(x, y);
                return false;
            }
        });

        this.pieces[0][0] = (ImageView) findViewById(R.id.connect1);
        this.pieces[1][0] = (ImageView) findViewById(R.id.connect2);
        this.pieces[2][0] = (ImageView) findViewById(R.id.connect3);
        this.pieces[0][1] = (ImageView) findViewById(R.id.connect4);
        this.pieces[1][1] = (ImageView) findViewById(R.id.connect5);
        this.pieces[2][1] = (ImageView) findViewById(R.id.connect6);
        this.pieces[0][2] = (ImageView) findViewById(R.id.connect7);
        this.pieces[1][2] = (ImageView) findViewById(R.id.connect8);
        this.pieces[2][2] = (ImageView) findViewById(R.id.connect9);
    }

    /**
     * Determines the x position of the square the user clicked on
     * @param x
     * @return
     */
    public int getXPosition(double x) {
        double thirdWidth = this.boardImage.getWidth() / 3;
        if (x < thirdWidth) {
            return 0;
        } else if (x < (thirdWidth * 2)) {
            return 1;
        }

        return 2;
    }

    /**
     * Determines the y position of the square the user clicked on
     * @param y
     * @return
     */
    public int getYPosition(double y) {
        double thirdHeight = this.boardImage.getHeight() / 3;
        if (y < thirdHeight) {
            return 0;
        } else if (y < (thirdHeight * 2)) {
            return 1;
        }

        return 2;
    }

    /**
     * Puts a tick tac toe piece on the board
     * Checks if someone won or tied
     *
     * @param x
     * @param y
     */
    public void move(int x, int y) {

        if (!this.board.canMove(x, y)) {
            Toast.makeText(getApplicationContext(), "Space Taken", Toast.LENGTH_SHORT).show();
            return;
        }

        if (turns == 0 || turns % 2 == 0) {
            this.board.move(this.player1, x, y);
            this.putPieceOnBoard(this.player1.getImageId(), x, y);
        } else {
            this.board.move(this.player2, x, y);
            this.putPieceOnBoard(this.player2.getImageId(), x, y);
        }

        this.gameOver();
    }

    /**
     * Puts the piece on the board
     * @param imageId
     * @param x
     * @param y
     */
    private void putPieceOnBoard(int imageId, final int x, final int y) {
        this.boardImage.setClickable(false);
        this.pieces[x][y].setImageResource(imageId);
        this.pieces[x][y].setY(this.pieces[x][y].getY() - 100);
        this.pieces[x][y].setAlpha(1f);
        this.pieces[x][y].animate().translationYBy(100f).rotation(180).setDuration(1500).withEndAction(new Runnable() {
            @Override
            public void run() {
                pieces[x][y].setRotation(-180f);
                boardImage.setClickable(true);
            }
        });

    }


    /**
     * If the game is over resets the game
     */
    private void gameOver() {

        String playerName = turns == 0 || turns % 2 == 0 ? "PLAYER 1" : "PLAYER 2";
        boolean playerWon;
        if (turns == 0 || turns % 2 == 0) {
            playerWon = this.board.hasPlayerWon(this.player1);
        } else {
            playerWon = this.board.hasPlayerWon(this.player2);
        }

        if (playerWon) {
            Toast.makeText(getApplicationContext(), playerName + " WINS", Toast.LENGTH_SHORT).show();
            this.resetGame();
        } else if (board.noWinner()) {
            Toast.makeText(getApplicationContext(), "TIE GAME", Toast.LENGTH_SHORT).show();
            this.resetGame();
        } else {
            turns += 1;
        }

    }

    /**
     * Resets the game
     */
    private void resetGame() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int y = 0; y < 3; y += 1) {
                    for (int x = 0; x < 3; x += 1) {
                        pieces[x][y].setAlpha(0f);
                    }
                }
                board.reset();
                Toast.makeText(getApplicationContext(), "NEW GAME STARTED", Toast.LENGTH_SHORT).show();
                turns = 0;
            }
        }, 2000);
    }
}
