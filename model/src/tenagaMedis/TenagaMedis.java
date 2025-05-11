package tenagaMedis;

public abstract class TenagaMedis {
    protected String nama;

    public TenagaMedis(String nama) {
        this.nama = nama;
    }

    public abstract void verifikasi();
}