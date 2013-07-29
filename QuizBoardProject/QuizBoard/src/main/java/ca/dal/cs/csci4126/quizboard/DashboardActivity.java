package ca.dal.cs.csci4126.quizboard;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import br.ufscar.dc.thingbroker.interfaces.ThingBrokerRequestListener;
import br.ufscar.dc.thingbroker.model.Thing;
import br.ufscar.dc.thingbroker.services.impl.ThingServiceImpl;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

		myThing = new ThingServiceImpl("192.168.1.102", "8080", new UsernamePasswordCredentials("", ""), false);

		crazyThing = new Thing();
		crazyThing.setThingId("quiz_board_unique");
		crazyThing.setName("quizBoard");

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

				index = sRsult.indexOf("queID");
				temp = sRsult.substring(index + 8);
				temp = temp.substring(0, temp.indexOf("\""));

				questionId = temp;

			}
		}, crazyThing);


		runLoop();

		db.getUserDetails();


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

		bPrefs.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				DefaultHttpClient client = new DefaultHttpClient();
				HttpConnectionParams.setConnectionTimeout(client.getParams(),
						10000); // Timeout Limit
				HttpResponse response;
				HttpPost post = new HttpPost(
						"http://192.168.1.102/public/mb_submit.php");
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
					nameValuePairs.add(new BasicNameValuePair("answer", choice));
					post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

					// Execute HTTP Post Request
					response = client.execute(post);
					
					HttpGet get = new HttpGet("http://192.168.1.102/public/mb_submit.php");
			        HttpResponse responseGet = client.execute(get);  
			        HttpEntity resEntityGet = responseGet.getEntity();  
			        if (resEntityGet != null) {  
			                    //do something with the response
			                    Log.i("GET RESPONSE",EntityUtils.toString(resEntityGet));
			        }

				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}

			}
		});

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

	public void runLoop() {
		Thread t = new Thread() {

			public void run() {
				while(true){
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
			}
		};

		t.start();
	}

}