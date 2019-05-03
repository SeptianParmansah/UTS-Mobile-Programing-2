<?php
  require_once 'koneksi.php';
  if($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = $_POST['id'];
    $kode_toko = $_POST['kode_toko'];
    $nama_toko = $_POST['nama_toko'];
    $alamat_toko = $_POST['alamat_toko'];
    $pemilik_toko = $_POST['pemilik_toko'];
    $query = "UPDATE db_toko SET kode_toko = '$kode_toko' nama_toko = '$nama_toko',
              alamat_toko = '$alamat_toko', pemilik_toko = '$pemilik_toko' WHERE id='$id'";
    $exeQuery = mysqli_query($konek, $query);
    echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'Data Berhasil Diupdate')) :
    json_encode(array('kode' =>2, 'pesan' => 'Data Gagal Diupdate'));
  } else {
    echo json_encode(array('kode' =>101, 'pesan' => 'Request Tidak Valid'));
  }
?>
