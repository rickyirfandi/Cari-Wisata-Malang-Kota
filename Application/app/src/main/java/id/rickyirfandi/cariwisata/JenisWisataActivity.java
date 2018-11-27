package id.rickyirfandi.cariwisata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class JenisWisataActivity extends AppCompatActivity {
RadioButton rd_taman, rd_museum, rd_kolam, rd_foto, rd_semua;
ImageButton btn_cari_wisata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_wisata);

        rd_taman = findViewById(R.id.rd_taman);
        rd_museum = findViewById(R.id.rd_museum);
        rd_kolam = findViewById(R.id.rd_kolam);
        rd_foto = findViewById(R.id.rd_foto);
        rd_semua = findViewById(R.id.rd_semua);

        btn_cari_wisata = findViewById(R.id.btn_cari_wisata);

        btn_cari_wisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JenisWisataActivity.this,CariActivity.class);
                startActivity(intent);
            }
        });

        rd_taman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd_taman.setChecked(true);
                rd_semua.setChecked(false);
            }
        });

        rd_museum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd_museum.setChecked(true);
                rd_semua.setChecked(false);
            }
        });

        rd_kolam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd_kolam.setChecked(true);
                rd_semua.setChecked(false);
            }
        });

        rd_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd_foto.setChecked(true);
                rd_semua.setChecked(false);
            }
        });

        rd_semua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChecked();
                rd_semua.setChecked(true);
            }
        });
    }

    private void resetChecked(){
        rd_taman.setChecked(false);
        rd_museum.setChecked(false);
        rd_kolam.setChecked(false);
        rd_foto.setChecked(false);
        rd_semua.setChecked(false);
    }
}
