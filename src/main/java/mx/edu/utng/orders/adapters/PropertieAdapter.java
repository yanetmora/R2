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

import mx.edu.utng.orders.model.Propertie;
import mx.edu.utng.orders.R;
import mx.edu.utng.orders.model.Propertie;


/**
public class PropertieAdapter extends RecyclerView.Adapter<PropertieAdapter.PropertieViewHolder>
        implements View.OnClickListener {


    List<Propertie> properties;
    View.OnClickListener listener;
    //Constructor
    public PropertieAdapter(List<Propertie> properties){
        this.properties = properties;
    }
    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public PropertieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se inflael cardview al reciclerview
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_propertie,parent,false);
        PropertieViewHolder holder=new PropertieViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(PropertieViewHolder holder, int position) {
        holder.tvCompanyName.setText(properties.get(position).getCompanyName());
        holder.tvContacName.setText(properties.get(position).getContacName());
        holder.tvContacTitle.setText(properties.get(position).getContactTitle());
        holder.tvAddress.setText(properties.get(position).getAddress());
        holder.tvCity.setText(properties.get(position).getCity());
        holder.tvRegion.setText(properties.get(position).getRegion());
        holder.tvCodigoPostal.setText(properties.get(position).getPostalCode());
        holder.tvCountry.setText(properties.get(position).getCountry());
        holder.tvPhone.setText(properties.get(position).getPhone());
        holder.tvFax.setText(properties.get(position).getFax());


        holder.ivDepTemp.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);

    }

    @Override
    public int getItemCount() {
        return properties.size();
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public static class PropertieViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cvPropertie;
        TextView tvCompanyName;
        TextView tvContacName;
        TextView tvContacTitle;
        TextView tvAddress;
        TextView tvCity;
        TextView tvRegion;
        TextView tvCodigoPostal;
        TextView tvCountry;
        TextView tvPhone;
        TextView tvFax;

        ImageView ivDepTemp;
        ImageButton btEditDeptEmp;
        ImageButton btDeleteDeptEmp;

        View.OnClickListener listener;




        public PropertieViewHolder(View itemView) {
            super(itemView);
            cvPropertie =(CardView)itemView.findViewById(R.id.cv_propertie);
            tvCompanyName =(TextView)itemView.findViewById(R.id.tv_name_company);
            tvContacName =(TextView)itemView.findViewById(R.id.tv_contac_name);
            tvContacTitle =(TextView)itemView.findViewById(R.id.tv_contac_title);
            tvAddress =(TextView)itemView.findViewById(R.id.tv_address);
            tvCity =(TextView)itemView.findViewById(R.id.tv_city);
            tvRegion =(TextView)itemView.findViewById(R.id.tv_region);
            tvCodigoPostal =(TextView)itemView.findViewById(R.id.tv_cp);
            tvCountry =(TextView)itemView.findViewById(R.id.tv_pais);
            tvPhone =(TextView)itemView.findViewById(R.id.tv_phone);
            tvFax =(TextView)itemView.findViewById(R.id.tv_fax);

            ivDepTemp=(ImageView)itemView.findViewById(R.id.iv_propertie);
            btEditDeptEmp=(ImageButton) itemView.findViewById(R.id.bt_edit_propertie);
            btDeleteDeptEmp=(ImageButton) itemView.findViewById(R.id.bt_delete_propertie);
            btEditDeptEmp.setOnClickListener(this);
            btDeleteDeptEmp.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onClick(v);
            }
        }

        public void setListener(View.OnClickListener listener){
            this.listener=listener;

        }
    }
}
*/