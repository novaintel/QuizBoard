package ca.dal.cs.csci4126.quizboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by James on 7/7/13.
 */
public class SubQuestionTypes extends Activity implements View.OnClickListener {
    CheckBox cbEnglish = (CheckBox) findViewById(R.id.cbEnglish);
    CheckBox cbPhysics = (CheckBox) findViewById(R.id.cbPhysics);
    CheckBox cbMathematics = (CheckBox) findViewById(R.id.cbMathematics);
    CheckBox cbChemistry = (CheckBox) findViewById(R.id.cbChemistry);
    CheckBox cbElectricalEngineering = (CheckBox) findViewById(R.id.cbElectricalEngineering);
    CheckBox cbComputerScience = (CheckBox) findViewById(R.id.cbComputerScience);

    Button bSubQuestSubmit = (Button) findViewById(R.id.bSubQuestSubmit);
    Button bSubQuestCancel = (Button) findViewById(R.id.bSubQuestCancel);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subQuestionTypes);
    }

    @Override
    public void onClick(View v) {

    }
}
