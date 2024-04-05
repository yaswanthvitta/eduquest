package com.example.courseendproject;


import android.content.Context;
import android.content.ContextWrapper;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Databasehelper extends SQLiteOpenHelper {
    public static final String TAG = Databasehelper.class.getSimpleName();
    public static int flag;
    // Exact Name of you db file that you put in assets folder with extension.
    static String DB_NAME = "elearning.db";
    private final Context myContext;
    String outFileName = "";
    private String DB_PATH;
    private SQLiteDatabase db;

    public Databasehelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        ContextWrapper cw = new ContextWrapper(context);
        DB_PATH = cw.getFilesDir().getAbsolutePath() + "/databases/";
        Log.e(TAG, "Databasehelper: DB_PATH " + DB_PATH);
        outFileName = DB_PATH + DB_NAME;
        File file = new File(DB_PATH);
        Log.e(TAG, "Databasehelper: " + file.exists());
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            //do nothing - database already exist
        } else {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(outFileName, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            try {
                copyDataBase();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */

    private void copyDataBase() throws IOException {

        Log.i("Database",
                "New database is being copied to device!");
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        // Open your local db as the input stream
        InputStream myInput = null;
        try {
            myInput = myContext.getAssets().open(DB_NAME);
            // transfer bytes from the inputfile to the
            // outputfile
            myOutput = new FileOutputStream(DB_PATH + DB_NAME);
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
            Log.i("Database",
                    "New database has been copied to device!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        Log.e(TAG, "openDataBase: Open " + db.isOpen());
        return db;

    }

    @Override
    public synchronized void close() {
        if (db != null)
            db.close();
        super.close();
    }

    public void onCreate(SQLiteDatabase arg0) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}

