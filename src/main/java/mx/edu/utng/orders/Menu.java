package mx.edu.utng.orders;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mx.edu.utng.orders.model.Customer;


public class Menu extends AppCompatActivity {
    private Button btCustomer;
    private Button btFilm;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        btCustomer=(Button)findViewById(R.id.bt_customer);
        btFilm=(Button)findViewById(R.id.bt_film);
        btCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this, CustomerActivity.class);
                startActivity(intent);
            }
        });
        btFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent film=new Intent(Menu.this, FilmActivity.class);
                startActivity(film);
            }
        });

    }
}
