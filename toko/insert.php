<?php
  require_once 'koneksi.php';
  if($_SERVER['REQUEST_METHOD'] == 'POST') {
    $kode_toko = $_POST['kode_toko'];
    $nama_toko = $_POST['nama_toko'];
    $alamat_toko = $_POST['$alamat_toko'];
    $pemilik_toko =$_POST['$pemilik_toko'];

    $query = "INSERT INTO db_toko (kode_toko, nama_toko, alamat_toko, pemilik_toko) VALUES
    ('$kode_toko','$nama_toko','$alamat_toko','$pemilik_toko')";
    $exeQuery = mysqli_query($konek, $query);
    echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'Data Berhasil Ditambahkan'))
    : json_encode(array('kode' =>2, 'pesan' => 'Data Gagal Ditambahkan'));
  } else {
    echo json_encode(array('kode' =>101, 'pesan' => 'Request Tidak Valid'));
  }
?>
