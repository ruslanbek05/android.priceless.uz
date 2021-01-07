package priceless.uz.tovar;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tovar_table")
public class Tovar implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "nomi")
    private String nomi;

    @ColumnInfo(name = "shtrix_kod")
    private String shtrix_kod;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNomi() {
        return nomi;
    }

    public void setNomi(@NonNull String nomi) {
        this.nomi = nomi;
    }

    public String getShtrix_kod() {
        return shtrix_kod;
    }

    public void setShtrix_kod(String shtrix_kod) {
        this.shtrix_kod = shtrix_kod;
    }
}
