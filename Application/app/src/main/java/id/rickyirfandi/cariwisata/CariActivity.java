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

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;


public class CariActivity extends AppCompatActivity {
    TextView status;
    List<WisataModel> listWisata;

    private LocationRequest mLocationRequest;

    private long UPDATE_INTERVAL = 10 * 10000;
    private long FASTEST_INTERVAL = 20000;
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
        listWisata = new ArrayList<>();
        String msg = "obj : ";

        try{
            JSONArray wisata = new JSONArray(JSON);

            for (int i = 0; i < wisata.length(); i++) {
                JSONObject temp = wisata.getJSONObject(i);
                String id =  temp.getString("id");
                String nama = temp.getString("nama");
                String alamat = temp.getString("alamat");
                String kategori = temp.getString("kategori");
                String foto = temp.getString("foto");
                double latitude = temp.getDouble("latitude");
                double longitude = temp.getDouble("longitude");
                double jarak = Jarak(Data.latitude, Data.longitude,latitude,longitude);
                int harga = temp.getInt("harga");
                int rating = temp.getInt("rating");
                int review = temp.getInt("review");
                
                if(kategori.equalsIgnoreCase("Wisata Taman")){
                    if(Data.WISATA_TAMAN){
                        WisataModel tmp = new WisataModel(id,nama,alamat,kategori,foto,latitude,longitude,jarak,harga,rating,review);
                        listWisata.add(tmp);
                        //bikin obj
                    }
                } else if (kategori.equalsIgnoreCase("Wisata Museum")){
                    if(Data.WISATA_MUSEUM){
                        WisataModel tmp = new WisataModel(id,nama,alamat,kategori,foto,latitude,longitude,jarak,harga,rating,review);
                        listWisata.add(tmp);
                        //bikin obj
                    }
                } else if (kategori.equalsIgnoreCase("Wisata Kolam")){
                    if(Data.WISATA_KOLAM){
                        WisataModel tmp = new WisataModel(id,nama,alamat,kategori,foto,latitude,longitude,jarak,harga,rating,review);
                        listWisata.add(tmp);
                        //bikin obj
                    }
                } else if (kategori.equalsIgnoreCase("Wisata Foto")){
                    if(Data.WISATA_FOTO){
                        WisataModel tmp = new WisataModel(id,nama,alamat,kategori,foto,latitude,longitude,jarak,harga,rating,review);
                        listWisata.add(tmp);
                        //bikin obj
                    }
                } else {

                }
            }

            for(int i = 0; i < listWisata.size(); i++){
                String nama = listWisata.get(i).nama;
                msg += nama + " : ";
            }

            status.setText(msg);

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
