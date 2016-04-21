package pawan.example.com.sendemailproject;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SendMailActivity extends AppCompatActivity implements View.OnClickListener {

    //Internet status flag
    Boolean isConnectionExist = false;

    // Connection detector class

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button send = (Button) this.findViewById(R.id.button1);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i("SendMailActivity", "Send Button Clicked.");

        String fromEmail = "tttttttttttt@gmail.com";
        String fromPassword = "password";
        String toEmails = "fdxgvldf@gmail.com";
        Log.i("SendMailActivity", "To List: ");
        String emailSubject = "pawankumar910@gmail.com";
        String emailBody = "pawankumar910@gmail.com";

        new SendMailTask(SendMailActivity.this).execute(fromEmail,
                fromPassword, toEmails, emailSubject, emailBody);

    }
}