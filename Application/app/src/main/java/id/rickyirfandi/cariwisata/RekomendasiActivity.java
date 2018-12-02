package id.rickyirfandi.cariwisata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class RekomendasiActivity extends AppCompatActivity {
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekomendasi);

        status = findViewById(R.id.textView5);

        String msg = "";
        for (int i = 0; i < 3; i++) {
            msg += Data.HasilRekomendasi[i].nama + " \n";
        }

        status.setText(msg);
    }
}
