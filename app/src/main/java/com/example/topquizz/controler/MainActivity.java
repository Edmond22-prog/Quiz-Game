package com.example.topquizz.controler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.topquizz.R;
import com.example.topquizz.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private TextInputEditText mNameInput;
    private Button mPlayButton;

    private User mUser;
    private SharedPreferences mPreferences; // Variable de l'API permettant de stocker des informations

    // Variable d'instance definissant l'identifiant de la GameActivity
    private static final int GAME_ACTIVITY_REQUEST_CODE = 1;
    // Variable d'instance definissant l'identifiant du nom de l'utilisateur/joueur
    public static final String FIRSTNAME_PLAYER = "FIRSTNAME_PLAYER";
    // Variables d'instance definissant l'identifiant du score de l'utilisateur/joueur
    public static final String SCORE_PLAYER = "SCORE_PLAYER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity :: onCreate()");

        mPreferences = getPreferences(MODE_PRIVATE);

        mGreetingText = (TextView) findViewById(R.id.text_start);
        mNameInput = (TextInputEditText) findViewById(R.id.edit_start_text);
        mPlayButton = (Button) findViewById(R.id.first_button);

        mPlayButton.setEnabled(false);  // Desactive le bouton au demarrage

        mUser = new User();
        salutationUtilisateur();

        /* La methode .addTextChangedListener()
        appelle la fonction onTextChanged qui verifie si un text est entre dans l'EditText */
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // On met le bouton en ecoute, en attente d'un click
        mPlayButton.setOnClickListener(new View.OnClickListener(){

            // Fonction qui s'execute a chaque click du bouton
            public void onClick(View v){
                /* Instructions permettant de quitter d'une activite a une autre */
                Intent gameActivity = new Intent (MainActivity.this, GameActivity.class);
                //startActivity(gameActivity);  Demarrage simple de l'activite
                startActivityForResult(gameActivity, GAME_ACTIVITY_REQUEST_CODE);   // Demarrage de l'activite avec attente de resultat

                mUser.setFirstName(mNameInput.getText().toString());
                // Insertion du nom dans le fichier
                mPreferences.edit().putString(FIRSTNAME_PLAYER, mUser.getFirstName()).apply();
            }
        });
    }

    // Methode qui recupere le resultat d'une autre activite
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*
        Les conditions du if signifie :
        1 ere : Verification de l'identifiant de l'activite dont on veut le resultat
        2 eme : Verification de la finition de la dites activite
         */
        if(GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            // Recuperation du score et du nombre de question pose de l'activite
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            int nombreQuestion = data.getIntExtra(GameActivity.BUNDLE_STATE_QUESTION, 0);
            // Insertion du score dans le fichier
            mPreferences.edit().putInt(SCORE_PLAYER, score).apply();

            salutationUtilisateur();
        }
    }

    private void salutationUtilisateur (){
        String firstName = mPreferences.getString(FIRSTNAME_PLAYER, null);
        if(firstName != null){
            int score = mPreferences.getInt(SCORE_PLAYER, 0);
            String Salutation = "Bon retour, "+firstName+"!\nTon dernier score etait "+score+"/10, feras-tu mieux cette fois ?";
            mGreetingText.setText(Salutation);
            mNameInput.setText(firstName);
            mNameInput.setSelection(firstName.length());
            mPlayButton.setEnabled(true);
        }
    }

    /* Les differents etats d'une application mobile sont les suivantes
    en plus de la methode onCreate() plus haut */

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("MainActivity :: onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("MainActivity :: onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("MainActivity :: onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("MainActivity :: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivity :: onDestroy()");
    }
}