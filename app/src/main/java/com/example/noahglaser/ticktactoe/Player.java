package com.example.noahglaser.ticktactoe;


import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

    /**
     * The image id the drawable folder
     */
    private int imageId;

    /**
     * Create a player with the parcel
     */
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    /**
     * Normal Constructor
     * @param imageId
     */
    public Player(int imageId) {
        this.imageId = imageId;
    }

    /**
     * Used for creating Player in the parcel stuff
     * @param in
     */
    private Player(Parcel in) {
        imageId = in.readInt();
    }

    /**
     * Gets the image id
     *
     * @return
     */
    public int getImageId() {
        return this.imageId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imageId);
    }
}
