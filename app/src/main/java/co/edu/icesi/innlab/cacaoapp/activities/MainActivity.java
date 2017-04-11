package co.edu.icesi.innlab.cacaoapp.activities;
/**
 * Created by Jose Moncada on 25/09/2016.
 * Version 1
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.edu.icesi.innlab.cacaoapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        //  finish();
    }
}
