<?php
require_once 'koneksi.php';
if($_SERVER['REQUEST_METHOD'] == 'POST'){
  $id = $_POST['id'];
  $query = "DELETE FROM db_toko WHERE id ='$id'";
  $exeQuery = mysqli_query($konek, $query);
  echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'Data Berhasil Dihapus')) :
  json_encode(array('kode' =>2, 'pesan' => 'Data Gagal Dihapus'));
} else
{
  echo json_encode(array('kode' =>101, 'pesan' => 'Request Tidak Valid'));
}
?>
