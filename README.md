# Nama : Adi Satria Permana
# NIM  : 1247050074 

# LINK PDF Tampilan: https://drive.google.com/drive/folders/1bLQZ5pO_f20L3JAJZNYDXYWDkx3_GaIT?usp=drive_link
# LINK GITHUB : https://github.com/AdiSatriaPermana/UTSPAM
# ☕ Kopi dan Nyemil - Mobile Application (UTS Pengembangan Aplikasi Mobile)
Aplikasi pemesanan makanan dan minuman berbasis mobile berbasis Android Native (Kotlin) dengan antarmuka modern berbatu gaya **Neo-Brutalism**. Aplikasi ini dirancang intuitif untuk memilih menu kopi/camilan, mengatur jumlah kuantitas, hingga mencetak bukti pembayaran berupa struk digital.

---

## 📱 Fitur Utama

- **Splash / Welcome Screen:** Halaman penyambung awal dengan maskot retro dan slogan khas.
- **Autentikasi (Sign Up / Login / Register):** Alur navigasi antar-halaman autentikasi pengguna.
- **Katalog Menu & Filter (Home):** Menampilkan 6 daftar menu (Kopi, Non-Kopi, dan Cemilan) dilengkapi filter kategori (*Semua*, *Minuman*, *Makanan*).
- **Multi-Item Selector & Real-Time Cart Calculation:** Tombol pengatur kuantitas (`+` / `-`) pada setiap item dengan kalkulasi otomatis total item dan total bayar pada *Bottom Pay Bar*.
- **Manajemen Profil:** Pengubahan nama profil pengguna secara dinamis.
- **Form Alamat Pengiriman:** Input nama penerima, alamat lengkap, dan detail patokan pengiriman.
- **Struk Digital (Konfirmasi Pesanan):** Layar penutup transaksi yang dikemas menarik berupa tampilan Struk Belanja Kasir resmi.

---

## 🛠️ Teknologi yang Digunakan

- **Language:** Kotlin
- **IDE:** Android Studio
- **UI Architecture:** XML Layouts (ConstraintLayout & RelativeLayout)
- **Design Style:** Neo-Brutalism (High-contrast borders & bold colors)
- **Component:** RecyclerView, ViewBinding / findViewById, Intent Navigation

---

## 🚀 Cara Menjalankan Aplikasi

### Prasyarat
- Android Studio Ladybug / Jellyfish (atau versi terbaru)
- JDK 17 / Kotlin Plugin
- Emulator Android (API Level 24+) atau Perangkat HP Android fisik

### Langkah-Langkah

1. **Clone repository ini:**
   ```bash
   git clone [https://github.com/AdiSatriaPermana/UTSPAM.git](https://github.com/AdiSatriaPermana/UTSPAM.git)

## 📱 Panduan Alur Penggunaan Aplikasi

1. **Layar Awal (Splash / Welcome):**
   - Saat aplikasi pertama kali dibuka, pengguna disambut dengan layar Splash bertema Neo-Brutalism.
   - Klik tombol **GET STARTED** untuk masuk ke alur autentikasi.

2. **Layar Pilihan Autentikasi & Login / Register:**
   - Pilih tombol **LOGIN** atau **REGISTER**.
   - Pada halaman **Register**, masukkan Nama Lengkap, Username, dan Password, lalu klik **REGISTER NOW** (nama pengguna akan tersimpan secara otomatis).
   - Pada halaman **Login**, masukkan Username dan Password lalu klik **LOGIN** untuk masuk ke halaman utama.

3. **Layar Utama (Home & Katalog Menu):**
   - Pengguna disapa dengan salam pembuka sesuai nama pengguna yang terdaftar (contoh: *Halo, Adi Satria P!*).
   - Gunakan tombol filter di bagian atas (**SEMUA**, **MINUMAN**, **MAKANAN**) untuk menyaring daftar menu.
   - Klik tombol **`+`** atau **`-`** pada tiap kartu menu untuk menambah atau mengurangi jumlah pesanan (kuantitas).
   - Total item dan total harga bayar di *bottom bar* akan bertambah/berkurang secara *real-time*.

4. **Navigasi Profil (Optional):**
   - Klik tombol **PROFILE** di pojok kanan atas untuk melihat dan memperbarui nama pengguna.

5. **Layar Checkout & Alamat Pengiriman:**
   - Setelah memilih menu, klik tombol **BAYAR ➔** di bagian bawah.
   - Pengguna akan diarahkan ke halaman **Alamat Pengiriman**.
   - Rincian pesanan dan total harga akan ditampilkan di ringkasan atas.
   - Isi Nama Penerima, Alamat Lengkap, serta Detail Patokan Pengiriman, lalu klik **ORDER DAN KIRIM ➔**.

6. **Layar Konfirmasi & Struk Digital:**
   - Aplikasi akan mencetak **Bukti Pembayaran Resmi (Struk Digital)** lengkap dengan rincian item, nama pelanggan, dan status pesanan (*LUNAS & DIPROSES*).
   - Klik tombol **OKE, KEMBALI KE HOME** untuk menyelesaikan transaksi dan kembali ke halaman utama.

## 📸 Tampilan Aplikasi

| Splash / Welcome | Sign Up | Login | Register |
| :---: | :---: | :---: | :---: |
| <img src="Screenshoot/home.jpeg" width="180"> | <img src="Screenshoot/sign up.jpeg" width="180"> | <img src="Screenshoot/login.jpeg" width="180"> | <img src="Screenshoot/register.jpeg" width="180"> |

| Home (Semua) | Filter Minuman | Filter Makanan | Profile |
| :---: | :---: | :---: | :---: |
| <img src="Screenshoot/semua.jpeg" width="180"> | <img src="Screenshoot/minuman.jpeg" width="180"> | <img src="Screenshoot/makanan.jpeg" width="180"> | <img src="Screenshoot/profil.jpeg" width="180"> |

|                  Home (Bayar)                  | Alamat Pengiriman | Struk Konfirmasi |
|:----------------------------------------------:| :---: | :---: |
| <img src="Screenshoot/bayar.jpeg" width="180"> | <img src="Screenshoot/alamat.jpeg" width="180"> | <img src="Screenshoot/konfirmasi.jpeg" width="180"> |