package ca.dal.cs.csci4126.quizboard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    Bundle prefBundle;

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

        prefBundle = getIntent().getExtras();

        loadPreferences();
    }

    @Override
    public void onClick(View v) {
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
        editor.putBoolean("cbenglish", cbEnglish.isChecked());
        editor.putBoolean("cbphysics", cbPhysics.isChecked());
        editor.putBoolean("cbmathematics", cbMathematics.isChecked());
        editor.putBoolean("cbelectricalengineering", cbElectricalEngineering.isChecked());
        editor.putBoolean("cbchemistry", cbChemistry.isChecked());
        editor.putBoolean("cbcomputerscience", cbComputerScience.isChecked());
        editor.commit();
    }

    private void loadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        Boolean isCheckedEnglish = sharedPreferences.getBoolean("cbenglish", false);
        Boolean isCheckedPhysics = sharedPreferences.getBoolean("cbphysics", false);
        Boolean isCheckedMathematics = sharedPreferences.getBoolean("cbmathematics", false);
        Boolean isCheckedElectricalEngineering
                = sharedPreferences.getBoolean("cbelectricalengineering", false);
        Boolean isCheckedChemistry = sharedPreferences.getBoolean("cbchemistry", false);
        Boolean isCheckedComputerScience = sharedPreferences.getBoolean("cbcomputerscience", false);

        String userMajor = prefBundle.getString("userMajor");

        cbEnglish.setChecked(isCheckedEnglish);
        cbPhysics.setChecked(isCheckedPhysics);
        cbMathematics.setChecked(isCheckedMathematics);
        cbChemistry.setChecked(isCheckedChemistry);
        cbElectricalEngineering.setChecked(isCheckedElectricalEngineering);
        cbComputerScience.setChecked(isCheckedComputerScience);


        if ( userMajor.equals("English Department")){
            cbEnglish.setChecked(true);
            cbEnglish.setEnabled(false);
        }
        else if ( userMajor.equals("Physics Department")){
            cbPhysics.setChecked(true);
            cbPhysics.setEnabled(false);
        }
        else if ( userMajor.equals("Mathematics Department")){
            cbMathematics.setChecked(true);
            cbMathematics.setEnabled(false);
        }
        else if ( userMajor.equals("Chemistry Department")){
            cbChemistry.setChecked(true);
            cbChemistry.setEnabled(false);
        }
        else if ( userMajor.equals("Electrical Engineering Department")){
            cbElectricalEngineering.setChecked(true);
            cbElectricalEngineering.setEnabled(false);
        }
        else if ( userMajor.equals("Computer Science Department")){
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
