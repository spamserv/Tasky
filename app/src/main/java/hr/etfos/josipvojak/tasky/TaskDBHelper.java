package hr.etfos.josipvojak.tasky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by jvojak on 12.4.2016..
 */
public class TaskDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "tasksInfo";
    static final String TABLE_TASKS = "tasks";

    static final String TABLE_TASKS_ID = "_id";
    static final String TABLE_TASKS_TITLE = "title";
    static final String TABLE_TASKS_DESCRIPTION = "description";
    static final String TABLE_TASKS_IMAGE = "image_uri";
    public TaskDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GAMES_TABLE = "CREATE TABLE " + TABLE_TASKS     + "(" + TABLE_TASKS_ID + " INTEGER PRIMARY KEY, "
                + TABLE_TASKS_TITLE + " TEXT," + TABLE_TASKS_DESCRIPTION +
                " TEXT,"     + TABLE_TASKS_IMAGE + " TEXT" + ")";   db.execSQL(CREATE_GAMES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_IF = "DROP TABLE IF EXISTS " + TABLE_TASKS;
        onCreate(db);
    }

    // Umetanje
    public void addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_TASKS_TITLE, task.getTitle());
        values.put(TABLE_TASKS_DESCRIPTION, task.getDescription());
        values.put(TABLE_TASKS_IMAGE, task.getPriorityPicture());
        db.insert(TABLE_TASKS, TABLE_TASKS_TITLE, values);
         // Bolji način od stalnog zatvarnja je koristiti singlton pattern
        db.close();  }  // Dohvaćanje liste svih igara


    public ArrayList<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList<Task>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.query(TABLE_TASKS, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                Task task = new Task(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                tasks.add(task);
            } while(cursor.moveToNext());
        }
        db.close();
        return tasks;
    }

    public void deleteGame(Task task){
        int id = task.getId();
        String[] arg = new String[]{ String.valueOf(id)};
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, TABLE_TASKS_ID + "=?", arg);
        db.close();
    }
}
