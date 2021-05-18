package com.egc.listviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.egc.listviewexample.models.Car;
import com.ib.custom.toast.CustomToastView;

public class carRegister extends AppCompatActivity implements View.OnClickListener {
    DbHelper db = new DbHelper(this);
    private TextView txtName;
    private TextView txtBienvenido;
    private TextView txtModelo;
    private TextView txtCilindraje;
    private TextView txtValor;
    private TextView ptxtImagen;
    private Button btnListar;
    private Button btnAdd;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_register);
        txtBienvenido = findViewById(R.id.txtBienvenido);
        txtName = findViewById(R.id.txtName);
        txtModelo = findViewById(R.id.txtModelo);
        txtCilindraje = findViewById(R.id.txtCilindraje);
        txtValor = findViewById(R.id.txtValor);
        ptxtImagen = findViewById(R.id.ptxtImagen);
        btnDelete= findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        btnListar = findViewById(R.id.btnListar);
        btnListar.setOnClickListener(this);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            Car C = new Car(txtName.getText().toString(), txtCilindraje.getText().toString(), txtModelo.getText().toString(),
                    txtValor.getText().toString(), ptxtImagen.getText().toString());

            db.insertCar(db.getWritableDatabase(), C);

            CustomToastView.makeSuccessToast(this, "Auto Guardado", R.layout.custom_toast).show();

           clearFields();

        } else if (v.getId() == R.id.btnListar){
            Intent myIntent= new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }
        else if(v.getId()==R.id.btnDelete){
            db.deleteAll();
            CustomToastView.makeSuccessToast(this, "Registros eliminados", R.layout.custom_toast).show();
        }
    }
    public void clearFields(){
        txtName.setText("");
        txtCilindraje.setText("");
        txtModelo.setText("");
        txtValor.setText("");
        ptxtImagen.setText("");
    }
}