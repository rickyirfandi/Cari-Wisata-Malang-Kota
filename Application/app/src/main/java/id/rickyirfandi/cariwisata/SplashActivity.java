package id.rickyirfandi.cariwisata;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {
    private TextView status;
    private String JSON_Wisata;
    private HttpURLConnection httpConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        status = findViewById(R.id.txt_status);

        new Thread(new Runnable() {
            public void run(){
                status.setText("Mengunduh data yang diperlukan");
                try {
                    DownloadDataWisata("http://serviswaterheatermalang.com/wisata/darurat.php");
                    Thread.sleep(2000);
                    Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    status.setText("Terdapat kesalahan dalam mengunduh");
                }
            }
        }).start();
    }

    private void DownloadDataWisata(String urlStr){
        ConnectivityManager connMgr =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // Check for network connections
        if (networkInfo != null && networkInfo.isConnected()) {
            // Create background thread to connect and get data
            new DownloadTask().execute(urlStr);
        } else {
            status.setText("Error, tidak ada koneksi Internet.");
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressDialog = new ProgressDialog(AsynctaskActivity.this) ;
            //progressDialog.setMessage("Please Wait");
            //progressDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            InputStream in = null;

            try {
                StringBuilder sb = new StringBuilder();
                in = openHttpConnection(url);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));

                String nextLine = "";
                while ((nextLine = reader.readLine()) != null) {
                    sb.append(nextLine);
                    JSON_Wisata = sb.toString();
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Error", "doInBackground: Input stream process" );
            }

            return JSON_Wisata;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Data.JSON_Data = JSON_Wisata;
            status.setText(JSON_Wisata);
            httpConn.disconnect();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        private InputStream openHttpConnection(String urlStr) {
            InputStream in = null;
            int resCode = -1;

            try {
                URL url = new URL(urlStr);

                httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setReadTimeout(10000 /* milliseconds */);
                httpConn.setConnectTimeout(15000 /* milliseconds */);
                httpConn.setRequestMethod("GET");
                httpConn.connect();
                resCode = httpConn.getResponseCode();

                if (resCode == HttpURLConnection.HTTP_OK) {
                    in = httpConn.getInputStream();
                }
                else
                {
                    Log.e ("HTTP", "openHttpConnection: Host not reached" );
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("Error", "openHttpConnection: URL not correct",e);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Error", "openHttpConnection: host not reach",e);
            }
            return in;
        }
    }
}
