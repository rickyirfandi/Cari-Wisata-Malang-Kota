package id.rickyirfandi.cariwisata;

public class WisataModel {
    String id, nama, alamat, kategori;
    double latitude, longitude, jarak;
    int harga, rating,review;

    public WisataModel(String id, String nama, String alamat, String kategori, double latitude, double longitude, double jarak, int harga, int rating, int review) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.kategori = kategori;
        this.latitude = latitude;
        this.longitude = longitude;
        this.jarak = jarak;
        this.harga = harga;
        this.rating = rating;
        this.review = review;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getJarak() {
        return jarak;
    }

    public int getHarga() {
        return harga;
    }

    public int getRating() {
        return rating;
    }

    public int getReview() {
        return review;
    }
}
