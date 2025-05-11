# UTS_PBO2_TIF-RP-23-CNS-A_23552011300
# UTS Pemrograman Berorientasi Obyek 2
- Mata Kuliah: Pemrograman Berorientasi Obyek 2
- Dosen Pengampu: Muhammad Ikhwan Fathulloh

# Profil
- Nama: Damar Satritama Putra
- NIM: 23552011300
- Studi Kasus: Kasir Apotek

# Judul Studi Kasus
Sistem Kasir Apotek Berbasis Java dan MySQL

# Penjelasan Studi Kasus
Studi kasus ini membahas implementasi aplikasi kasir untuk apotek yang melayani pembelian obat baik dengan resep dokter maupun pembelian langsung. Sistem memungkinkan pengguna (kasir) untuk memasukkan data pasien, memverifikasi dokter dan apoteker yang terlibat, mencatat obat-obatan yang dibeli, dan menyimpan transaksi ke database. Sistem ini dibangun menggunakan Java dengan paradigma Object-Oriented Programming (OOP) serta menggunakan database MySQL untuk menyimpan data.

# Penjelasan 4 Pilar OOP dalam Studi Kasus
# 1. Inheritance
   Dalam sistem ini, konsep inheritance diterapkan melalui class abstrak TenagaMedis yang diturunkan menjadi class Dokter dan class Apoteker. Kedua class tersebut mewarisi atribut nama serta method verifikasi()
   dari TenagaMedis, namun mengimplementasikan perilakunya masing-masing sesuai peran di dunia nyata.

# 2. Encapsulation
Encapsulation digunakan pada class seperti Obat dan Pasien, di mana atribut-atribut seperti nama, harga, umur dan stok bersifat private. Akses terhadap atribut dilakukan melalui method getter (misalnya getNama(), getHarga()). Dengan demikian, data-data penting tidak bisa diubah secara langsung dari luar class, sehingga menjaga keamanan dan integritas data.

# 3. Polymorphism
Polymorphism terlihat dari method verifikasi() yang ada di class TenagaMedis. Meskipun method ini dideklarasikan di superclass, masing-masing subclass (Dokter dan Apoteker) memiliki implementasi berbeda. Saat method verifikasi() dipanggil dari objek bertipe TenagaMedis, hasilnya bergantung pada jenis objek aslinya (apakah dokter atau apoteker).

# 4. Abstract
Abstraction diterapkan dengan menggunakan class TenagaMedis sebagai abstract class. Class ini mendefinisikan struktur umum untuk entitas tenaga medis tanpa memberikan implementasi langsung untuk semua method. Hanya class turunan seperti Dokter dan Apoteker yang menyediakan implementasi nyata dari method verifikasi(). Ini menyederhanakan logika program dan membantu fokus pada fitur penting tanpa memikirkan detail teknisnya

# Demo Proyek
- Github: [Github](URL)
- Youtube: Youtube
