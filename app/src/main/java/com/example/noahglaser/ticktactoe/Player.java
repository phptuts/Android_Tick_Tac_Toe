package com.example.noahglaser.ticktactoe;


import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

    private int imageId;

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public Player(int imageId) {
        this.imageId = imageId;
    }

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
