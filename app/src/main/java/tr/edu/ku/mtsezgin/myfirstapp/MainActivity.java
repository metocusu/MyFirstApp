package tr.edu.ku.mtsezgin.myfirstapp;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("MainActivity", "onCreate");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Log.v( "menu handler", "Menu item selected " + item );

        switch (item.getItemId()) {
            case R.id.action_search:
                //openSearch();
                Log.v("menu handler","search menu");
                return true;
            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Alternative button */
    public void alternativeActionMethod(View view) {
        // We’ll think about this
        Intent intent = new Intent(this, ImageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        System.out.println("Trying to send message " + message );
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Write a message to the database

        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        System.out.println("Trying to send message " + message );
        intent.putExtra(EXTRA_MESSAGE, message);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue(message);
        Log.d("MainActivity", "Sent database request.");

        Point  p        = new Point( (int) (Math.random()*10),
                                     (int) (Math.random()*10) );
        // String p_id = myRef.push().getKey();
        String p_id = String.valueOf( p.hashCode() );
        myRef = database.getReference( p_id );
        myRef.child( p_id ).setValue(p);



        startActivity(intent);
    }
}
