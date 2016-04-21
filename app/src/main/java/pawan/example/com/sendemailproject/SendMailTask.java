package pawan.example.com.sendemailproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class SendMailTask extends AsyncTask {

    private ProgressDialog statusDialog;
    private Activity sendMailActivity;

    public SendMailTask(Activity activity) {
        sendMailActivity = activity;

    }

    protected void onPreExecute() {
        statusDialog = new ProgressDialog(sendMailActivity);
        statusDialog.setMessage("Preparing to send mail....");
        statusDialog.setIndeterminate(false);
        statusDialog.setCancelable(false);
        statusDialog.show();
    }

    @Override
    protected Object doInBackground(Object... args) {

        if (testConnection()) {
        try {
            Log.i("SendMailTask", "About to instantiate GMail...");
            GMail androidEmail = new GMail(args[0].toString(),
                    args[1].toString(), args[2].toString(), args[3].toString(),
                    args[4].toString());
            androidEmail.createEmailMessage();
            androidEmail.sendEmail();
            publishProgress("Email Sent.");
            Log.i("SendMailTask", "Mail Sent.");
        } catch (AddressException e) {
            publishProgress(e.getMessage());
            Log.e("SendMailTask", e.getMessage(), e);
        } catch (AuthenticationFailedException e) {
            //publishProgress(e.getMessage());
            publishProgress("Incorrect Password....");
        } catch (MessagingException e) {
            publishProgress(e.getMessage());
            Log.e("SendMailTask", e.getMessage(), e);
        } catch (Exception e) {
            publishProgress(e.getMessage());
            Log.e("SendMailTask", e.getMessage(), e);
        }
    }else {
            publishProgress("Please check your internet connection....");
        }
        return null;
    }

    @Override
    public void onProgressUpdate(Object... values) {
        Toast.makeText(sendMailActivity, values[0].toString(), Toast.LENGTH_LONG).show();
        /*if(values[0].toString().equals("Incorrect Password....")) {
            Toast.makeText(sendMailActivity, "Incorrect Password....", Toast.LENGTH_LONG).show();
        }else {
            statusDialog.setMessage(values[0].toString());
        }*/
    }

    @Override
    public void onPostExecute(Object result) {
        statusDialog.dismiss();
    }

    public boolean testConnection() {
        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

}