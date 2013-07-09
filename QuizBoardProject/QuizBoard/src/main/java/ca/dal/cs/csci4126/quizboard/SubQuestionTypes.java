package ca.dal.cs.csci4126.quizboard;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

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
        bSubQuestCancel = (Button) findViewById(R.id.bSubQuestCancel);
    }

    @Override
    public void onClick(View v) {

    }
}
