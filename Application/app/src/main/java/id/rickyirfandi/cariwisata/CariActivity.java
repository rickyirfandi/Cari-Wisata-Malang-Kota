package id.rickyirfandi.cariwisata;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;


public class CariActivity extends AppCompatActivity {
    TextView status;

    private LocationRequest mLocationRequest;

    private long UPDATE_INTERVAL = 10 * 10000;  /* 10 secs */
    private long FASTEST_INTERVAL = 20000; /* 2 sec */
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);

        status = findViewById(R.id.textView4);
        startLocationUpdates();
        BuatListWisata();

    }

    private void BuatListWisata(){
        String JSON = Data.JSON_Data;
        try{
            JSONArray wisata = new JSONArray(JSON);

            String test = "";
            for (int i = 0; i < wisata.length(); i++) {
                JSONObject c = wisata.getJSONObject(i);
                String id = c.getString("id");
                String nama = c.getString("nama");
                String alamat = c.getString("alamat");
                test += "\n id/nama/alamat\n" + id + nama + alamat;
            }

            status.setText(test);

        } catch(Exception e){
            e.printStackTrace();
        }

    }


    protected void startLocationUpdates() {
        // Create the location request to start receiving updates
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            // do work here
                            Location location = locationResult.getLastLocation();
                            Data.latitude = location.getLatitude();
                            Data.longitude = location.getLongitude();
                        }
                    },
                    Looper.myLooper());
        }
        }



    //Haversine Formula
    public double Jarak(double lat1, double lon1, double lat2, double lon2){
        int RADIUS_BUMI = 6371;
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double deltaLonRad = Math.toRadians(lon2 - lon1);
        double hasil = Math.acos(Math.sin(lat1Rad) * Math.sin(lat2Rad) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.cos(deltaLonRad)) * RADIUS_BUMI;
        return (hasil*1000); // in meters
    }
}
