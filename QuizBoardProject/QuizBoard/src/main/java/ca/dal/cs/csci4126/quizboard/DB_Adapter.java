package ca.dal.cs.csci4126.quizboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by James on 7/21/13.
 */
public class DB_Adapter {

    private static final String DATABASE_NAME = "quiz_board";

    private static final String DATABASE_QUESTION_TABLE = "Question";

    public static final String KEY_quesId = "que_id";
    public static final String KEY_departmentId = "department_ID";
    public static final String KEY_question = "question";
    public static final String KEY_choice1 = "choice_1";
    public static final String KEY_choice2 = "choice_2";
    public static final String KEY_choice3 = "choice_3";
    public static final String KEY_choice4 = "choice_4";
    public static final String KEY_answer = "answer";

    private static final String TAG = "DBAdapter";


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_questions =
            "create table Question (que_id integer primary key autoincrement, "
                    + "department_ID integer primary key, "
                    + "question text not null, choice_1 text not null, "
                    + "choice_2 text not null, choice_3 text not null,"
                    + "choice_4 text not null, answer text not null, " +
                    "FOREIGN KEY (department_ID) REFERENCES Departments (department_ID));";

    private static final String DATABASE_CREATE_department =
            "create table Departments (department_ID integer primary key autoincrement, "
                    + "department_name text not null);";

    private static final String DATABASE_CREATE_FACULTY =
            "create table Departments (banner_Number text primary key UNIQUE, "
                    + "faculty_member_name text not null);";

    private static final String DATABASE_CREATE_STUDENT =
            "create table Student (banner_Number text primary key UNIQUE, "
                    + "student_name text not null, student_points integer not null DEFAULT '0');";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DB_Adapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DATABASE_CREATE_department);
            db.execSQL(DATABASE_CREATE_FACULTY);
            db.execSQL(DATABASE_CREATE_STUDENT);
            db.execSQL(DATABASE_CREATE_questions);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion
                    + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }

    //---opens the database---
    public DB_Adapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    public long insertQuestion(String question, String solution1, String solution2, String solution3, String solution4, String answer)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_question, question);
        initialValues.put(KEY_choice1, solution1);
        initialValues.put(KEY_choice2, solution2);
        initialValues.put(KEY_choice3, solution3);
        initialValues.put(KEY_choice4, solution4);
        initialValues.put(KEY_answer, answer);
        return db.insert(DATABASE_QUESTION_TABLE, null, initialValues);
    }
}
