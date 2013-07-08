package ca.dal.cs.csci4126.quizboard;

<<<<<<< HEAD
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Prefs extends Activity implements OnClickListener {

    private RadioButton leadBoardAppearYes;
    private RadioButton leadBoardAppearNo;
    private Spinner selectMajor;
    private EditText mInputEditText;
    private Button mSaveButton;
    private Button mShowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefs);
        selectMajor = (Spinner) findViewById(R.id.spinnerMajor);
        leadBoardAppearYes = (RadioButton) findViewById(R.id.radioLeadBoardAppearYes);
        leadBoardAppearNo = (RadioButton) findViewById(R.id.radioLeadBoardAppearNo);
        mInputEditText = (EditText) findViewById(R.id.pref_editText);
        mSaveButton = (Button) findViewById(R.id.save_button);
        mShowButton = (Button) findViewById(R.id.bSubTopicList);
        mSaveButton.setOnClickListener(this);
        mShowButton.setOnClickListener(this);
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
                //SavePreferences("quizBoardPrefs", mInputEditText.getText().toString());
                message="Nothing as of yet...";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void SavePreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("prefName", mInputEditText.getText().toString());
        editor.putBoolean("prefAppearYes", leadBoardAppearYes.isChecked());
        editor.putBoolean("prefAppearNo", leadBoardAppearNo.isChecked());
        editor.putInt("prefMajor", selectMajor.getSelectedItemPosition());
        editor.commit();
    }

    private void loadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String savedPref = sharedPreferences.getString("quizBoardPrefs", "");
        Boolean prefAppearYes = sharedPreferences.getBoolean("prefAppearYes", true);
        Boolean prefAppearNo = sharedPreferences.getBoolean("prefAppearNo",false);
        int majorPos = sharedPreferences.getInt("prefMajor",0);
        //mOutputView.setText(savedPref);
        mInputEditText.setText(savedPref);
        leadBoardAppearYes.setChecked(prefAppearYes);
        leadBoardAppearNo.setChecked(prefAppearNo);
        selectMajor.setSelection(majorPos);

=======
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Prefs extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
>>>>>>> 5a3f6804d375a29f5245ac29068fce7fbefffbfc
    }
}