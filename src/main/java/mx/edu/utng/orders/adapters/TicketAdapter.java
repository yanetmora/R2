package mx.edu.utng.orders.adapters;

import android.content.Context;
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
import mx.edu.utng.orders.model.Ticket;


public class TicketAdapter extends RecyclerView.Adapter <TicketAdapter.TicketViewHolder> implements View.OnClickListener {

    List<Ticket> tickets;
    View.OnClickListener listener;
//CONSTRUCTOR
    public TicketAdapter(List<Ticket> tickets) {
        this.tickets = tickets;
    }
/******GETTER AND SETTER LISTENER*****/
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public TicketAdapter.TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se inflate
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket,parent,false);
        TicketViewHolder holder=new TicketViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(TicketAdapter.TicketViewHolder holder, int position) {
        holder.tvTicketName.setText(tickets.get(position).getName());
        holder.tvTicketPhone.setText(String.valueOf(tickets.get(position).getPhone()));
        holder.tvTicketFolio.setText(String.valueOf(tickets.get(position).getFolio()));
        holder.ivTicket.setImageResource(R.drawable.ticket);
        /**************/
        holder.setListener(this);
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class TicketViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cvTicket;
        TextView tvTicketName;
        TextView tvTicketPhone;
        TextView tvTicketFolio;
        ImageView ivTicket;
        ImageButton btEditTicket;
        ImageButton btDeleteTicket;
        View.OnClickListener listener;
/*Setter de listener*/
        public void setListener(View.OnClickListener listener) {
            this.listener = listener;
        }

        //constructor
        //relacionar los elementos
        public TicketViewHolder(View itemView) {
            super(itemView);
            cvTicket=(CardView)itemView.findViewById(R.id.cv_ticket);
            tvTicketName=(TextView)itemView.findViewById(R.id.tv_ticket_name);
            tvTicketPhone=(TextView)itemView.findViewById(R.id.tv_ticket_phone);
            tvTicketFolio=(TextView)itemView.findViewById(R.id.tv_ticket_folio);
            ivTicket=(ImageView)itemView.findViewById(R.id.iv_ticket);
            btEditTicket=(ImageButton)itemView.findViewById(R.id.bt_edit_ticket);
            btDeleteTicket=(ImageButton)itemView.findViewById(R.id.bt_delet_ticket);
            btEditTicket.setOnClickListener(this);
            btDeleteTicket.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onClick(v);
            }
        }
    }
}
