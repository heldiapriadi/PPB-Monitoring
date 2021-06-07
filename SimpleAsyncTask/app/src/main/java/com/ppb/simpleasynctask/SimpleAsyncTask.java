package com.ppb.simpleasynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String>{
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar pb) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pb);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(101);

        int s = n * 200;
        Log.d("test", String.valueOf(n));
        int i = 1;
        while (i <= n) {
            try {
                publishProgress(i*100/n);
                Thread.sleep(200);

                Log.d("test", String.valueOf(i));
                i++;
            }
            catch (Exception e) {
                Log.i("makemachine", e.getMessage());
            }
        }
//        try {
//            Thread.sleep(s);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        publishProgress(n);
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        mTextView.get().setText("Napping..." + String.valueOf(values[0]) + "%");
        mProgressBar.get().setProgress(values[0]);
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
