package tenagaMedis;

public class Dokter extends TenagaMedis {
    public Dokter(String nama) {
        super(nama);
    }

    @Override
    public void verifikasi() {
        System.out.println("Dokter " + nama + " telah memverifikasi resep.");
    }
}