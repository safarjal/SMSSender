package com.safarjal.smssender;


import com.safarjal.smssender.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity {
	EditText receivedSmsNumberEditText;
	EditText receivedSmsTextEditText;
	EditText sentSmsNumberEditText;
	EditText sentSmsTextEditText;
	SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receivedSmsNumberEditText = (EditText) findViewById(R.id.receivedSmsNumber);
        receivedSmsTextEditText = (EditText) findViewById(R.id.receivedSmsText);
        sentSmsNumberEditText = (EditText) findViewById(R.id.sentSmsNumber);
        sentSmsTextEditText = (EditText) findViewById(R.id.sentSmsText);

		settings = PreferenceManager.getDefaultSharedPreferences(this);

		putValueInEditText();

    }

	public void onClickSaveButton(View view){
		if(receivedSmsNumberEditText.getText().toString().equals("")||
				receivedSmsTextEditText.getText().toString().equals("")||
				sentSmsNumberEditText.getText().toString().equals("")||
				sentSmsTextEditText.getText().toString().equals("")				
				){
			toast("Please do not leave the value empty");
		}else{
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("sentSmsNumber", sentSmsNumberEditText.getText().toString());
			editor.putString("sentSmsText", sentSmsTextEditText.getText().toString());
			editor.putString("receivedSmsNumber", receivedSmsNumberEditText.getText().toString());
			editor.putString("receivedSmsText", receivedSmsTextEditText.getText().toString());
			editor.commit();
			toast("Save successful");
		}
	}
	public void toast(String toasttext){
		Context context;
		context = getApplicationContext();
		CharSequence text = toasttext;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	


	private void putValueInEditText() {
		String sentSmsText;
		String sentSmsNumber;
		String receivedSmsText;
		String receivedSmsNumber;
		sentSmsText = settings.getString("sentSmsText", "abcde");
		sentSmsNumber = settings.getString("sentSmsNumber", "12345");
		receivedSmsText = settings.getString("receivedSmsText", "fghij");
		receivedSmsNumber = settings.getString("receivedSmsNumber", "6789");
		sentSmsTextEditText.setText(sentSmsText);
		sentSmsNumberEditText.setText(sentSmsNumber);
		receivedSmsTextEditText.setText(receivedSmsText);
		receivedSmsNumberEditText.setText(receivedSmsNumber);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
