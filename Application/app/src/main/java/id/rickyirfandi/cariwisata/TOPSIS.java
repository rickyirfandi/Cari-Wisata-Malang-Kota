package id.rickyirfandi.cariwisata;

import java.util.List;

public class TOPSIS {
    private List<WisataModel> listWisata;
    private double PEMBAGI_JARAK, PEMBAGI_HARGA, PEMBAGI_REVIEW, PEMBAGI_RATING;
    private double[][] MATRIX_NORMALISASI;
    private double[][] MATRIX_TERBOBOT;
    private double[] Aplus = new double[4];
    private double[] Amin = new double[4];
    private double[] SIplus;
    private double[] SImin;
    private double[] JumlahSI;
    private double[] CIPlus;

    public TOPSIS(List<WisataModel> listWisata) {
        this.listWisata = listWisata;
    }

    public List<WisataModel> HitungTOPSIS(){
        HITUNG_PEMBAGI();
        HITUNG_NORMALISASI();
        HITUNG_TERBOBOT();
        HitungAPlusAmin();
        HitungSIPlusSIMin();
    }

    //STEP 1 : mendapatkan pembagi dari masing masing kriteria
    private void HITUNG_PEMBAGI (){
        double jarak = 0;
        int harga = 0, review = 0, rating = 0;
        for(int i = 0; i < listWisata.size(); i++){
            jarak += (listWisata.get(i).getJarak() * listWisata.get(i).getJarak());
            harga += (listWisata.get(i).getHarga() * listWisata.get(i).getHarga());
            review += (listWisata.get(i).getReview() * listWisata.get(i).getReview());
            rating += (listWisata.get(i).getRating() * listWisata.get(i).getRating());
        }
        PEMBAGI_JARAK = Math.sqrt(jarak);
        PEMBAGI_HARGA = Math.sqrt(harga);
        PEMBAGI_REVIEW = Math.sqrt(review);
        PEMBAGI_RATING = Math.sqrt(rating);
    }

    //STEP 2 : Menghitung tabel normalisasi
    private void HITUNG_NORMALISASI(){
        MATRIX_NORMALISASI = new double [listWisata.size()][4];
        for(int i = 0; i < listWisata.size(); i++){
            for (int j = 0; j < 4; j++ ){
                switch(j) {
                    case 0:
                        double jarak = listWisata.get(i).getJarak();
                        jarak = jarak/PEMBAGI_JARAK;
                        MATRIX_NORMALISASI[i][j] = jarak;
                        break;
                    case 1:
                        double harga = listWisata.get(i).getHarga();
                        harga = harga/PEMBAGI_HARGA;
                        MATRIX_NORMALISASI[i][j] = harga;
                        break;
                    case 2:
                        double review = listWisata.get(i).getReview();
                        review = review/PEMBAGI_REVIEW;
                        MATRIX_NORMALISASI[i][j] = review;
                        break;
                    case 3:
                        double rating = listWisata.get(i).getRating();
                        rating = rating/PEMBAGI_RATING;
                        MATRIX_NORMALISASI[i][j] = rating;
                        break;
                    default:
                }
            }
        }
    }

    //STEP 3 : Menghitung tabel terbobot
    private void HITUNG_TERBOBOT(){
        MATRIX_TERBOBOT = new double [listWisata.size()][4];
            int BOBOT_JARAK = Data.BOBOT_JARAK,
                    BOBOT_HARGA = Data.BOBOT_HARGA,
                    BOBOT_RATING = Data.BOBOT_RATING,
                    BOBOT_REVIEW = Data.BOBOT_REVIEW;

        for(int i = 0; i < listWisata.size(); i++){
            for (int j = 0; j < 4; j++ ){
                switch(j) {
                    case 0:
                        double jarak = MATRIX_NORMALISASI[i][j];
                        jarak = jarak * BOBOT_JARAK;
                        MATRIX_TERBOBOT[i][j] = jarak;
                        break;
                    case 1:
                        double harga = MATRIX_NORMALISASI[i][j];
                        harga = harga * BOBOT_JARAK;
                        MATRIX_TERBOBOT[i][j] = harga;
                        break;
                    case 2:
                        double review = MATRIX_NORMALISASI[i][j];
                        review = review * BOBOT_JARAK;
                        MATRIX_TERBOBOT[i][j] = review;
                        break;
                    case 3:
                        double rating = MATRIX_NORMALISASI[i][j];
                        rating = rating * BOBOT_JARAK;
                        MATRIX_TERBOBOT[i][j] = rating;
                        break;
                    default:
                }
            }
        }
    }

