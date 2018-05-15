package wybiwyd.dossantos.diiage.org.wybiwyd;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Quentin on 15/05/2018.
 */

public class Beer {
    Long id;
    String name;
    String desc;
    Double alc;
    Double price;

    public static Beer fromCursor(Cursor cursor)
    {
        Beer b = new Beer();
        b.id = cursor.getLong(cursor.getColumnIndex("id"));
        b.alc = cursor.getDouble(cursor.getColumnIndex("alc"));
        b.desc = cursor.getString(cursor.getColumnIndex("desc"));
        b.name = cursor.getString(cursor.getColumnIndex("name"));
        b.price = cursor.getDouble(cursor.getColumnIndex("price"));
        return b;
    }

    public ContentValues toContentValues()
    {
        ContentValues cv = new ContentValues();
        if (name != null)
            cv.put("name", name);
        if (desc != null)
            cv.put("desc", desc);
        if (alc != null)
            cv.put("alc", alc);
        if (price != null)
            cv.put("price", price);
        return cv;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
