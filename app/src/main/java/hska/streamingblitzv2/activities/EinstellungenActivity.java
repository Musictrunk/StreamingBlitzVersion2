package hska.streamingblitzv2.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import hska.streamingblitzv2.R;
import hska.streamingblitzv2.dao.DBHelper;
import hska.streamingblitzv2.dao.DatabaseSchema;
import hska.streamingblitzv2.model.Einstellungen;

public class EinstellungenActivity extends AppCompatActivity {

    String userString;
    String TAG = "LOGGING-Einstellungen";
    SQLiteDatabase mDb;
    DBHelper dbAdapter;
    CheckBox checkBoxEinstellungenNetflix;
    CheckBox checkBoxEinstellungenAmazon;
    CheckBox checkBoxEinstellungenMaxdome;
    CheckBox checkBoxEinstellungenSnap;
    Integer netflix;
    Integer amazon;
    Integer maxdome;
    Integer snap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        userString = extras.getString("EXTRA_USER");

        Log.d(TAG, "- " + userString);

        ((TextView) findViewById(R.id.txt_einstellungen_username)).setText(userString);

        checkBoxEinstellungenNetflix = (CheckBox) findViewById(R.id.checkBox_einstellungen_netflix);
        checkBoxEinstellungenAmazon = (CheckBox) findViewById(R.id.checkBox_einstellungen_amazon);
        checkBoxEinstellungenMaxdome = (CheckBox) findViewById(R.id.checkBox_einstellungen_maxdome);
        checkBoxEinstellungenSnap = (CheckBox) findViewById(R.id.checkBox_einstellungen_snap);

        dbAdapter = new DBHelper(this);
        try {
            dbAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.loadSettings(userString);


            checkBoxEinstellungenNetflix.setChecked((netflix == 0? false:true));
            checkBoxEinstellungenAmazon.setChecked((amazon == 0? false:true));
            checkBoxEinstellungenMaxdome.setChecked((maxdome == 0 ? false : true));
            checkBoxEinstellungenSnap.setChecked((snap == 0 ? false : true));
    }

    public void saveSettings(View view){
        /*InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(checkBoxNetflix.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(checkBoxAmazon.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(checkBoxMaxdome.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(checkBoxSnap.getWindowToken(), 0);
        Einstellungen einstellungen = new Einstellungen(checkBoxNetflix.isChecked(), checkBoxAmazon.isChecked(), checkBoxMaxdome.isChecked(), checkBoxSnap.isChecked(), userString);
        try {
            Einstellungen i = dbAdapter.insertEinstellungen(einstellungen);
            if (i == einstellungen)
                Toast.makeText(RegisterEinstellungenActivity.this, "Einstellung wurde angelegt", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(RegisterEinstellungenActivity.this, "Etwas lief schief!",
                    Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this, SucheActivity.class);
        String message = userString;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);*/
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_einstellungen, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_history:
                showHistory();
                break;
            case R.id.menu_scanner:
                showScanner();
                break;
            case R.id.menu_hilfe:
                showHilfe();
                break;
            case R.id.menu_einstellungen:
                showEinstellungen();
                break;

            default:
                break;
        }
        return false;
    }

    protected void showHistory()
    {
        Intent i = new Intent(this, HistoryActivity.class);
        startActivity(i);
    }

    protected void showScanner()
    {
        Intent i = new Intent(this, ScanActivity.class);
        startActivity(i);
    }

    protected void showHilfe()
    {
        Intent i = new Intent(this, HilfeActivity.class);
        startActivity(i);
    }

    protected void showEinstellungen()
    {
        Intent i = new Intent(this, EinstellungenActivity.class);
        startActivity(i);

    }
}
