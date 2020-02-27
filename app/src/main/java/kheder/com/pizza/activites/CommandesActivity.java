package kheder.com.pizza.activites;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import kheder.com.pizza.Model.FlatCommande;
import kheder.com.pizza.R;
import kheder.com.pizza.adapter.CommandeArrayAdapter;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CommandesActivity extends AppCompatActivity {


    //LoginButton loginButton;
    CallbackManager callbackManager;
    LoginManager loginManager;
    Profile profile;
    ArrayList<FlatCommande> commandes;
    CommandesActivity that = this;
    ListView listView;
    ProgressBar progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_commandes);

        listView = (ListView) findViewById(R.id.listviewCommande);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //Profile profile = getIntent().getParcelableExtra("profile");
        profile = Profile.getCurrentProfile();

        TextView nom = (TextView) findViewById(R.id.nom);
        nom.setText("Hello, Customer");


        callbackManager = CallbackManager.Factory.create();

        loginManager  = LoginManager.getInstance();



        FloatingActionButton command = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        command.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommandesActivity.this,CommandeActivity.class);

                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:

                loginManager.logOut();
                Intent intent = new Intent(CommandesActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }
}
