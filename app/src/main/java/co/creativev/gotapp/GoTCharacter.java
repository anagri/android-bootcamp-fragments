package co.creativev.gotapp;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

public class GoTCharacter implements Parcelable, BaseColumns {
    public static final int STARK = 1;
    public static final int LANNISTER = 2;
    public static final int BARATHEON = 3;
    public static final int TARGARYEN = 4;
    public static final int BOLTON = 5;
    public static final int DOTHRAKI = 6;
    public static final int FACELESS_MEN = 7;

    public static final String NAME = "name";
    public static final String THUMB_URL = "thumb_url";
    public static final String FULL_URL = "full_url";
    public static final String HOUSE = "house";
    public static final String HOUSE_ID = "house_res_id";
    public static final String DESCRIPTION = "description";
    public static final String[] ALL_COLS = {_ID, NAME, THUMB_URL, FULL_URL, HOUSE, HOUSE_ID, DESCRIPTION};
    public static final String GOT_TABLE = "got_characters";

    public final int id;
    public final String name;
    public final String thumbUrl;
    public final boolean alive;
    public final String fullUrl;
    public final int houseId;
    public final String description;

    public GoTCharacter(String name, String thumbUrl, String fullUrl, boolean alive, int houseId, String description) {
        this(0, name, thumbUrl, fullUrl, alive, houseId, description);
    }

    public GoTCharacter(int id, String name, String thumbUrl, String fullUrl, boolean alive, int houseId, String description) {
        this.id = id;
        this.name = name;
        this.thumbUrl = thumbUrl;
        this.alive = alive;
        this.fullUrl = fullUrl;
        this.houseId = houseId;
        this.description = description;
    }

    public String lifeStatus() {
        return alive ? "Will Die" : "Already Dead";
    }

    public String getHouse(Context context) {
        switch (houseId) {
            case STARK:
                return context.getString(R.string.stark);
            case LANNISTER:
                return context.getString(R.string.lannister);
            case BARATHEON:
                return context.getString(R.string.baratheon);
            case TARGARYEN:
                return context.getString(R.string.targaryen);
            case BOLTON:
                return context.getString(R.string.bolton);
            case DOTHRAKI:
                return context.getString(R.string.dothraki);
            case FACELESS_MEN:
                return context.getString(R.string.faceless_men);
            default:
                return context.getString(R.string.unknown_house);
        }
    }

    public int getHouseImageRes() {
        switch (houseId) {
            case STARK:
                return R.drawable.stark;
            case LANNISTER:
                return R.drawable.lannister;
            case BARATHEON:
                return R.drawable.baratheon;
            case TARGARYEN:
                return R.drawable.targaryen;
            case BOLTON:
                return R.drawable.bolton;
            case DOTHRAKI:
                return R.drawable.dothraki;
            case FACELESS_MEN:
                return R.drawable.faceless;
            default:
                return R.drawable.house_placeholder;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.thumbUrl);
        dest.writeByte(alive ? (byte) 1 : (byte) 0);
        dest.writeString(this.fullUrl);
        dest.writeInt(this.houseId);
        dest.writeString(this.description);
    }

    protected GoTCharacter(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.thumbUrl = in.readString();
        this.alive = in.readByte() != 0;
        this.fullUrl = in.readString();
        this.houseId = in.readInt();
        this.description = in.readString();
    }

    public static final Creator<GoTCharacter> CREATOR = new Creator<GoTCharacter>() {
        public GoTCharacter createFromParcel(Parcel source) {
            return new GoTCharacter(source);
        }

        public GoTCharacter[] newArray(int size) {
            return new GoTCharacter[size];
        }
    };
}
