package com.android.speechRecognizer;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1 ;
    EditText editTextArea;
    Spinner spinner1, spinner2;
    ImageButton speaknow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextArea = (EditText) findViewById(R.id.editTextArea);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        speaknow = (ImageButton) findViewById(R.id.btn_speak_now);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("English");
        spinnerArray.add("French");
        spinnerArray.add("Arabic");
        spinnerArray.add("Spanish");
        spinnerArray.add("Russian");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        speaknow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                String codeFromSpinner = spinner1.getSelectedItem().toString();
                codeFromSpinner = getCodeFromLang(codeFromSpinner);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, codeFromSpinner);
                intent.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", new String[]{codeFromSpinner});

                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please speak");
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("onActivityResult >>", "requestCode >> "+ requestCode);
        Log.i("onActivityResult >>", "resultCode >> "+ resultCode);
        Log.i("onActivityResult >>", "data >> "+ data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK)
            {
                List<String> results = data.getStringArrayListExtra
                        (RecognizerIntent.EXTRA_RESULTS);
                String mAnswer = results.get(0);
                editTextArea.setText(mAnswer);
            }
        }
    }

    public String getCodeFromLang(String lang)
    {
        String code = "";
        if(lang.equalsIgnoreCase("English"))
        {
            code = "en";
        }
        else if(lang.equalsIgnoreCase("French"))
        {
            code = "fr";
        }
        else if(lang.equalsIgnoreCase("Arabic"))
        {
            code = "ar";
        }
        else if(lang.equalsIgnoreCase("Spanish"))
        {
            code = "es";
        }
        else if(lang.equalsIgnoreCase("Russian"))
        {
            code = "ru";
        }
        return code;
    }
}
