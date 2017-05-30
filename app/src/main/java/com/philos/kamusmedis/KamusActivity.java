package com.philos.kamusmedis;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class KamusActivity extends AppCompatActivity {
    String terms;
    private TextView artiKata;
    private TextToSpeech convertToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_kamus);

        TextView kata = (TextView)findViewById(R.id.kata);
        artiKata = (TextView)findViewById(R.id.kamus);
        Button textToSpeech = (Button)findViewById(R.id.button);

        Intent intent = getIntent();
        terms = intent.getStringExtra("KAMUS_KATA");

        kata.setText(terms);
        setArtiKata(terms);

        textToSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String convertTextToSpeech = artiKata.getText().toString();
                convertToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            convertToSpeech.setLanguage(new Locale("id","ID"));
                            convertToSpeech.speak(convertTextToSpeech, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    }
                });
            }
        });
    }

    public void setArtiKata(String a){
        DbBackend dbBackend = new DbBackend(KamusActivity.this);
        KamusObject allKamusQuestions = dbBackend.getKamusByKata(terms);
            artiKata.setText(allKamusQuestions.getDefinisi());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "tidak ada pengaturan tersedia!!!", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        if(convertToSpeech != null){
            convertToSpeech.stop();
            convertToSpeech.shutdown();
        }
        super.onPause();
    }
}
