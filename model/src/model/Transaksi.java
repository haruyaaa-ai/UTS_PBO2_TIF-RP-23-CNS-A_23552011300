package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import database.KoneksiDB;

public class Transaksi {
    private Pasien pasien;
    private List<Obat> daftarObat;
    private int dokterId;

    public Transaksi(Pasien pasien, List<Obat> daftarObat, int dokterId) {
        this.pasien = pasien;
        this.daftarObat = daftarObat;
        this.dokterId = dokterId;
    }

    public void cetakStruk() {
        double total = 0;
        System.out.println("\n===== Struk Pembelian =====");
        System.out.println("Nama Pasien: " + pasien.getNama());
        for (Obat obat : daftarObat) {
            System.out.println("Obat: " + obat.getNama() + " - Harga: Rp" + obat.getHarga());
            total += obat.getHarga();
        }
        System.out.println("Total: Rp" + total);

        try (Connection conn = KoneksiDB.getConnection()) {
            // Simpan transaksi
            String transaksiQuery = "INSERT INTO transaksi (pasien_id, total, tanggal) VALUES (?, ?, NOW())";
            try (PreparedStatement psTransaksi = conn.prepareStatement(transaksiQuery)) {
                psTransaksi.setInt(1, pasien.getId());
                psTransaksi.setDouble(2, total);
                psTransaksi.executeUpdate();
            }

            // Simpan resep
            int resepId = -1;
            String resepQuery = "INSERT INTO resep (pasien_id, dokter_id, tanggal) VALUES (?, ?, NOW())";
            try (PreparedStatement psResep = conn.prepareStatement(resepQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                psResep.setInt(1, pasien.getId());
                psResep.setInt(2, dokterId);
                psResep.executeUpdate();
                var rs = psResep.getGeneratedKeys();
                if (rs.next()) resepId = rs.getInt(1);
            }

            // Simpan detail resep
            String detailQuery = "INSERT INTO detail_resep (resep_id, obat_id, jumlah) VALUES (?, ?, ?)";
            for (Obat obat : daftarObat) {
                try (PreparedStatement psDetail = conn.prepareStatement(detailQuery)) {
                    psDetail.setInt(1, resepId);
                    psDetail.setInt(2, obat.getId());
                    psDetail.setInt(3, 1); // jumlah default 1
                    psDetail.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}