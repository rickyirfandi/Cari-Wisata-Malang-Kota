package id.rickyirfandi.cariwisata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BobotActivity extends AppCompatActivity {
private TextView txt_jarak, txt_harga, txt_rating, txt_review;
private ImageButton btn_rendah_jarak, btn_rendah_harga, btn_rendah_rating,  btn_rendah_review,
                    btn_sedang_jarak, btn_sedang_harga, btn_sedang_rating, btn_sedang_review,
                    btn_tinggi_jarak, btn_tinggi_harga, btn_tinggi_rating, btn_tinggi_review;
final int BOBOT_RENDAH = 2;
final int BOBOT_SEDANG = 5;
final int BOBOT_TINGGI = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bobot);

        txt_jarak = findViewById(R.id.txt_jarak);
        txt_harga = findViewById(R.id.txt_harga);
        txt_rating = findViewById(R.id.txt_rating);
        txt_review = findViewById(R.id.txt_review);

        btn_rendah_jarak = findViewById(R.id.btn_rendah_jarak);
        btn_rendah_harga = findViewById(R.id.btn_rendah_harga);
        btn_rendah_rating = findViewById(R.id.btn_rendah_rating);
        btn_rendah_review = findViewById(R.id.btn_rendah_review);
        btn_sedang_jarak = findViewById(R.id.btn_sedang_jarak);
        btn_sedang_harga = findViewById(R.id.btn_sedang_harga);
        btn_sedang_rating = findViewById(R.id.btn_sedang_rating);
        btn_sedang_review = findViewById(R.id.btn_sedang_review);
        btn_tinggi_jarak = findViewById(R.id.btn_tinggi_jarak);
        btn_tinggi_harga= findViewById(R.id.btn_tinggi_harga);
        btn_tinggi_rating = findViewById(R.id.btn_tinggi_rating);
        btn_tinggi_review = findViewById(R.id.btn_tinggi_review);

        btn_rendah_jarak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.BOBOT_JARAK = BOBOT_RENDAH;
                txt_jarak.setText("Jarak : RENDAH");
            }
        });
        btn_sedang_jarak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.BOBOT_JARAK = BOBOT_SEDANG;
                txt_jarak.setText("Jarak : SEDANG");
            }
        });
        btn_tinggi_jarak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.BOBOT_JARAK = BOBOT_TINGGI;
                txt_jarak.setText("Jarak : TINGGI");
            }
        });
        btn_rendah_harga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.BOBOT_HARGA = BOBOT_RENDAH;
                txt_harga.setText("Harga : RENDAH");
            }
        });
        btn_sedang_harga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.BOBOT_HARGA= BOBOT_SEDANG;
                txt_harga.setText("Harga : SEDANG");
            }
        });
        btn_tinggi_harga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.BOBOT_HARGA = BOBOT_TINGGI;
                txt_harga.setText("Harga : TINGGI");
            }
        });
        btn_rendah_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.BOBOT_RATING = BOBOT_RENDAH;
                txt_rating.setText("Rating : RENDAH");
            }
        });
        btn_sedang_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.BOBOT_RATING = BOBOT_SEDANG;
                txt_rating.setText("Rating : SEDANG");
            }
        });
        btn_tinggi_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.BOBOT_RATING = BOBOT_TINGGI;
                txt_rating.setText("Rating : RATING");
            }
        });
        btn_rendah_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.BOBOT_REVIEW = BOBOT_RENDAH;
                txt_review.setText("Review : RENDAH");
            }
        });
        btn_sedang_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.BOBOT_REVIEW= BOBOT_SEDANG;
                txt_review.setText("Review : SEDANG");
            }
        });
        btn_tinggi_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.BOBOT_REVIEW = BOBOT_TINGGI;
                txt_review.setText("Review : TINGGI");
            }
        });
    }
}
