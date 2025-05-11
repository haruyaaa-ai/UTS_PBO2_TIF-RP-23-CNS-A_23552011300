package tenagaMedis;

public class Apoteker extends TenagaMedis {
    public Apoteker(String nama) {
        super(nama);
    }

    @Override
    public void verifikasi() {
        System.out.println("Apoteker " + nama + " telah menyiapkan obat.");
    }
}