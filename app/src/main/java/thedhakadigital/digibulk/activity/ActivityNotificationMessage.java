package thedhakadigital.digibulk.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import thedhakadigital.digibulk.R;

public class ActivityNotificationMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_message);
        String message = getIntent().getStringExtra("message");
//        String message = getIntent().getStringExtra("message");
        TextView tv =(TextView) findViewById(R.id.tvMessage);
        tv.setText("message shown here?\n"+message);
    }
}
