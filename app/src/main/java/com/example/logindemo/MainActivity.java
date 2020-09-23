package com.example.logindemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button insert;
    private Button update;
    private Button delete;
    private Button read;
    private TextView show_data;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Realm Data");
        //anh xa
        realm = Realm.getDefaultInstance();
        Button insert = findViewById(R.id.insert);
        Button update = findViewById(R.id.update);
        Button read = findViewById(R.id.read);
        Button delete = findViewById(R.id.delete);
        TextView textView = findViewById(R.id.show_data);


        insert.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        read.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.insert) {
            Log.d("insert", "insert");
            SoInsertDialog();
        }
        if (v.getId() == R.id.update) {
            Log.d("insert", "insert");
        }
        if (v.getId() == R.id.delete) {
            Log.d("insert", "insert");
        }
        if (v.getId() == R.id.read) {
            Log.d("insert", "insert");
        }
    }

    private void SoInsertDialog() {
        final AlertDialog.Builder al = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.insert_dialog, null);
        al.setView(view);

        final EditText name = view.findViewById(R.id.name);
        final EditText age = view.findViewById(R.id.age);
        final Spinner gender = view.findViewById(R.id.gender);
        Button save = view.findViewById(R.id.save);
        final AlertDialog alertDialog = al.show();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                final DataModel dataModel = new DataModel();
                Number current_id = realm.where(DataModel.class).max("id");
                long nextid;
                if(current_id==null){
                   nextid=1;
                }else{
                    nextid=current_id.intValue()+1;
                }
                dataModel.setId(nextid








































                  );

                dataModel.setAge(Integer.parseInt(age.getText().toString()));
                dataModel.setName(name.getText().toString());
                dataModel.setGender(gender.getSelectedItem().toString());

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(dataModel);
                    }
                });
            }
        });
    }
}