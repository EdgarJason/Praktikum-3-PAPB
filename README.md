# PRAKTIKUM 3 PAPB

## Penjelasan singkat implementasi state di aplikasi.
Implementasi state di aplikasi ini difokuskan pada fungsi remember dan mutableStateOf (serta variasinya mutableIntStateOf) untuk bind alur data langsung ke elemen UI. Pada level navigasi, state currentScreen menentukan tampilan mana yang harus dirender, sehingga saat nilai state berubah dari "menu" ke nama screen lain, Compose langsung mengganti composable yang aktif tanpa butuh intent / fragment manager. 
Pada fitur counter, state integer trace angka dan diberi validasi kondisi agar nilai tidak bisa turun di jadi below zero saat tombol minus ditekan. 
Pada fitur toggle box, state boolean dipakai untuk menyimpan kondisi warna sehingga tiap event, klik langsung membalik nilai dan mengubah background antara merah / hijau. 
Pada profil, state boolean menyimpan status follow yang memetakan teks tombol, warna background tombol, dan label teks indikator di bawahnya.

## Analisis singkat: mengapa UI Compose lebih sederhana dibandingkan XML tradisional untuk kasus ini.
Jetpack Compose jauh lebih simple dibanding XML tradisional karena paradigma declarative UI, di mana UI didefinisikan murni sebagai representasi visual dari state saat itu juga. Di sistem XML, kita harus mengelola 2 file terpisah untuk layout dan logic, melakukan binding elemen via findViewById atau view binding, lalu menulis instruksi imperatif yang panjang untuk set properti tampilan secara manual setiap kali action terjadi, misalnya memanggil setText(), setBackgroundColor(), atau mengontrol visibility secara terpisah yang rawan bug sinkronisasi. 
Sementara di Compose, kita cukup menuliskan logika conditional langsung di dalam fungsi UI. Ketika state berubah, framework hanya melakukan recomp pada composable yang membaca nilai tersebut tanpa sinkronisasi manual.

