package ar.edu.itba.mygymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import ar.edu.itba.mygymapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
            Luego del binding voy a poder acceder a todos los elementos
            del html, por ejemplo a los textView, botones, etc.
            Ejemplo:
            activityMainBinding.myTextView.setText(R.string.app_name);
         */

        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

    }

    public void login(View view) {
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }

    public void register(View view) {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
}