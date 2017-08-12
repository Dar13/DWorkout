package xyz.moor3.dworkout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nmoore on 8/11/17.
 */

public class DBManager extends SQLiteOpenHelper {
    private static DBManager mInstance = null;

    private static final String DATABASE_NAME = "DWorkout";
    private static final int DATABASE_VERSION = 1;

    private Context mCtx;

    public static DBManager getInstance(Context ctx)
    {
        if(mInstance == null)
        {
            mInstance = new DBManager(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS ExerciseInfo (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL, favorite BOOLEAN);");

        db.execSQL("CREATE TABLE IF NOT EXISTS SetInfo " +
                    "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "exercise_id INTEGER NOT NULL REFERENCES ExerciseInfo (id)," +
                    "time TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                    "weight INTEGER NOT NULL," +
                    "reps INTEGER NOT NULL);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO...
    }

    private DBManager(Context ctx)
    {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        mCtx = ctx;
    }
}
