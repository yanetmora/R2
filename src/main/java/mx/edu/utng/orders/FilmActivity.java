package mx.edu.utng.orders;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orders.adapters.FilmAdapter;
import mx.edu.utng.orders.model.Film;
import mx.edu.utng.orders.sqlite.DBOperations;



public class FilmActivity extends AppCompatActivity {
    private EditText etTitle;
    private EditText etDescription;
    private EditText etRelease;
    private EditText etLanguage;
    private EditText etRentalDuration;
    private EditText etRentalRate;
    private EditText etLength;
    private EditText etReplacement;
    private EditText etRating;
    private EditText etLast;
    private EditText etSpecial;
    private EditText etFulltext;

    private Button btSaveFilm;

    private DBOperations operations;
    private Film film=new Film();
    private RecyclerView rvFilm;
    private List<Film> films=new ArrayList<Film>();
    private FilmAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        operations=DBOperations.getDBOperations(getApplicationContext());
        film.setIdFilm("");
        //metodo
        initComponents();

    }


    private void initComponents() {
        rvFilm = (RecyclerView) findViewById(R.id.rv_film_list);
        rvFilm.setHasFixedSize(true);
        LinearLayoutManager layoutManeger = new LinearLayoutManager(this);
        rvFilm.setLayoutManager(layoutManeger);
        //uso de metodo
        getFilms();
        adapter = new FilmAdapter(films);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delete_film:
                        operations.deleteFilm(films.get(rvFilm.getChildPosition((View)v.getParent().getParent())).getIdFilm());
                        getFilms();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_film:
                        Toast.makeText(getApplicationContext(), "Edit Film", Toast.LENGTH_SHORT).show();
                        Cursor c=operations.getFilmsById(films.get(rvFilm.getChildPosition((View)v.getParent().getParent())).getIdFilm());
                        if(c!=null){
                            if(c.moveToFirst()){
                                film=new Film(c.getString(1),c.getString(2),c.getString(3)
                                        ,c.getString(4),
                                        c.getString(5),c.getString(6),c.getString(7),c.getString(8),c.getString(9),c.getString(10),
                                        c.getString(11),c.getString(12),c.getString(13));

                                etTitle.setText(film.getTitle());
                                etDescription.setText(film.getDescription());
                                etRelease.setText(film.getReleaseYear());
                                etLanguage.setText(film.getLanguage());
                                etRentalDuration.setText(film.getRentalDuration());
                                etRentalRate.setText(film.getRentalRate());
                                etLength.setText(film.getLenght());
                                etReplacement.setText(film.getReplacementCost());
                                etRating.setText(film.getRating());
                                etLast.setText(film.getLastUpdate());
                                etSpecial.setText(film.getSpecialFeatures());
                                etFulltext.setText(film.getFulltext());
                            }else{
                                Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        rvFilm.setAdapter(adapter);

        etTitle=(EditText)findViewById(R.id.et_title);
        etDescription=(EditText)findViewById(R.id.et_description);
        etRelease=(EditText)findViewById(R.id.et_release_year);
        etLanguage=(EditText)findViewById(R.id.et_languaje);
        etRentalDuration=(EditText)findViewById(R.id.et_rental_duration);
        etRentalRate=(EditText)findViewById(R.id.et_rental_rate);
        etLength=(EditText)findViewById(R.id.et_length);
        etReplacement=(EditText)findViewById(R.id.et_replacement);
        etRating=(EditText)findViewById(R.id.et_rating);
        etLast=(EditText)findViewById(R.id.et_last);
        etSpecial=(EditText)findViewById(R.id.et_special);
        etFulltext=(EditText)findViewById(R.id.et_fulltext);


        btSaveFilm=(Button)findViewById(R.id.bt_save_film);
        btSaveFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!film.getIdFilm().equals("")) {
                    film.setTitle(etTitle.getText().toString());
                    film.setDescription(etDescription.getText().toString());
                    film.setReleaseYear(etRelease.getText().toString());
                    film.setLanguage(etLanguage.getText().toString());
                    film.setRentalDuration(etRentalDuration.getText().toString());
                    film.setRentalRate(etRentalRate.getText().toString());
                    film.setLenght(etLength.getText().toString());
                    film.setReplacementCost(etReplacement.getText().toString());
                    film.setRating(etRating.getText().toString());
                    film.setLastUpdate(etLast.getText().toString());
                    film.setSpecialFeatures(etSpecial.getText().toString());
                    film.setFulltext(etFulltext.getText().toString());
                    operations.updateFilm(film);
                }else {


                    film = new Film("", etTitle.getText().toString(),
                            etDescription.getText().toString()
                            ,etRelease.getText().toString(),
                            etLanguage.getText().toString(),etRentalDuration.getText().toString(),
                            etRentalRate.getText().toString(),
                            etLength.getText().toString(),etReplacement.getText().toString(),
                            etRating.getText().toString(),etLast.getText().toString(),etSpecial.getText().toString()
                            ,etFulltext.getText().toString());
                    operations.insertFilm(film);
                }

                //se llaman
                clearData();
                getFilms();
                adapter.notifyDataSetChanged();
                Log.d("Film", "Films");
                DatabaseUtils.dumpCursor(operations.getFilms());


            }
        });

    }
    //metodo
    private void getFilms(){
        Cursor c =operations.getFilms();
        films.clear();
        if(c!=null){
            while (c.moveToNext()){
                films.add(new Film(c.getString(1),c.getString(2),c.getString(3)
                        ,c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8),
                        c.getString(9),c.getString(10),c.getString(11),c.getString(12),c.getString(13)));
            }
        }

    }
    //metodo

    private void clearData(){
        etTitle.setText("");
        etDescription.setText("");
        etRelease.setText("");
        etLanguage.setText("");
        etRentalDuration.setText("");
        etRentalRate.setText("");
        etLength.setText("");
        etReplacement.setText("");
        etRating.setText("");
        etLast.setText("");
        etSpecial.setText("");
        etFulltext.setText("");




        film=null;
        /***********si se ocupa***************/
        film=new Film();
        film.setIdFilm("");
    }

}
