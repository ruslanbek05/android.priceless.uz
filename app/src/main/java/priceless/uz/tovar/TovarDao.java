package priceless.uz.tovar;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TovarDao {

    @Insert(onConflict = REPLACE)
    void insertTovar(Tovar tovar);

    @Delete
    void delete(Tovar tovar);

    @Delete
    void reset(List<Tovar> tovars);

    @Query("UPDATE tovar_table SET nomi = :sNomi WHERE id = :sid")
    void update(int sid, String sNomi);

    @Query("SELECT * FROM tovar_table")
    List<Tovar> getAll();

}
