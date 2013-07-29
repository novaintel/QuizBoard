package ca.dal.cs.csci4126.quizboard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by James on 7/7/13.
 */
public class SubQuestionTypes extends Activity implements View.OnClickListener {
    CheckBox cbEnglish;
    CheckBox cbPhysics;
    CheckBox cbMathematics;
    CheckBox cbChemistry;
    CheckBox cbElectricalEngineering;
    CheckBox cbComputerScience;

    Button bSubQuestSubmit;
    Button bSubQuestCancel;

    Bundle getPref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subquestiontypes);

        cbEnglish = (CheckBox) findViewById(R.id.cbEnglish);
        cbPhysics = (CheckBox) findViewById(R.id.cbPhysics);
        cbMathematics = (CheckBox) findViewById(R.id.cbMathematics);
        cbChemistry = (CheckBox) findViewById(R.id.cbChemistry);
        cbElectricalEngineering = (CheckBox) findViewById(R.id.cbElectricalEngineering);
        cbComputerScience = (CheckBox) findViewById(R.id.cbComputerScience);

        bSubQuestSubmit = (Button) findViewById(R.id.bSubQuestSubmit);
        bSubQuestSubmit.setOnClickListener(this);
        bSubQuestCancel = (Button) findViewById(R.id.bSubQuestCancel);
        bSubQuestCancel.setOnClickListener(this);

        getPref = getIntent().getExtras();

        loadPreferences();
    }

    @Override
    public void onClick(View v) {

        String message="";
        switch (v.getId()) {
            case R.id.bSubQuestSubmit:
                SavePreferences();
                onPause();
                break;
            case R.id.bSubQuestCancel:
                onPause();
                break;
        }
    }

    private void SavePreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("subQuestEnglish", cbEnglish.isChecked());
        editor.putBoolean("subQuestPhysics", cbPhysics.isChecked());
        editor.putBoolean("subQuestMathematics", cbMathematics.isChecked());
        editor.putBoolean("subQuestChemistry", cbChemistry.isChecked());
        editor.putBoolean("subQuestElectricalEngineering", cbElectricalEngineering.isChecked());
        editor.putBoolean("subQuestComputerScience", cbComputerScience.isChecked());

        editor.commit();
    }

    private void loadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        Boolean cbEnglishisChecked = sharedPreferences.getBoolean("subQuestEnglish", false);
        Boolean cbPhysicsisChecked = sharedPreferences.getBoolean("subQuestPhysics",false);
        Boolean cbMathematicsisChecked = sharedPreferences.getBoolean("subQuestMathematics", false);
        Boolean cbChemistryisChecked = sharedPreferences.getBoolean("subQuestChemistry",false);
        Boolean cbElectricalEngineeringisChecked
                = sharedPreferences.getBoolean("subQuestElectricalEngineering", false);
        Boolean cbComputerScienceisChecked
                = sharedPreferences.getBoolean("subQuestComputerScience",false);

        String userMajor = getPref.getString("prefMajor");

         cbEnglish.setChecked(cbEnglishisChecked);
         cbPhysics.setChecked(cbPhysicsisChecked);
         cbMathematics.setChecked(cbMathematicsisChecked);
         cbChemistry.setChecked(cbChemistryisChecked);
         cbElectricalEngineering.setChecked(cbElectricalEngineeringisChecked);
         cbComputerScience.setChecked(cbComputerScienceisChecked);

        if(userMajor.equals("English Department")){
            cbEnglish.setChecked(true);
            cbEnglish.setEnabled(false);
        } else if(userMajor.equals("Physics Department")){
            cbPhysics.setChecked(true);
            cbPhysics.setEnabled(false);
        } else if(userMajor.equals("Mathematics Department")){
            cbMathematics.setChecked(true);
            cbMathematics.setEnabled(false);
        } else if(userMajor.equals("Chemistry Department")){
            cbChemistry.setChecked(true);
            cbChemistry.setEnabled(false);
        } else if(userMajor.equals("Electrical Engineering Department")){
            cbElectricalEngineering.setChecked(true);
            cbElectricalEngineering.setEnabled(false);
        } else if(userMajor.equals("Computer Science Department")){
            cbComputerScience.setChecked(true);
            cbComputerScience.setEnabled(false);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