    //STEP 4 : Menghitung nilai A+ dan A- dari setiap kriteria
    // A+ Jarak : MIN, A+ Harga : MIN, A+ Review : MAX, A+ Review : MAX
    // A- Jarak : MAX, A+ Harga : MAX, A+ Review : MIN, A+ Review : MIN
    public void HitungAPlusAmin(){
        double Aplus_Jarak = 9999, Aplus_Harga = 9999, Aplus_Review = 0, Aplus_Rating = 0;
        double Amin_Jarak = 0, Amin_Harga = 0, Amin_Review = 9999, Amin_Rating = 9999;
        for(int i = 0; i < listWisata.size(); i++){
            for (int j = 0; j < 4; j++ ){
                switch(j) {
                    case 0:
                        double jarak = MATRIX_TERBOBOT[i][j];
                        if(jarak<Aplus_Jarak){
                            Aplus_Jarak = jarak;
                        }
                        if(jarak>Amin_Jarak){
                            Amin_Jarak = jarak;
                        }
                        break;
                    case 1:
                        double harga = MATRIX_TERBOBOT[i][j];
                        if(harga<Aplus_Harga){
                            Aplus_Harga = harga;
                        }
                        if(harga>Amin_Harga){
                            Amin_Harga = harga;
                        }
                        break;
                    case 2:
                        double review = MATRIX_TERBOBOT[i][j];
                        if(review>Aplus_Review){
                            Aplus_Review = review;
                        }
                        if(review<Amin_Review){
                            Amin_Review = review;
                        }
                        break;
                    case 3:
                        double rating = MATRIX_TERBOBOT[i][j];
                        if(rating>Aplus_Rating){
                            Aplus_Rating = rating;
                        }
                        if(rating<Amin_Rating){
                            Amin_Rating = rating;
                        }
                        break;
                    default:
                }
            }
        }
        Aplus[0]=Aplus_Jarak;
        Aplus[1]=Aplus_Harga;
        Aplus[2]=Aplus_Review;
        Aplus[3]=Aplus_Rating;
        Amin[0]=Amin_Jarak;
        Amin[1]=Amin_Harga;
        Amin[2]=Amin_Review;
        Amin[3]=Amin_Rating;
    }

    //STEP 5 : Menghitung nilai SI+ dan SI- dari setiap kriteria Juga Jumlah SI
    public void HitungSIPlusSIMin(){
        SIplus = new double[listWisata.size()];
        SImin = new double[listWisata.size()];
        JumlahSI = new double[listWisata.size()];

        //SI+
        for(int i = 0; i < listWisata.size(); i++){
            double SIPlus_ = ((Aplus[0] - MATRIX_TERBOBOT[i][0])*(Aplus[0] - MATRIX_TERBOBOT[i][0]));
            SIPlus_ += ((Aplus[1] - MATRIX_TERBOBOT[i][1])*(Aplus[1] - MATRIX_TERBOBOT[i][1]));
            SIPlus_ += ((Aplus[2] - MATRIX_TERBOBOT[i][2])*(Aplus[2] - MATRIX_TERBOBOT[i][2]));
            SIPlus_ += ((Aplus[3] - MATRIX_TERBOBOT[i][3])*(Aplus[3] - MATRIX_TERBOBOT[i][3]));
            SIPlus_ = Math.sqrt(SIPlus_);
            SIplus[i] = SIPlus_;
        }

        //SI-
        for(int i = 0; i < listWisata.size(); i++){
            double SIMin_ = ((MATRIX_TERBOBOT[i][0] - Amin[0])*(MATRIX_TERBOBOT[i][0] - Amin[0]));
            SIMin_ += ((MATRIX_TERBOBOT[i][1] - Amin[1])*(MATRIX_TERBOBOT[i][1] - Amin[1]));
            SIMin_ += ((MATRIX_TERBOBOT[i][2] - Amin[2])*(MATRIX_TERBOBOT[i][2] - Amin[2]));
            SIMin_ += ((MATRIX_TERBOBOT[i][3] - Amin[3])*(MATRIX_TERBOBOT[i][3] - Amin[3]));
            SIMin_  = Math.sqrt(SIMin_ );
            SImin [i] = SIMin_ ;
        }

        //Jumlah SI
        for(int i = 0; i < listWisata.size(); i++){
            double jumlah = (SIplus[i]+SImin[i]);
            JumlahSI [i] = jumlah;
        }
    }

    //Step 6 Menghitung nilai CI+
    private void HitungCIPlus(){
        for(int i = 0; i < listWisata.size(); i++){
            double CIplus_ = (SImin[i]/JumlahSI[i]);
            CIPlus[i] = CIplus_;
        }
    }
}
