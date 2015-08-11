package com.hxy.AsyncTask2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Entity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MyActivity extends Activity {
    private Button button;
    private ImageView imageView;
    private ProgressDialog progressDialog;
    private final String IMAGE_PATH = "http://www.baidu.com/img/bdlogo.png";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.imageView);
        //    弹出要给ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提示信息");
        progressDialog.setMessage("正在下载中，请稍后......");
        //    设置setCancelable(false); 表示我们不能取消这个弹出框，等下载完成之后再让弹出框消失
        progressDialog.setCancelable(false);
        //    设置ProgressDialog样式为圆圈的形式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new MyAsyncTask().execute(IMAGE_PATH);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    public class MyAsyncTask extends AsyncTask<String, Integer, byte[]>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            //    在onPreExecute()中我们让ProgressDialog显示出来
            progressDialog.show();
        }
        @Override
        protected byte[] doInBackground(String... params)
        {
            //    通过Apache的HttpClient来访问请求网络中的一张图片
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = null;

                httpGet = new HttpGet(params[0]);

            byte[] image = new byte[]{};
            try
            {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                if(httpEntity != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                {
                    image = EntityUtils.toByteArray(httpEntity);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                httpClient.getConnectionManager().shutdown();
            }
            return image;
        }
        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(byte[] result)
        {
            super.onPostExecute(result);
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inJustDecodeBounds=true;

            //    将doInBackground方法返回的byte[]解码成要给Bitmap 不要用这个 很容易oom
            Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length,options);
            //    更新我们的ImageView控件
            imageView.setImageBitmap(bitmap);
            //    使ProgressDialog框消失
            progressDialog.dismiss();
        }
    }

}
