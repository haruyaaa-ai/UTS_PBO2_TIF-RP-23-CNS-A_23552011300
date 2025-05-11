package main;

import model.*;
import tenagaMedis.*;
import database.KoneksiDB;
import java.sql.*;
import java.util.*;

public class KasirApotek {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Connection conn = KoneksiDB.getConnection()) {
            // Input pasien
            System.out.print("Masukkan nama pasien: ");
            String namaPasien = scanner.nextLine();
            System.out.print("Masukkan umur pasien: ");
            int umurPasien = scanner.nextInt();
            scanner.nextLine();

            int pasienId;
            String insertPasien = "INSERT INTO pasien (nama, umur) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertPasien, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, namaPasien);
                ps.setInt(2, umurPasien);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                pasienId = rs.getInt(1);
            }

            // Validasi dokter
            System.out.print("Masukkan nama dokter: ");
            String namaDokter = scanner.nextLine();
            int dokterId = getTenagaMedisId(conn, namaDokter, "dokter");
            if (dokterId == -1) {
                System.out.println("Dokter tidak ditemukan di database.");
                return;
            }
            Dokter dokter = new Dokter(namaDokter);
            dokter.verifikasi();

            // Input daftar obat
            List<Obat> daftarObat = new ArrayList<>();
            while (true) {
                System.out.print("Masukkan nama obat (atau ketik 'selesai'): ");
                String namaObat = scanner.nextLine();
                if (namaObat.equalsIgnoreCase("selesai")) break;

                String query = "SELECT * FROM obat WHERE nama = ?";
                try (PreparedStatement psObat = conn.prepareStatement(query)) {
                    psObat.setString(1, namaObat);
                    ResultSet rsObat = psObat.executeQuery();
                    if (rsObat.next()) {
                        Obat obat = new Obat(
                            rsObat.getInt("id"),
                            rsObat.getString("nama"),
                            rsObat.getDouble("harga"),
                            rsObat.getInt("stok")
                        );
                        daftarObat.add(obat);
                    } else {
                        System.out.println("Obat tidak ditemukan.");
                    }
                }
            }

            // Validasi apoteker
            System.out.print("Masukkan nama apoteker: ");
            String namaApoteker = scanner.nextLine();
            int apotekerId = getTenagaMedisId(conn, namaApoteker, "apoteker");
            if (apotekerId == -1) {
                System.out.println("Apoteker tidak ditemukan di database.");
                return;
            }
            Apoteker apoteker = new Apoteker(namaApoteker);
            apoteker.verifikasi();

            // Cetak struk dan simpan transaksi + resep
            Pasien pasien = new Pasien(pasienId, namaPasien, umurPasien);
            Transaksi transaksi = new Transaksi(pasien, daftarObat, dokterId);
            transaksi.cetakStruk();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getTenagaMedisId(Connection conn, String nama, String tabel) throws SQLException {
        String query = "SELECT id FROM " + tabel + " WHERE nama = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, nama);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }
}