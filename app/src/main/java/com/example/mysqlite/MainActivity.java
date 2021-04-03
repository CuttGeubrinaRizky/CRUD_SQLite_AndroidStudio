package com.example.mysqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysqlite.data.DatabaseHelper;
import com.example.mysqlite.data.Mahasiswa;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText nama, nim, jurusan, no;
    Button simpan, viewAll, update, delete;
    TextView textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MainActivity.this.deleteDatabase("mahasiswaa.db");
        myDb = new DatabaseHelper(this);

        nama = (EditText) findViewById(R.id.editTextName);
        nim = (EditText) findViewById(R.id.editTextNim);
        jurusan = (EditText) findViewById(R.id.editTextJur);
        no = (EditText) findViewById((R.id.editTextNo));
        simpan = (Button) findViewById((R.id.btn));
        viewAll = (Button) findViewById((R.id.button));
        update = (Button) findViewById(R.id.btnUpdate);
        delete = (Button) findViewById(R.id.btnDelete);
        AddData();
        ViewAll();
        UpdateData();
        DeleteData();
    }

    public void DeleteData() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(no.getText().toString());
                if(deletedRows > 0 )
                    Toast.makeText(MainActivity.this,"Data Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void UpdateData() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(no.getText().toString(), nama.getText().toString(), nim.getText().toString(), jurusan.getText().toString());
                if(isUpdate == true)
                    Toast.makeText(MainActivity.this,"Data Update", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void AddData(){
        simpan.setOnClickListener((v) -> {
            boolean isInserted = myDb.addOne (nama.getText().toString(), nim.getText().toString(), jurusan.getText().toString());
            if(isInserted == true)
                Toast.makeText(MainActivity.this,"Data Inserted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this,"Data not Inserted", Toast.LENGTH_SHORT).show();

        });
    }

    public void ViewAll() {
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("no :"+ res.getString(0)+"\n");
                    buffer.append("nama :"+ res.getString(1)+"\n");
                    buffer.append("nim :"+ res.getString(2)+"\n");
                    buffer.append("jurusan :"+ res.getString(3)+"\n\n");
                }

                //show all data
                showMessage("Data", buffer.toString());
            }
        });
    }

    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}