package team.fourty.seven.learnit.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import team.fourty.seven.learnit.controllers.helpers.CustomDatabaseOpenHelper;
import team.fourty.seven.learnit.controllers.helpers.DBAccessController;
import team.fourty.seven.learnit.models.JournalEntry;

public class JournalEntryController
{

    private DBAccessController dbAccessController;
    private SQLiteDatabase sqLiteDatabase;
    private final String DB_TABLE_NAME = "JOURNAL_ENTRIES";
    private final String JOURNAL_ENTRY_TITLE = "title";
    private final String JOURNAL_ENTRY_DESCRIPTION = "description";
    private final String JOURNAL_ENTRY_CATEGORY = "category";



    public JournalEntryController(Context context)
    {
        this.dbAccessController = DBAccessController.getInstance(context);
    }



    public List<JournalEntry> getEntries(String categoryName)
    {
        sqLiteDatabase = this.dbAccessController.openDatabase();
        List<JournalEntry> journalEntries = new ArrayList<>();

        Cursor cursor = null;

        try
        {
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE " + JOURNAL_ENTRY_CATEGORY + " = '" + categoryName + "'",null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                JournalEntry entry = new JournalEntry();
                String title = cursor.getString(cursor.getColumnIndex(JOURNAL_ENTRY_TITLE));
                String description = cursor.getString(cursor.getColumnIndex(JOURNAL_ENTRY_DESCRIPTION));
                String category = cursor.getString(cursor.getColumnIndex(JOURNAL_ENTRY_CATEGORY));
                entry.setTitle(title);
                entry.setDescription(description);
                entry.setCategory(category);
                journalEntries.add(entry);
                cursor.moveToNext();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            this.dbAccessController.closeDatabase();
            cursor.close();
        }

        return journalEntries;
    }


    public void createEntry(JournalEntry newEntry)
    {
        sqLiteDatabase = this.dbAccessController.openDatabase();
        sqLiteDatabase.beginTransaction();

        try
        {
            String title = newEntry.getTitle();
            String description = newEntry.getDescription();
            String category = newEntry.getCategory();

            ContentValues values = new ContentValues();
            values.put(JOURNAL_ENTRY_TITLE, title);
            values.put(JOURNAL_ENTRY_DESCRIPTION, description);
            values.put(JOURNAL_ENTRY_CATEGORY, category);
            sqLiteDatabase.insert(DB_TABLE_NAME, null, values);
//            sqLiteDatabase.execSQL("INSERT INTO " + DB_TABLE_NAME + "(title, description, category ) VALUES (" + title + ", " + description + ", " + category + ")");

            sqLiteDatabase.setTransactionSuccessful();

            Log.i("Create", "created");
        }
        catch (Exception e)
        {
            Log.i("Create", "not created");
        }
        finally
        {
            sqLiteDatabase.endTransaction();
            this.dbAccessController.closeDatabase();
        }
    }


    public void editEntry(JournalEntry newEntry)
    {
        sqLiteDatabase = this.dbAccessController.openDatabase();
        sqLiteDatabase.beginTransaction();

        try
        {
            String title = newEntry.getTitle();
            String description = newEntry.getDescription();
            String category = newEntry.getCategory();

            ContentValues values = new ContentValues();
            values.put(JOURNAL_ENTRY_TITLE, title);
            values.put(JOURNAL_ENTRY_DESCRIPTION, description);
            values.put(JOURNAL_ENTRY_CATEGORY, category);
            sqLiteDatabase.update(DB_TABLE_NAME, values, JOURNAL_ENTRY_TITLE + " = '" + newEntry.getTitle() + "'", null);

            sqLiteDatabase.setTransactionSuccessful();

            Log.i("Updated", "updated");
        }
        catch (Exception e)
        {
            Log.i("Updated", "not updated");
        }
        finally
        {
            sqLiteDatabase.endTransaction();
            this.dbAccessController.closeDatabase();
        }
    }


    public void removeEntry(JournalEntry entry)
    {
        sqLiteDatabase = this.dbAccessController.openDatabase();
        sqLiteDatabase.beginTransaction();

        try
        {
            String title = entry.getTitle();
            //sqLiteDatabase.delete(DB_TABLE_NAME,JOURNAL_ENTRY_TITLE + " = " + title, null);
            sqLiteDatabase.execSQL("DELETE FROM " + DB_TABLE_NAME + " WHERE " + JOURNAL_ENTRY_TITLE + "='" + title + "'");

            sqLiteDatabase.setTransactionSuccessful();

            Log.i("Delete", "deleted");
        }
        catch (Exception e)
        {
            Log.i("Delete", "not deleted");
        }
        finally
        {
            sqLiteDatabase.endTransaction();
            this.dbAccessController.closeDatabase();
        }
    }

}
