package com.example.topquizz.controler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.topquizz.R;
import com.example.topquizz.model.Question;
import com.example.topquizz.model.QuestionBank;

import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mGameQuestion;
    private Button mButtonAnswer1;
    private Button mButtonAnswer2;
    private Button mButtonAnswer3;
    private Button mButtonAnswer4;

    private QuestionBank mQuestionBank;
    private Question mQuestion;
    private int mNumberOfQuestion;
    private int mScore;

    /* Variable de classe pour stocker le nom de la cle de l'Intent
    Cette cle sera utilise par la MainActivity pour recuperer le score */
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    /* Booleen verifiant si l'action de l'utilisateur est pris en compte ou pas :
    true pour oui et false pour non */
    private boolean mEnableTouchEvents;

    /* Variables de classe stockant les cles du score et du nombre de question en cours */
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity :: onCreate()");

        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestion = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        }else{
            mScore = 0;
            mNumberOfQuestion = 10;
        }

        mGameQuestion = (TextView) findViewById(R.id.game_question);
        mButtonAnswer1 = (Button) findViewById(R.id.game_answer1);
        mButtonAnswer2 = (Button) findViewById(R.id.game_answer2);
        mButtonAnswer3 = (Button) findViewById(R.id.game_answer3);
        mButtonAnswer4 = (Button) findViewById(R.id.game_answer4);

        mQuestionBank = this.QuestionGeneration();

        // On place un ecouteur de click sur chaque bouton
        mButtonAnswer1.setOnClickListener(this);
        mButtonAnswer2.setOnClickListener(this);
        mButtonAnswer3.setOnClickListener(this);
        mButtonAnswer4.setOnClickListener(this);
        // On place un identifiant sur chaque bouton pour connaitre sur lequel l'utilisateur a clique
        mButtonAnswer1.setTag(0);
        mButtonAnswer2.setTag(1);
        mButtonAnswer3.setTag(2);
        mButtonAnswer4.setTag(3);

        mQuestion = mQuestionBank.getQuestion();
        displayQuestion(mQuestion);

        mEnableTouchEvents = true;
    }

    private QuestionBank QuestionGeneration(){
        Question question1 = new Question("Comment s'appelle le titan de Eren en Japonais ?",
                                          Arrays.asList("Shingeki No Kyojin", "Kyojin", "Shingetsu Bahamut", "Shingeki"),
                                           0);
        Question question2 = new Question("Le type de lecteur ciblé par les studios d’edition varie en fonction des editeurs." +
                "C’est l’exemple de <L'attaque des Titans> qui voit sa cible editoriale variée." +
                "Qu’elles sont les deux types de mangas sous lequel Shingeki peut facilement se fondre ?",
                                          Arrays.asList("Shonen & Yaoi", "Seinen & Josei", "Shonen & Seinen", "Shonen & Josei"),
                                           2);
        Question question3 = new Question("Donnez les noms des 3 murs du plus petit au plus grand dans lequel l’humanité se cache " +
                "pour se proteger des Titans ? ",
                                          Arrays.asList("Mur Sina-Mur Rose-Mur Maria", "Mur Rose-Mur Sina-Mur Maria", "Mur Maria-Mur Sina-Mur Rose", "Mur Rose-Mur Maria-Mur Sina"),
                                           0);
        Question question4 = new Question("De combien de mètres le Titan Colossal dépasse t’il le Mur Maria ?",
                                          Arrays.asList("5 m", "10 m", "15 m", "20 m"),
                                            1);
        Question question5 = new Question("Au sein du bataillon d'exploration, quel est le grade de Livai Ackerman ?",
                                          Arrays.asList("Caporal-Chef", "Caporal-Major", "Lieutenant-Caporal", "Caporal"),
                                            0);
        Question question6 = new Question("Comment s’appelle le liquide qui sert a la transmission du pouvoir de metarmorphose en Titan ?",
                                          Arrays.asList("Liquide Celebro-Spinale", "Liquide Cerebro-Spilale", "Liquide Cerebro-Spinale", "Liquide Celebro-Spilale"),
                                            2);
        Question question7 = new Question("Quelle est la profession de l’homme dont l'auteur s’est inspiré pour créer le Chara-Design du Titan Cuirassé ?",
                                          Arrays.asList("Sprinteur", "Catcheur", "Forgeron", "Boxeur"),
                                            1);
        Question question8 = new Question("C’est quoi le nom de l’opening 1 et de l’ending 2 de Shingeki no Kyojin ?",
                                          Arrays.asList("Guren No Yumiya & Great Escape", "Sasageyo & Call Your Name", "Jiyuu No Tsubasa & Utsukushiki Zankoku Na Sekai", "The Reluctant Heroes & DOA"),
                                            0);
        Question question9 = new Question("Qui a construit les murs qui protège les Eldiens des Titans ?",
                                          Arrays.asList("Karl Fritz", "Les Mahrs", "Les Titans Primordiaux", "Ymir"),
                                            0);
        Question question10 = new Question("Un Eldien est un natif de… ",
                                          Arrays.asList("L'ile du Paradis", "L'Empire Fritz", "L'Empire Eldienne", "L'Empire d'Eldia"),
                                            3);
        Question question11 = new Question("D’où est ce que le Roi Karl Fritz s’est t’il inspiré pour nommer les différents Murs sur l’ile du paradis ?",
                                          Arrays.asList("De ses 3 filles", "Des 3 soeurs d'Ymir Fritz", "Des 3 premieres detenteuses de l'Originel", "Des 3 filles d'Ymir Fritz"),
                                            3);
        Question question12 = new Question("Pourquoi le 145e Roi d’Eldia fit le vœu de renoncer à utiliser le pouvoir du TITAN ORIGINEL ?",
                                          Arrays.asList("Pour proteger son peuple de l'exterieur", "Pour garder l'equilibre du Monde", "Par pitie des peuples exterieurs", "Pour s’assurer que le titan en question ne soit jamais utilisé a de mauvaises fins"),
                                            3);
        Question question13 = new Question("Combien d’année ce sont Ecoulée entre la destruction du Mur dans le district de Shiganshina et la conquete de " +
                "celle si par le bataillon d’exploration ? ",
                                          Arrays.asList("3 ans", "4 ans", "5 ans", "6 ans"),
                                            2);
        Question question14 = new Question("Quel est le nom complet de la premiere victime D’Armin en titan ?",
                                          Arrays.asList("Bertolt Hoover", "Erwin Smith", "Marco Bott", "Reiner Braun"),
                                            0);
        Question question15 = new Question("Après la Bataille ou fut mort le Major Erwin, a combien s’éleve le nombre de Survivant au sein du bataillon d’exploration ?",
                                          Arrays.asList("9", "8", "10", "7"),
                                            0);
        Question question16 = new Question("Le district de Trost est rataché a quel Mur ?",
                                            Arrays.asList("Le Mur Maria", "Le Mur Rose", "Le Mur Sina", "Le Mur Shiganshina"),
                                            1);
        Question question17 = new Question("Combien de Titan Primordiaux sont impliqués a la bataille de Shiganshina ?",
                                            Arrays.asList("5", "4", "3", "6"),
                                            0);
        Question question18 = new Question("Combien de jeunes en tout faisaient partie de la 104e Brigade d’entrainement ?",
                                            Arrays.asList("216", "217", "218", "219"),
                                            2);
        Question question19 = new Question("Quel est l’autre nom donner au Bataillon d’exploration ?",
                                            Arrays.asList("Rodeurs des Murs", "Bataillon de la Liberte", "Chasseurs de Titans", "Corps de Reconnaissance"),
                                            3);
        Question question20 = new Question("Comment s’appelle les ailes qui se trouvent sur l’insigne du bataillon d’explorations ? ",
                                            Arrays.asList("Les Ailes de la Foi", "Les Ailes de l'Exploration", "Les Ailes de la Liberté", "Les Ailes du Courage"),
                                            2);
        Question question21 = new Question("Après la mort du Major Erwin Smith qui prend le commandement du bataillon d’exploration ?",
                                            Arrays.asList("Armin Arlert", "Hansi Zoe", "Livai Ackerman", "Eren Jager"),
                                            1);
        Question question22 = new Question("Quel est le nom complet de l’assassin de BEAN & SAWNEY ?",
                                            Arrays.asList("Jean Kirstein", "Annie Leonhart", "Reiner Braun", "Aucune des propositions"),
                                            1);
        Question question23 = new Question("Un detenteur de Titan Primordial a une longevite de combien d'annees ?",
                                            Arrays.asList("7 ans", "20 ans", "13 ans", "16 ans"),
                                            2);
        Question question24 = new Question("A quel age Eren obtient son Titan Primordial ?",
                                            Arrays.asList("9 ans", "10 ans", "11 ans", "12 ans"),
                                            1);
        Question question25 = new Question("Qui est l'auteur de Shingeki No Kyojin ?",
                                            Arrays.asList("Masashi Kishimoto", "Eichiro Oda", "Hiro Mashima", "Hajime Isayama"),
                                            3);
        Question question26 = new Question("Comment s'appelle la Reine de l'ile du Paradis ?",
                                            Arrays.asList("Historia Fritz", "Historia Reiss", "Christa Reiss", "Christa Fritz"),
                                            1);
        /*Question question27 = new Question("Quelle est la condition pour activer le grand terassement ?",
                                            Arrays.asList("", "", "", ""),
                                            1);*/
        Question question28 = new Question("En quelle annee est sortie Shingeki No Kyojin ?",
                                            Arrays.asList("1999", "2006", "2009", "2013"),
                                            2);
        Question question29 = new Question("Combien de Titans possede Eren ?",
                                            Arrays.asList("3", "1", "2", "4"),
                                            0);
        Question question30 = new Question("Comment s'appelle le soldat qui a sauve Eren et Mikasa lors de la premiere apparition du " +
                "titan colossal ?",
                                            Arrays.asList("Dieter", "Moses", "Waltz", "Hannes"),
                                            3);
        return new QuestionBank(Arrays.asList(question1,question2,question3,question4,question5,question6,question7,question8,question9,question10,
                                                question11,question12,question13,question14,question15,question16,question17,question18,question19,question20,
                                                question21,question22,question23,question24,question25,question26,/*question27,*/question28,question29,question30));
    }

    // Affichage de la question ainsi que des choix possibles a l'ecran
    private void displayQuestion(final Question question){
        mGameQuestion.setText(question.getQuestion());
        List<String> reponse = question.getChoiceList();
        mButtonAnswer1.setText(reponse.get(0));
        mButtonAnswer2.setText(reponse.get(1));
        mButtonAnswer3.setText(reponse.get(2));
        mButtonAnswer4.setText(reponse.get(3));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestion);
    }

    // Methodes de l'interface View.OnClickListener
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        int reponseIndex = (int) v.getTag();
        if(reponseIndex == mQuestion.getAnswerIndex()){
            // Instruction en cas de choix correct
            // On peut utiliser la methode .setDuration() sur une instance Toast pour attribuer un temps
            Toast.makeText(this, "Correct !", Toast.LENGTH_SHORT).show();
            mScore++;
        }else{
            // Instruction en cas de choix incorrect
            Toast.makeText(this, "Incorrect !", Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents = false;

        mNumberOfQuestion--;
        new Handler().postDelayed(new Runnable() {
            /* La classe Handler permet de communiquer avec systeme
            La methode .postDelayed permettant d'effectuer une tache apres une duree de temps precise
            Les taches a effectuer sont dans la fonction run() */
            @Override
            public void run() {
                mEnableTouchEvents = true;

                if(mNumberOfQuestion == 0){
                    // Fin du jeu
                    end();
                }else{
                    // Question suivante
                    mQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mQuestion);
                }
            }
        }, 2000);   // Temps en millisecondes
    }

    // Fonction qui arrete le jeu
    private void end (){
        /* La classe AlertDialog est celle qui s'occupe des boites de dialogue
        L'objet Builder est l'objet specifique qui construit la boite */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Bravo")   // Titre de la boite de dialogue
                .setMessage("Votre score est de "+mScore)   // Message de la boite de dialogue
                // Message du bouton et implementation de l'interface qui gere le clic du bouton
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /* Envoi du resultat (score) a une autre activite */
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }).create().show();
    }

    /* Methode permettant de dire au systeme si l'action de l'utilisateur doit etre pris en compte ou non
    en fonction de la valeur de verite renvoye
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    /* Les differents etats d'une application mobile sont les suivantes
    en plus de la methode onCreate() plus haut */

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("GameActivity :: onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("GameActivity :: onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("GameActivity :: onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("GameActivity :: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("GameActivity :: onDestroy()");
    }
}