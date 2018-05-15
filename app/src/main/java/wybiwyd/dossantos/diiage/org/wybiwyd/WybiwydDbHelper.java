package wybiwyd.dossantos.diiage.org.wybiwyd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Quentin on 15/05/2018.
 */

public class WybiwydDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String DBNAME = "wybiwyd";

    public WybiwydDbHelper(Context context) {
        super(context, "wybiwyd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE \"Beer\" ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `desc` TEXT, `alc` REAL, `price` REAL, `idGamme` INTEGER )");
        sqLiteDatabase.execSQL("CREATE TABLE `Gamme` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `desc` TEXT )");
        sqLiteDatabase.execSQL("CREATE TABLE \"Ingredient\" ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT )");
        sqLiteDatabase.execSQL("CREATE TABLE `Beer_Ingredient` ( `idBeer` INTEGER, `idIngredient` INTEGER, PRIMARY KEY(`idIngredient`,`idBeer`) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < 2){
            sqLiteDatabase.execSQL("ALTER TABLE Beer ADD COLUMN idGamme INTEGER");
            sqLiteDatabase.execSQL("CREATE TABLE `Gamme` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `desc` TEXT )");
            sqLiteDatabase.execSQL("CREATE TABLE \"Ingredient\" ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT )");
            sqLiteDatabase.execSQL("CREATE TABLE `Beer_Ingredient` ( `idBeer` INTEGER, `idIngredient` INTEGER, PRIMARY KEY(`idIngredient`,`idBeer`) )");

            sqLiteDatabase.execSQL("INSERT INTO `Beer`(`name`,`desc`,`alc`,`price`) VALUES ('Ruby','Ruby',5,1)");
            sqLiteDatabase.execSQL("INSERT INTO `Beer`(`name`,`desc`,`alc`,`price`) VALUES ('Blonde','Blonde',5,1)");
            sqLiteDatabase.execSQL("INSERT INTO `Beer`(`name`,`desc`,`alc`,`price`) VALUES ('Brune','Brune',5,1)");
            sqLiteDatabase.execSQL("INSERT INTO `Beer`(`name`,`desc`,`alc`,`price`) VALUES ('Ambrée','Ambrée',5,1)");
        }
    }
}
