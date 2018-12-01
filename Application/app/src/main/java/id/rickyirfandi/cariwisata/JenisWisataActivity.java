package id.rickyirfandi.cariwisata;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class JenisWisataActivity extends AppCompatActivity {
RadioButton rd_taman, rd_museum, rd_kolam, rd_foto, rd_semua;
ImageButton btn_cari_wisata;
boolean WISATA_TAMAN = false,  WISATA_MUSEUM = false,  WISATA_KOLAM = false, WISATA_FOTO = false;

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
                if(!WISATA_TAMAN && !WISATA_MUSEUM && !WISATA_KOLAM && !WISATA_FOTO){
                    AlertDialog alertDialog = new AlertDialog.Builder(JenisWisataActivity.this).create();
                    alertDialog.setTitle("Maaf");
                    alertDialog.setMessage("Jenis Wisata Belum dipilih");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                } else {
                    Intent intent = new Intent(JenisWisataActivity.this,CariActivity.class);
                    Data.WISATA_TAMAN = WISATA_TAMAN;
                    Data.WISATA_MUSEUM = WISATA_MUSEUM;
                    Data.WISATA_KOLAM = WISATA_KOLAM;
                    Data.WISATA_FOTO = WISATA_FOTO;
                    startActivity(intent);
                }
            }
        });

        rd_taman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WISATA_TAMAN){
                    rd_taman.setChecked(false);
                    WISATA_TAMAN = false;
                } else {
                    rd_taman.setChecked(true);
                    WISATA_TAMAN = true;
                }
                rd_semua.setChecked(false);
            }
        });

        rd_museum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WISATA_MUSEUM){
                    rd_museum.setChecked(false);
                    WISATA_MUSEUM = false;
                } else {
                    rd_museum.setChecked(true);
                    WISATA_MUSEUM = true;
                }
                rd_semua.setChecked(false);
            }
        });

        rd_kolam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WISATA_KOLAM){
                    rd_kolam.setChecked(false);
                    WISATA_KOLAM = false;
                } else {
                    rd_kolam.setChecked(true);
                    WISATA_KOLAM = true;
                }
                rd_semua.setChecked(false);
            }
        });

        rd_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WISATA_FOTO){
                    rd_foto.setChecked(false);
                    WISATA_FOTO = false;
                } else {
                    rd_foto.setChecked(true);
                    WISATA_FOTO = true;
                }
                rd_semua.setChecked(false);
            }
        });

        rd_semua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd_taman.setChecked(true);
                rd_museum.setChecked(true);
                rd_kolam.setChecked(true);
                rd_foto.setChecked(true);
                rd_semua.setChecked(true);
                WISATA_TAMAN = true;
                WISATA_MUSEUM = true;
                WISATA_KOLAM = true;
                WISATA_FOTO = true;
            }
        });
    }
}
