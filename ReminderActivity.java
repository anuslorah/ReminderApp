package com.murach.reminder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ReminderActivity extends Activity implements OnClickListener {

    private Button startServiceButton;
    private Button stopServiceButton;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder);
		
        startServiceButton = (Button) findViewById(R.id.startServiceButton);
        stopServiceButton = (Button) findViewById(R.id.stopServiceButton);
        
        startServiceButton.setOnClickListener(this);
        stopServiceButton.setOnClickListener(this);		
	}

    @Override
    public void onClick(View v) {
        Intent serviceIntent = new Intent(this, ReminderService.class);

        switch (v.getId()) {
        	case R.id.startServiceButton:
        		// put code to start service and display toast here
                startService(serviceIntent);
                Context contextStart = getApplicationContext();
                CharSequence textStart = "Service Started";
                int durationStart = Toast.LENGTH_SHORT;
                Toast toastStart = Toast.makeText(contextStart, textStart, durationStart);
                toastStart.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toastStart.show();
        		break;
        	case R.id.stopServiceButton:
        		// put code to stop service and display toast here
                stopService(serviceIntent);
                Context contextStop = getApplicationContext();
                CharSequence textStop = "Service Stopped";
                int durationStop = Toast.LENGTH_SHORT;
                Toast toastStop = Toast.makeText(contextStop, textStop, durationStop);
                toastStop.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toastStop.show();
        		break;
        }
    }
}