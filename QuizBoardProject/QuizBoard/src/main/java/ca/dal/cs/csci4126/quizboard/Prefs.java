package ca.dal.cs.csci4126.quizboard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Prefs extends Activity implements OnClickListener {

    private RadioButton leadBoardAppearYes;
    private RadioButton leadBoardAppearNo;
    private Spinner selectMajor;
    private EditText inputUserName;
    private EditText inputBannerId;
    private Button mSaveButton;
    private Button mShowButton;
    private DB_Adapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefs);
        selectMajor = (Spinner) findViewById(R.id.spinnerMajor);
        leadBoardAppearYes = (RadioButton) findViewById(R.id.radioLeadBoardAppearYes);
        leadBoardAppearNo = (RadioButton) findViewById(R.id.radioLeadBoardAppearNo);
        inputUserName = (EditText) findViewById(R.id.pref_editName);
        inputBannerId = (EditText) findViewById(R.id.pref_editBannerId);
        mSaveButton = (Button) findViewById(R.id.save_button);
        mShowButton = (Button) findViewById(R.id.bSubTopicList);
        mSaveButton.setOnClickListener(this);
        mShowButton.setOnClickListener(this);
        dbAdapter = new DB_Adapter(this);
        loadPreferences();
    }

    @Override
    public void onClick(View v) {

        String message="";
        switch (v.getId()) {
            case R.id.save_button:
                SavePreferences();
                message="Text Saved in Preferences";
                break;
            case R.id.bSubTopicList:
                String major = selectMajor.getSelectedItem().toString();
                Bundle prefBundle = new Bundle();
                prefBundle.putString("prefMajor", major);
                Intent openSubQuest =
                        new Intent(Prefs.this, SubQuestionTypes.class);
                openSubQuest.putExtras(prefBundle);
                startActivity(openSubQuest);
                break;
        }
        //Don't want it to pop up when going to the custom questions page
        if(!message.equals(""))
             Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void SavePreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("prefName", inputUserName.getText().toString());
        editor.putString("prefBannerId", inputBannerId.getText().toString());
        editor.putBoolean("prefAppearYes", leadBoardAppearYes.isChecked());
        editor.putBoolean("prefAppearNo", leadBoardAppearNo.isChecked());
        editor.putInt("prefMajor", selectMajor.getSelectedItemPosition());
        dbAdapter.updateStudent(inputBannerId.getText().toString(),
                inputUserName.getText().toString());
        editor.commit();
    }

    private void loadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String prefName = sharedPreferences.getString("prefName", "");
        String prefBannerId = sharedPreferences.getString("prefBannerId", "");
        Boolean prefAppearYes = sharedPreferences.getBoolean("prefAppearYes", true);
        Boolean prefAppearNo = sharedPreferences.getBoolean("prefAppearNo",false);
        int majorPos = sharedPreferences.getInt("prefMajor",0);
        inputUserName.setText(prefName);
        inputBannerId.setText(prefBannerId);
        leadBoardAppearYes.setChecked(prefAppearYes);
        leadBoardAppearNo.setChecked(prefAppearNo);
        selectMajor.setSelection(majorPos);

    }
}