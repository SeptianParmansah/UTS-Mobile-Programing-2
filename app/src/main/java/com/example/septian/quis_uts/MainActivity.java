package com.example.septian.quis_uts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;import

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.septian.quis_uts.api.RestApi;
import com.example.septian.quis_uts.api.RetroServer;
import com.example.septian.quis_uts.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText kode_toko, nama_toko, alamat_toko, pemilik_toko;
    Button btnsave, btnTampildata, btnupdate,btndelete;
    ProgressBar pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd= (ProgressBar)findViewById(R.id.pd);
        pd.setIndeterminate(true);
        pd.setVisibility(View.GONE);
        kode_toko = (EditText) findViewById(R.id.Kd_toko);
        nama_toko = (EditText) findViewById(R.id.Nm_toko)
        alamat_toko = (EditText) findViewById(R.id.Almt_toko);
        pemilik_toko = (EditText) findViewById(R.id.Pmlk_toko);

        btnTampildata = (Button) findViewById(R.id.btntampildata);
        btnupdate =(Button) findViewById(R.id.btnUpdate);
        btnsave = (Button) findViewById(R.id.btn_insertdata);
        btndelete=(Button) findViewById(R.id.btnhapus);

//kondisi perubahan btn save > btn delete dan btn update
        Intent data = getIntent();
        final String iddata = data.getStringExtra("id");
        if(iddata != null) {
            btnsave.setVisibility(View.GONE);
            btnTampildata.setVisibility(View.GONE);
            btnupdate.setVisibility(View.VISIBLE);
            btndelete.setVisibility(View.VISIBLE);
            kode_toko.setText(data.getStringExtra("kode_toko"));
            nama_toko.setText(data.getStringExtra("nama_toko"));
            alamat_toko.setText(data.getStringExtra("alamat_toko"));
            pemilik_toko.setText(data.getStringExtra("pemilik_toko"));
        }

        //btn update
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setVisibility(View.VISIBLE);
                RestApi api =
                        RetroServer.getClient().create(RestApi.class);
                Call<ResponseModel> update =
                        api.updateData(iddata,kode_toko.getText().toString(),nama_toko.getText().toString(),
                                alamat_toko.getText().toString(),pemilik_toko.getText().toString());
                update.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    Log.d("Retro", "Response");
                    Toast.makeText(MainActivity.this,response.body().getPesan(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, TampilData.class));
                    pd.setVisibility(View.GONE);
                    finish();
                }
                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.setVisibility(View.GONE);
                        Log.d("Retro", "OnFailure");
                    }
                });
            }
        });

        //btn delete
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setVisibility(View.VISIBLE);
                RestApi api = RetroServer.getClient().create(RestApi.class);
                Call<ResponseModel> del = api.deleteData(iddata);
                del.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        pd.setVisibility(View.GONE);
                        Log.d("Retro", "onResponse");
                        Toast.makeText(MainActivity.this, response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        Intent gotampil = new Intent(MainActivity.this,TampilData.class);
                        startActivity(gotampil);
                    }
                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.setVisibility(View.GONE);
                        Log.d("Retro", "onFailure");
                    }
                });
            }
        });

        //btn tampil data
        btnTampildata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new
                        Intent(MainActivity.this,TampilData.class));
            }
        });

        //button insert
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String skode_toko = kode_toko.getText().toString();
                String snama_toko = nama_toko.getText().toString();
                String salamat_toko = alamat_toko.getText().toString();
                String spemilik_toko = pemilik_toko.getText().toString();
                if (skode_toko.isEmpty() ) {
                    kode_toko.setError("Kode Toko Perlu di Isi");
                }else if (snama_toko.isEmpty()){
                    nama_toko.setError("Nama Toko Perlu di Isi");
                } else if (salamat_toko.isEmpty()) {
                    alamat_toko.setError("Alamat Toko Perlu di Isi");
                } else if (spemilik_toko.isEmpty()){
                    pemilik_toko.setError("Pemilik Toko Perlu di Isi");
                } else {
                    RestApi api = RetroServer.getClient().create(RestApi.class);
                    Call<ResponseModel> sendbio = api.sendBiodata(snama,susia,sdomisili);
                    sendbio.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                            /*pd.setVisibility(View.GONE);*/
                            Log.d("RETRO", "response : " + response.body().toString());
                            String kode = response.body().getKode();
                            if(kode.equals("1"))
                            {
                                Toast.makeText(MainActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, TampilData.class));
                                kode_toko.getText().clear();
                                nama_toko.getText().clear();
                                alamat_toko.getText().clear();
                                pemilik_toko.getText().clear();
                            }else
                            {Toast.makeText(MainActivity.this, "Data Error Tidak Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseModel> call,
                                              Throwable t) {

                            /*pd.setVisibility(View.GONE);*/
                            Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                        }
                    });
                }}
        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("warning");
        alert.setMessage("do you wan to exit");
        alert.setPositiveButton("yes", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                });
        alert.setNegativeButton("no", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }
}
