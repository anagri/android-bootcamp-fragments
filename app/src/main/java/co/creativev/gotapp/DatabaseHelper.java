package co.creativev.gotapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String GOT_DB = "got.db";
    public static final int VERSION = 1;
    public static final String SERVER_URL = "https://s3-ap-southeast-1.amazonaws.com/android-bootcamp-assets/";

    public static final GoTCharacter[] GOT_CHARACTERS =
            {
                    new GoTCharacter("Arya Stark", SERVER_URL + "arya.jpg", SERVER_URL + "arya_full.jpg", true, "Stark", R.drawable.stark, "Arya Stark is the third child and second daughter of Lord Eddard Stark and Lady Catelyn Tully"),
                    new GoTCharacter("Bran Stark", SERVER_URL + "bran.jpg", SERVER_URL + "bran_full.jpg", true, "Stark", R.drawable.stark, "Brandon Stark, typically called Bran, is the second son of Lord Eddard Stark and Lady Catelyn Tully."),
                    new GoTCharacter("Brienne Tarth", SERVER_URL + "brienne.jpg", SERVER_URL + "brienne_full.jpg", true, "Stark", R.drawable.stark, "Brienne is sometimes called the Maid of Tarth and mocked as Brienne the Beauty."),
                    new GoTCharacter("Catelyn Stark", SERVER_URL + "catelyn.jpg", SERVER_URL + "catelyn_full.jpg", false, "Stark", R.drawable.stark, "Lady Catelyn Stark, also called Catelyn Tully, is the wife of Lord Eddard Stark and Lady of Winterfell."),
                    new GoTCharacter("Cercei Lannister", SERVER_URL + "cercei.jpg", SERVER_URL + "cercei_full.jpg", true, "Lannister", R.drawable.lannister, "Cersei Lannister is the eldest child of Tywin and Joanna Lannister by mere moments, and the twin sister of Jaime Lannister."),
                    new GoTCharacter("Daenerys Targaryen", SERVER_URL + "daenerys.jpg", SERVER_URL + "daenerys_full.jpg", true, "Targaryen", R.drawable.targaryen, "Princess Daenerys Targaryen, known as Daenerys Stormborn and Dany, is one of the last confirmed members of House Targaryen"),
                    new GoTCharacter("Davos Seaworth", SERVER_URL + "davos.jpg", SERVER_URL + "davos_full.jpg", true, "Baratheon", R.drawable.baratheon, "Ser Davos Seaworth, commonly called the Onion Knight, is the head of House Seaworth. He was once a smuggler."),
                    new GoTCharacter("Eddard Stark", SERVER_URL + "eddard.jpg", SERVER_URL + "eddard_full.jpg", false, "Stark", R.drawable.stark, "Eddard Stark, also affectionately called 'Ned', is the head of House Stark, Lord of Winterfell, and Warden of the North."),
                    new GoTCharacter("Hodor", SERVER_URL + "hodor.jpg", SERVER_URL + "hodor_full.jpg", true, "Stark", R.drawable.stark, "Hodor, the giant, simple-minded stableboy of Winterfell."),
                    new GoTCharacter("Jaime Lannister", SERVER_URL + "jaime.jpg", SERVER_URL + "jaime_full.jpg", true, "Lannister", R.drawable.lannister, "Ser Jaime Lannister, known as the Kingslayer, is a knight from House Lannister. He is the twin brother of Queen Cersei Lannister."),
                    new GoTCharacter("Jaqen Hagar", SERVER_URL + "jaqen.jpg", SERVER_URL + "jaqen_full.jpg", true, "Faceless Men", R.drawable.faceless, "Jaqen Hagar is the name of a sly Lorathi criminal who meets Arya Stark on her way to the Wall."),
                    new GoTCharacter("Joffrey Baratheon", SERVER_URL + "joffrey.jpg", SERVER_URL + "joffrey_full.jpg", false, "Baratheon", R.drawable.baratheon, "Prince Joffrey Baratheon is known to the Seven Kingdoms as the eldest son and heir of King Robert I Baratheon and Queen Cersei Lannister."),
                    new GoTCharacter("Jon Snow", SERVER_URL + "jon.jpg", SERVER_URL + "jon_full.jpg", false, "Stark", R.drawable.stark, "Jon Snow doesn't know anything"),
                    new GoTCharacter("Khal Drogo", SERVER_URL + "khal.jpg", SERVER_URL + "khal_full.jpg", false, "Dothraki", R.drawable.dothraki, "Drogo is a powerful khal or warlord of the fearsome Dothraki nomads."),
                    new GoTCharacter("Melisandre", SERVER_URL + "melisandre.jpg", SERVER_URL + "melisandre_full.jpg", true, "Baratheon", R.drawable.baratheon, "Melisandre is a priestess of R'hllor and a shadowbinder, hailing from the eastern city of Asshai. She has joined the entourage of Stannis Baratheon."),
                    new GoTCharacter("Petyr Baelish", SERVER_URL + "petyr.jpg", SERVER_URL + "petyr_full.jpg", true, "Lannister", R.drawable.lannister, "Petyr Baelish, sometimes called Littlefinger, is the head of House Baelish of the Fingers. Petyr wears a mockingbird as his personal crest instead of House Baelish's sigil, a titan's head."),
                    new GoTCharacter("Podrick Payne", SERVER_URL + "podrick.jpg", SERVER_URL + "podrick_full.jpg", true, "Lannister", R.drawable.lannister, "Podrick Payne is the squire of Tyrion Lannister. He is from a cadet branch of House Payne."),
                    new GoTCharacter("Grand Maester Pycelle", SERVER_URL + "pycelle.jpg", SERVER_URL + "pycelle_full.jpg", true, "Lannister", R.drawable.lannister, "Pycelle is a Grand Maester of the Citadel who has served in King's Landing and on the small council for over forty years."),
                    new GoTCharacter("Ramsay Bolton", SERVER_URL + "ramsay.jpg", SERVER_URL + "ramsay_full.jpg", true, "Bolton", R.drawable.bolton, "Ramsay Bolton (formerly Ramsay Snow) is the legitimized bastard son of Lord Roose Bolton. He is known as the Bastard of Bolton and the Bastard of the Dreadfort."),
                    new GoTCharacter("Renly Baratheon", SERVER_URL + "renly.jpg", SERVER_URL + "renly_full.jpg", false, "Baratheon", R.drawable.baratheon, "Renly Baratheon is the Lord of Storm's End and Lord Paramount of the Stormlands. The younger brother of King Robert I and Lord Stannis."),
                    new GoTCharacter("Robb Stark", SERVER_URL + "robb.jpg", SERVER_URL + "robb_full.jpg", false, "Stark", R.drawable.stark, "Robb Stark is the eldest son of Eddard Stark and Catelyn Tully and is the heir of House Stark to Winterfell and the north."),
                    new GoTCharacter("Robert Baratheon", SERVER_URL + "robert.jpg", SERVER_URL + "robert_full.jpg", false, "Baratheon", R.drawable.baratheon, "King Robert I Baratheon is the Lord of the Seven Kingdoms of Westeros and the head of House Baratheon of King's Landing"),
                    new GoTCharacter("Roose Bolton", SERVER_URL + "roose.jpg", SERVER_URL + "roose_full.jpg", true, "Bolton", R.drawable.bolton, "Roose Bolton is the Lord of the Dreadfort and head of House Bolton."),
                    new GoTCharacter("Sansa Stark", SERVER_URL + "sansa.jpg", SERVER_URL + "sansa_full.jpg", true, "Stark", R.drawable.stark, "Arya Stark is the third child and second daughter of Lord Eddard Stark and Lady Catelyn Tully."),
                    new GoTCharacter("Stannis Baratheon", SERVER_URL + "stannis.jpg", SERVER_URL + "stannis_full.jpg", false, "Baratheon", R.drawable.baratheon, "Stannis Baratheon is the head of House Baratheon of Dragonstone and the Lord of Dragonstone."),
                    new GoTCharacter("Tyrion Lannister", SERVER_URL + "tyrion.jpg", SERVER_URL + "tyrion_full.jpg", true, "Lannister", R.drawable.lannister, "Tyrion is a dwarf, with stubby legs, a jutting forehead, mismatched eyes of green and black, and a mixture of pale blond and black hair."),
                    new GoTCharacter("Tywin Lannister", SERVER_URL + "tywin.jpg", SERVER_URL + "tywin_full.jpg", false, "Lannister", R.drawable.lannister, "Tywin Lannister is Lord of Casterly Rock, Shield of Lannisport and Warden of the West. The head of House Lannister, Tywin is one of the most powerful lords in Westeros."),
                    new GoTCharacter("Varys", SERVER_URL + "varys.jpg", SERVER_URL + "varys_full.jpg", true, "Lannister", R.drawable.lannister, "Varys, called the Spider, is an enigmatic member of the small council and the master of whisperers, or spymaster, for the Iron Throne of the Seven Kingdoms.")
            };
    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, GOT_DB, null, VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL("CREATE TABLE " + GoTCharacter.GOT_TABLE + "(" +
                GoTCharacter._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                GoTCharacter.NAME + " TEXT," +
                GoTCharacter.THUMB_URL + " TEXT," +
                GoTCharacter.FULL_URL + " TEXT," +
                GoTCharacter.HOUSE + " TEXT," +
                GoTCharacter.HOUSE_RES_ID + " INTEGER," +
                GoTCharacter.DESCRIPTION + " TEXT);");
        try {
            for (GoTCharacter gotCharacter : GOT_CHARACTERS) {
                insertCharacter(db, gotCharacter);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GoTCharacter.GOT_TABLE + ";");
        onCreate(db);
    }

    public int getCount() {
        return (int) DatabaseUtils.longForQuery(getReadableDatabase(), "SELECT COUNT(*) from " + GoTCharacter.GOT_TABLE, null);
    }

    public Cursor getCharacterCursor() {
        return getReadableDatabase().query(GoTCharacter.GOT_TABLE, GoTCharacter.ALL_COLS, null, null, null, null, null);
    }

    public long insert(GoTCharacter goTCharacter) {
        return insertCharacter(getWritableDatabase(), goTCharacter);
    }

    private long insertCharacter(SQLiteDatabase db, GoTCharacter gotCharacter) {
        ContentValues values = new ContentValues();
        values.put(GoTCharacter.NAME, gotCharacter.name);
        values.put(GoTCharacter.THUMB_URL, gotCharacter.thumbUrl);
        values.put(GoTCharacter.FULL_URL, gotCharacter.fullUrl);
        values.put(GoTCharacter.HOUSE, gotCharacter.house);
        values.put(GoTCharacter.HOUSE_RES_ID, gotCharacter.houseResId);
        values.put(GoTCharacter.DESCRIPTION, gotCharacter.description);
        return db.insert(GoTCharacter.GOT_TABLE, null, values);
    }

    public Cursor getAllRows() {
        return getReadableDatabase()
                .query(GoTCharacter.GOT_TABLE, GoTCharacter.ALL_COLS, null, null, null, null, null);
    }
}
