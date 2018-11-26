package id.rickyirfandi.cariwisata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class JenisWisataActivity extends AppCompatActivity {
RadioButton rd_taman, rd_museum, rd_kolam, rd_foto, rd_semua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_wisata);

        rd_taman = findViewById(R.id.rd_taman);
        rd_museum = findViewById(R.id.rd_museum);
        rd_kolam = findViewById(R.id.rd_kolam);
        rd_foto = findViewById(R.id.rd_foto);
        rd_semua = findViewById(R.id.rd_semua);

        rd_taman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChecked();
                rd_taman.setChecked(true);
            }
        });

        rd_museum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChecked();
                rd_museum.setChecked(true);
            }
        });

        rd_kolam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChecked();
                rd_kolam.setChecked(true);
            }
        });

        rd_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChecked();
                rd_foto.setChecked(true);
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
