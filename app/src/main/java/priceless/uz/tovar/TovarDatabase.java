package priceless.uz.tovar;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tovar.class}, version = 1, exportSchema = false)
public abstract  class TovarDatabase extends RoomDatabase {

    private static TovarDatabase tovarDatabase;

    private static String DATABASE_NAME = "database";

    public synchronized static TovarDatabase getInstance(Context context){
        if(tovarDatabase == null){
            tovarDatabase = Room.databaseBuilder(context.getApplicationContext()
                    ,TovarDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return tovarDatabase;
    }

    public abstract TovarDao  tovarDao();

}
