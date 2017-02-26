package com.example.noahglaser.ticktactoe;


public class Player {

    private int imageId;


    public Player(int imageId) {
        this.imageId = imageId;
    }

    /**
     * Gets the image id
     * @return
     */
    public int getImageId()
    {
        return this.imageId;
    }

}
