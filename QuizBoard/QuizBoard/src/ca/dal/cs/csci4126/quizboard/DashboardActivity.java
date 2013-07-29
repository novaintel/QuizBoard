package ca.dal.cs.csci4126.quizboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import br.ufscar.dc.thingbroker.interfaces.ThingBrokerRequestListener;
import br.ufscar.dc.thingbroker.model.Thing;
import br.ufscar.dc.thingbroker.services.impl.ThingServiceImpl;
import ca.dal.cs.csci4126.quizboard.library.DatabaseHandler;
import ca.dal.cs.csci4126.quizboard.library.UserFunctions;


public class DashboardActivity extends Activity {
	UserFunctions userFunctions;
	Button btnLogout;
	Button bPrefs;
	private RadioGroup radioChoiceGroup;
	private RadioButton radio0;
	private RadioButton radio1;
	private RadioButton radio2;
	private RadioButton radio3;
	ThingServiceImpl myThing;
	private Thing crazyThing;
	private String questionId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DatabaseHandler db = new DatabaseHandler(this);
		setContentView(R.layout.dashboard);

		radioChoiceGroup = (RadioGroup) findViewById(R.id.radioGroup1);

		radio0 = (RadioButton) findViewById(R.id.radio0);
		radio1 = (RadioButton) findViewById(R.id.radio1);
		radio2 = (RadioButton) findViewById(R.id.radio2);
		radio3 = (RadioButton) findViewById(R.id.radio3);


		btnLogout = (Button) findViewById(R.id.btnLogout);


		btnLogout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//userFunctions.logoutUser(getApplicationContext());
				Intent login = new Intent(getApplicationContext(), LoginActivity.class);
				login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(login);
				// Closing dashboard screen
				finish();
			}
		});

		bPrefs = (Button) findViewById(R.id.bSubmitAnswer);
		
		if (isOnline() ) {
			myThing = new ThingServiceImpl("129.173.226.62", "8080", new UsernamePasswordCredentials("", ""), false);
			crazyThing = new Thing();
			crazyThing.setThingId("quiz_board_unique");
			crazyThing.setName("quizBoard");
			updateRadioButtons();
			runLoop();
			
			bPrefs.setOnClickListener(new View.OnClickListener() {

				public void onClick(View arg0) {
					DefaultHttpClient client = new DefaultHttpClient();
					HttpConnectionParams.setConnectionTimeout(client.getParams(),
							10000); // Timeout Limit
					
					String SetServerString = null;
					try {
						// Add your data
						List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
						nameValuePairs.add(new BasicNameValuePair("queID", questionId));
						int radioButtonID = radioChoiceGroup.getCheckedRadioButtonId();
						View radioButton = radioChoiceGroup.findViewById(radioButtonID);
						int idx = radioChoiceGroup.indexOfChild(radioButton);
						String choice = "";
						switch(idx){
							case 0:
								choice = "choice1";
								break;
							case 1:
				        		choice = "choice2";
				        		break;
							case 2:
				        		choice = "choice3";
				        		break;
							case 3:
				        		choice = "choice4";
				        		break;
						}
						
						String URL = "http://129.173.226.62/public/mb_submit.php?user="+"B00632155"+"&queId="+questionId+"&choice="+choice;
						
						
						 HttpGet httpget = new HttpGet(URL);
                         ResponseHandler<String> responseHandler = new BasicResponseHandler();
                         SetServerString = client.execute(httpget, responseHandler);
                         
                         int index = SetServerString.indexOf(":") + 2;
                         
                         SetServerString = SetServerString.substring(index, SetServerString.indexOf("\"", index));
                         
                         showIfCorrectBox(SetServerString);
                                       
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}

				}
			});
			
		} else {
			radio0.setSelected(false);
			radio1.setSelected(false);
			radio2.setSelected(false);
			radio3.setSelected(false);
		}

		

		bPrefs = (Button) findViewById(R.id.bPrefs);

		bPrefs.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//userFunctions.logoutUser(getApplicationContext());
				Intent login = new Intent(getApplicationContext(), Prefs.class);
				startActivity(login);
				// Closing dashboard screen
			}
		});


		/**
		 * Dashboard Screen for the application
		 * */        
		// Check login status in database
		/* userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())){


        }else{
        	// user is not logged in show login screen
        	Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(login);
        	// Closing dashboard screen
        	finish();
        }*/

	}
	
	private void showIfCorrectBox(String ifCorrect){
		AlertDialog.Builder served = new AlertDialog.Builder(this);
        served.setTitle("Results")
                .setMessage("The provided answer was "+ifCorrect + ".")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        served.show();
	}
	
	private void updateRadioButtons(){
		myThing.retrieveThingMetadata(5, new ThingBrokerRequestListener() {

			@Override
			public void executeAfterRequest(int serviceId, Object result, Exception ex) {
				String sRsult = (String) result;
				int index = sRsult.indexOf(":");
				String temp = sRsult.substring(index + 2, sRsult.indexOf("\",", index));
				radio0.setText(temp);

				index = sRsult.indexOf(":", sRsult.indexOf("\",", index));
				temp = sRsult.substring(index + 2, sRsult.indexOf("\",", index));
				radio1.setText(temp);

				index = sRsult.indexOf(":", sRsult.indexOf("\",", index));
				temp = sRsult.substring(index + 2, sRsult.indexOf("\",", index));
				radio2.setText(temp);

				index = sRsult.indexOf(":", sRsult.indexOf("\",", index));
				temp = sRsult.substring(index + 2, sRsult.indexOf("\",", index));
				radio3.setText(temp);

				index = sRsult.indexOf("choiceFour");
				temp = sRsult.substring(index + 13, sRsult.indexOf("\",", index));

				index = sRsult.indexOf("queID");
				temp = sRsult.substring(index + 8);
				temp = temp.substring(0, temp.indexOf("\""));

				questionId = temp;
			}
		}, crazyThing);
	}

	private void runLoop() {
		Thread t = new Thread() {

			public void run() {
				while(true){
					updateRadioButtons();
					try {
						this.sleep(45000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		};

		t.start();
	}

	public boolean isOnline() {
		ConnectivityManager cm =
				(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
	
}