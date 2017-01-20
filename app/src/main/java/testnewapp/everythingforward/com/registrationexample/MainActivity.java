package testnewapp.everythingforward.com.registrationexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import testnewapp.everythingforward.com.registrationexample.service.LoginIntentService;

public class MainActivity extends AppCompatActivity {

    ImageView registerButton;
    EditText usernameET, passwordET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        registerButton = (ImageView)findViewById(R.id.imageView);
        usernameET = (EditText)findViewById(R.id.editText);

        passwordET=(EditText)findViewById(R.id.editText3);








        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginIntentService.class);
                ArrayList<String> userData = new ArrayList<String>();
                userData.add(usernameET.getText().toString());
                userData.add(passwordET.getText().toString());

                Bundle throwBundle = new Bundle();
                throwBundle.putStringArrayList("key",userData);

                intent.putExtras(throwBundle);

                startService(intent);

                Toast.makeText(MainActivity.this, "Registering User", Toast.LENGTH_SHORT).show();


            }
        });


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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
