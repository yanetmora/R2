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
import mx.edu.utng.orders.model.Customer;



public class CustomerAdapter extends RecyclerView.Adapter <CustomerAdapter.CustomerViewHolder>implements View.OnClickListener {
    List<Customer> customers;
    View.OnClickListener listener;

    public CustomerAdapter(List<Customer> customers) {
        this.customers = customers;
    }
/********listener**********/
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se inflate
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer,parent,false);
        CustomerViewHolder holder=new CustomerViewHolder(view);
        view.setOnClickListener(this);
        return holder;

    }

    @Override
    public void onBindViewHolder(CustomerViewHolder holder, int position) {
        holder.tvFirtsname.setText(customers.get(position).getFirstname());
        holder.tvLastname.setText(customers.get(position).getLastname());
      holder.tvPhone.setText(customers.get(position).getPhone());
       /*   holder.tvEmail.setText(customers.get(position).getEmail());
        holder.tvStreet.setText(customers.get(position).getStreet());
        holder.tvZip.setText(customers.get(position).getZip());
        holder.tvCity.setText(customers.get(position).getCity());
        holder.tvCountry.setText(customers.get(position).getCountry());
        holder.tvDatereg.setText(customers.get(position).getDatereg());
        holder.tvDiscount.setText(customers.get(position).getDiscount());
        holder.tvStatus.setText(customers.get(position).getStatus());*/

/// imagen
        holder.ivCustomer.setImageResource(R.drawable.customer);
        /**************/
       holder.setListener(this);
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    /********CLASE******/
    public static class CustomerViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cvCustomer;
        TextView tvFirtsname;
        TextView tvLastname;
        TextView tvPhone;
        TextView tvEmail;
        TextView tvStreet;
        TextView tvZip;
        TextView tvCity;
        TextView tvCountry;
        TextView tvDatereg;
        TextView tvDiscount;
        TextView tvStatus;
        ImageView ivCustomer;
        ImageButton btEditCustomer;
        ImageButton btDeleteCustomer;
        View.OnClickListener listener;


        public void setListener(View.OnClickListener listener) {
            this.listener = listener;
        }

        public CustomerViewHolder(View itemView) {
            super(itemView);
            cvCustomer=(CardView)itemView.findViewById(R.id.cv_customer);
            tvFirtsname=(TextView)itemView.findViewById(R.id.tv_firtname);
            tvLastname=(TextView)itemView.findViewById(R.id.tv_lastname);tvPhone=(TextView)itemView.findViewById(R.id.tv_phone_customer);
            /*tvEmail=(TextView)itemView.findViewById(R.id.tv_email);
            tvStreet=(TextView)itemView.findViewById(R.id.tv_street);
            tvZip=(TextView)itemView.findViewById(R.id.tv_zip);
            tvCity=(TextView)itemView.findViewById(R.id.tv_city);
            tvCountry=(TextView)itemView.findViewById(R.id.tv_country);
            tvDatereg=(TextView)itemView.findViewById(R.id.tv_datereg);
            tvDiscount=(TextView)itemView.findViewById(R.id.tv_discount);
            tvStatus=(TextView)itemView.findViewById(R.id.tv_status);*/

            ivCustomer=(ImageView)itemView.findViewById(R.id.iv_customer);

            btEditCustomer=(ImageButton)itemView.findViewById(R.id.bt_edit_customer);

            btDeleteCustomer=(ImageButton)itemView.findViewById(R.id.bt_delete_customer);
            btEditCustomer.setOnClickListener(this);
            btDeleteCustomer.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onClick(v);
            }
        }
    }

    }
