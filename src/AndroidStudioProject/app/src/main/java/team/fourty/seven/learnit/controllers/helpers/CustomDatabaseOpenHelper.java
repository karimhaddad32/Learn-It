package team.fourty.seven.learnit.controllers.helpers;

import android.content.Context;

/**
 * A helper class for accessing an existing SQLite database.
 *
 * Created by Sleiman on 3/16/2019
 */
public class CustomDatabaseOpenHelper extends SQLiteAssetHelper {

    // You should change put your database name instead.
    private static final String DATABASE_NAME = "database.db";

    // Keep the version number as is for now.
    private static final int DATABASE_VERSION = 1;

    public CustomDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
