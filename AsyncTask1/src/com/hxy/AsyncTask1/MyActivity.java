package com.hxy.AsyncTask1;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyActivity extends Activity {
    Button download;
    ProgressBar pb;
    TextView tv;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        pb=(ProgressBar)findViewById(R.id.pb);
        tv=(TextView)findViewById(R.id.tv);

        download = (Button)findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadTask downloadTask=new DownloadTask();
                downloadTask.execute(100);

            }
        });
    }
    class DownloadTask extends AsyncTask<Integer,Integer,String>{

        @Override
        protected String doInBackground(Integer... integers) {
            for (int i=0;i<=100;i++){
                pb.setProgress(i);
                publishProgress(i);
                try {
                    Thread.sleep(integers[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return "AsyncTask was done";
        }

        @Override
        protected void onPostExecute(String s) {
            setTitle(s);
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            tv.setText(values[0]+"%");
            super.onProgressUpdate(values);
        }
    }
}
