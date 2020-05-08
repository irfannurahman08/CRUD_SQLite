package id.web.iotproject.crud_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    databseHelper mydb;
    EditText editText1,editText2,editText3, editTextid;
    Button btnTambahData,btnshowData, btnUpdateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new databseHelper(this);

        editText1 =(EditText)findViewById(R.id.editText);
        editText2 =(EditText)findViewById(R.id.editText2);
        editText3 =(EditText)findViewById(R.id.editText3);
        editTextid =(EditText)findViewById(R.id.editTextID);
        btnTambahData =(Button)findViewById(R.id.btnAdd);
        btnshowData = (Button)findViewById(R.id.btnAllData);
        btnUpdateData = (Button)findViewById(R.id.btnUpdate);
    }

        public void adddata(View view) {
            boolean tes = mydb.tambahData(editText1.getText().toString(),
                    editText2.getText().toString(),
                    editText3.getText().toString());
        if (tes == true){
            Toast.makeText(MainActivity.this, "data di tambahkan", Toast.LENGTH_SHORT).show();
            editText1.setText("");
            editText2.setText("");
            editText3.setText("");
        }
        else
            Toast.makeText(MainActivity.this, "data tidak di tambahkan", Toast.LENGTH_SHORT).show();
    }

    public void showdata(View view) {
        Cursor res = mydb.getAllData();
        if (res.getCount() == 0){
            TampilkanPesan("Eror ","Data Kosong");
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("id :"+ res.getString(0)+"\n");
            buffer.append("nama :"+ res.getString(1)+"\n");
            buffer.append("surename :"+ res.getString(2)+"\n");
            buffer.append("Score :"+ res.getString(3)+"\n");
        }
        TampilkanPesan("Data",buffer.toString());
    }

    public void TampilkanPesan(String judul, String pesan){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(judul);
        builder.setMessage(pesan);
        builder.show();
    }

    public void updateData(View view) {
        boolean update = mydb.updateData(
                editTextid.getText().toString(),
                editText1.getText().toString(),
                editText2.getText().toString(),
                editText3.getText().toString());

        if (update == true){
            Toast.makeText(MainActivity.this, "data di update", Toast.LENGTH_SHORT).show();
            editText1.setText("");
            editText2.setText("");
            editText3.setText("");
        }
        else
            Toast.makeText(MainActivity.this, "data tidak di update", Toast.LENGTH_SHORT).show();
    }
}
