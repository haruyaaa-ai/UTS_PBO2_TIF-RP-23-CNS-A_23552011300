CREATE DATABASE IF NOT EXISTS apotek;
USE apotek;

CREATE TABLE IF NOT EXISTS pasien (
id INT AUTO_INCREMENT PRIMARY KEY,
nama VARCHAR(100) NOT NULL,
umur INT NOT NULL
);

CREATE TABLE IF NOT EXISTS dokter (
id INT AUTO_INCREMENT PRIMARY KEY,
nama VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS apoteker (
id INT AUTO_INCREMENT PRIMARY KEY,
nama VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS obat (
id INT AUTO_INCREMENT PRIMARY KEY,
nama VARCHAR(100) NOT NULL,
harga DECIMAL(10,2) NOT NULL,
stok INT NOT NULL
);

CREATE TABLE IF NOT EXISTS resep (
id INT AUTO_INCREMENT PRIMARY KEY,
pasien_id INT,
dokter_id INT,
tanggal DATE,
FOREIGN KEY (pasien_id) REFERENCES pasien(id),
FOREIGN KEY (dokter_id) REFERENCES dokter(id)
);

CREATE TABLE IF NOT EXISTS detail_resep (
resep_id INT,
obat_id INT,
jumlah INT,
FOREIGN KEY (resep_id) REFERENCES resep(id),
FOREIGN KEY (obat_id) REFERENCES obat(id)
);

CREATE TABLE IF NOT EXISTS transaksi (
id INT AUTO_INCREMENT PRIMARY KEY,
pasien_id INT,
total DECIMAL(10,2),
tanggal DATE,
FOREIGN KEY (pasien_id) REFERENCES pasien(id)
);

INSERT INTO dokter (nama) VALUES ('dr. Andi'), ('dr. Budi');

INSERT INTO apoteker (nama) VALUES ('Siti'), ('Rina');

INSERT INTO obat (nama, harga, stok) VALUES
('Paracetamol', 5000.00, 100),
('Amoxicillin', 10000.00, 50),
('Antasida', 7000.00, 75),
('Vitamin C', 6000.00, 200),
('Ibuprofen', 8000.00, 80);
