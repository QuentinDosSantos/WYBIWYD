package wybiwyd.dossantos.diiage.org.wybiwyd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.btnNewBeer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        WybiwydDbHelper helper = new WybiwydDbHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        //add beer
        ContentValues cv= new ContentValues();
        cv.put("name", "LAG");
        cv.put("alc", 4.5);
        cv.put("desc", "bierre du réseau");
        cv.put("price", 0.5);

        long id= db.insert("Beer", null, cv);
        Log.d("DEBUG", "Bierre inseré");
        //update created beer
        ContentValues updateContentValues = new ContentValues();
        updateContentValues.put("alc", 4.7);
        db.update("Beer", updateContentValues, "id = ?", new String[]{String.valueOf(id)});
        Log.d("DEBUG", "Bierre modifié");

        /*
        try {
            db.beginTransaction();
            //del created beer
            db.delete("Beer", "id = ?", new String[]{String.valueOf(id)});
            if (true){
                throw new Exception();
            }

            //Si une transiction se termine sans cette méthode, il y a rollback.
            db.setTransactionSuccessful();
        }
        catch (Exception ex){
            //en fait non
        }
        finally {
            db.endTransaction();
        }*/

        Log.d("DEBUG", "Bierre supprimé");

        Cursor cursor = db.query("Beer", new String[]{"*"}, null, null, null, null, null);

        ArrayList<Beer> beers = new ArrayList<>();
        while (cursor.moveToNext())
        {
           beers.add(Beer.fromCursor(cursor));
        }
        cursor.close();
        db.close();

        ((ListView)findViewById(R.id.lstBeers))
                .setAdapter(new ArrayAdapter<Beer>(this, R.layout.list_item, beers));
    }
}
