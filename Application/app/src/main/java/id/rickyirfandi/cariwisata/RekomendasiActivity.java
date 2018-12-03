package id.rickyirfandi.cariwisata;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RekomendasiActivity extends AppCompatActivity {
    TextView rekomendasi1, rekomendasi2, rekomendasi3,alamat1, alamat2, alamat3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekomendasi);

        rekomendasi1 = findViewById(R.id.rekomendasi1);
        rekomendasi2 = findViewById(R.id.rekomendasi2);
        rekomendasi3 = findViewById(R.id.rekomendasi3);

        alamat1 = findViewById(R.id. alamat1);
        alamat2 = findViewById(R.id. alamat2);
        alamat3 = findViewById(R.id. alamat3);

        rekomendasi1.setText(Data.HasilRekomendasi[0].getNama());
        rekomendasi2.setText(Data.HasilRekomendasi[1].getNama());
        rekomendasi3.setText(Data.HasilRekomendasi[2].getNama());

        alamat1.setText(Data.HasilRekomendasi[0].getAlamat());
        alamat2.setText(Data.HasilRekomendasi[1].getAlamat());
        alamat3.setText(Data.HasilRekomendasi[2].getAlamat());

        rekomendasi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?saddr="+Data.latitude+","+Data.longitude+"&daddr="+Data.HasilRekomendasi[0].getLatitude()+","+Data.HasilRekomendasi[0].getLongitude();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });

        rekomendasi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?saddr="+Data.latitude+","+Data.longitude+"&daddr="+Data.HasilRekomendasi[1].getLatitude()+","+Data.HasilRekomendasi[1].getLongitude();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });

        rekomendasi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?saddr="+Data.latitude+","+Data.longitude+"&daddr="+Data.HasilRekomendasi[2].getLatitude()+","+Data.HasilRekomendasi[2].getLongitude();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
    }
}
