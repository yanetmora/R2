package mx.edu.utng.orders.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.edu.utng.orders.R;
import mx.edu.utng.orders.model.Film;



public class FilmAdapter extends   RecyclerView.Adapter <FilmAdapter.FilmViewHolder> implements View.OnClickListener{
    List<Film> films;
    View.OnClickListener listener;

    public FilmAdapter(List<Film> films) {
        this.films = films;
    }
    /*GETTER SETTER**/

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se inflate
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film,parent,false);
        FilmViewHolder holder=new FilmViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {

        holder.tvTitle.setText(films.get(position).getTitle());
        holder.tvDescription.setText(films.get(position).getDescription());
        holder.tvReleaseYear.setText(films.get(position).getReleaseYear());
        /*
        holder.tvLanguaje.setText(films.get(position).getLanguage());
        holder.tvRentalDuration.setText(films.get(position).getRentalDuration());
        holder.tvRentalRate.setText(films.get(position).getRentalRate());
        holder.tvLength.setText(films.get(position).getLenght());

        holder.tvReplacement.setText(films.get(position).getReplacementCost());
        holder.tvRating.setText(films.get(position).getRating());
        holder.tvLast.setText(films.get(position).getLastUpdate());
        holder.tvSpecialFeuture.setText(films.get(position).getSpecialFeatures());
        holder.tvFulltext.setText(films.get(position).getFulltext());*/

/// imagen
        holder.ivFilm.setImageResource(R.drawable.customer);
        /**************/
        holder.setListener(this);

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    /*************clase****/
    public class FilmViewHolder  extends  RecyclerView.ViewHolder implements View.OnClickListener {
    CardView cvFilm;
    TextView tvTitle;
    TextView tvDescription;
    TextView tvReleaseYear;
    TextView tvLanguaje;
    TextView tvRentalDuration;
    TextView tvRentalRate;
    TextView tvLength;
    TextView tvReplacement;
    TextView tvRating;
    TextView tvLast;
    TextView tvSpecialFeuture;
    TextView tvFulltext;
    ImageView ivFilm;
    ImageButton btEditFilm;
    ImageButton btDeleteFilm;
    View.OnClickListener listener;


        public void setListener(View.OnClickListener listener) {
            this.listener = listener;
        }

        public FilmViewHolder(View itemView) {
            super(itemView);
            cvFilm=(CardView)itemView.findViewById(R.id.cv_film);
            tvTitle=(TextView)itemView.findViewById(R.id.tv_title);
            tvDescription=(TextView)itemView.findViewById(R.id.tv_description);
            tvReleaseYear=(TextView)itemView.findViewById(R.id.tv_release_year);
           /* tvLanguaje=(TextView)itemView.findViewById(R.id.tv_languaje);
            tvRentalDuration=(TextView)itemView.findViewById(R.id.tv_rental_duration);
            tvRentalRate=(TextView)itemView.findViewById(R.id.tv_rental_rate);
            tvLength=(TextView)itemView.findViewById(R.id.tv_length);
            tvReplacement=(TextView)itemView.findViewById(R.id.tv_replacement);
            tvRating=(TextView)itemView.findViewById(R.id.tv_rating);
            tvLast=(TextView)itemView.findViewById(R.id.tv_last_update);
            tvSpecialFeuture=(TextView)itemView.findViewById(R.id.tv_special);
            tvFulltext=(TextView)itemView.findViewById(R.id.tv_fulltext);*/

            ivFilm=(ImageView)itemView.findViewById(R.id.iv_film);

            btEditFilm=(ImageButton)itemView.findViewById(R.id.bt_edit_film);

            btDeleteFilm=(ImageButton)itemView.findViewById(R.id.bt_delete_film);
            btEditFilm.setOnClickListener(this);
            btDeleteFilm.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onClick(v);
            }
        }
    }
}
