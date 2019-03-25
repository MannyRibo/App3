package com.example.reminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText NieuweURL;
    private EditText NieuweNaam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Init local variables
        NieuweURL = findViewById(R.id.editText_update);
        NieuweNaam = findViewById(R.id.editText_update2);

        Button addButton = findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nieuweURL = NieuweURL.getText().toString();
                String nieuweNaam = NieuweNaam.getText().toString();


                Website nieuweWebsite = new Website(nieuweURL, nieuweNaam);

                if ((!TextUtils.isEmpty(nieuweURL)) && (!TextUtils.isEmpty(nieuweNaam))) {
                    nieuweWebsite.setURL(nieuweURL);
                    nieuweWebsite.setmWebsiteName(nieuweNaam);
                    //Prepare the return parameter and return
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(MainActivity.WEBSITE_TOEVOEGEN, nieuweWebsite);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(AddActivity.this, "Voer een URL en websitenaam in", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}