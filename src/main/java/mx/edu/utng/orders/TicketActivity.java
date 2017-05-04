package mx.edu.utng.orders;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orders.adapters.TicketAdapter;
import mx.edu.utng.orders.model.Ticket;
import mx.edu.utng.orders.sqlite.DBOperations;



public class TicketActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPhone;
    private EditText etFolio;
    private Button btSaveTicket;

    private DBOperations operations;
    private Ticket ticket=new Ticket();
    private RecyclerView rvTickets;
    private List<Ticket> tickets=new ArrayList<Ticket>();
    private TicketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        operations=DBOperations.getDBOperations(getApplicationContext());
        ticket.setIdTicket("");
        //metodo
        initComponents();
    }
    private void initComponents(){
        rvTickets=(RecyclerView)findViewById(R.id.rv_ticket_list);
        rvTickets.setHasFixedSize(true);
        LinearLayoutManager layoutManeger=new LinearLayoutManager(this);
        rvTickets.setLayoutManager(layoutManeger);
        //uso de metodo
        getTickets();
        adapter=new TicketAdapter(tickets);
        //el adapter escucha
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delet_ticket:
                        operations.deleteTicket(tickets.get(rvTickets.getChildPosition((View)v.getParent().getParent())).getIdTicket());
                        getTickets();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_ticket:
                        Toast.makeText(getApplicationContext(), "Edit Ticket", Toast.LENGTH_SHORT).show();
                        Cursor c=operations.getTicketById(tickets.get(rvTickets.getChildPosition((View)v.getParent().getParent())).getIdTicket());
                        if(c!=null){
                            if(c.moveToFirst()){
                                ticket=new Ticket(c.getString(1),c.getString(2),c.getInt(3),c.getInt(4));
                                etName.setText(ticket.getName());
                                etPhone.setText(String.valueOf(ticket.getPhone()));
                                etFolio.setText(String.valueOf(ticket.getFolio()));
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
        rvTickets.setAdapter(adapter);

        etName=(EditText)findViewById(R.id.et_name_ticket);
        etPhone=(EditText)findViewById(R.id.et_phone);
        etFolio=(EditText)findViewById(R.id.et_folio);


        btSaveTicket=(Button)findViewById(R.id.bt_save_ticket);

        btSaveTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ticket.getIdTicket().equals("")) {
                ticket.setName(etName.getText().toString());
                    ticket.setPhone(Integer.parseInt(etPhone.getText().toString()));
                    ticket.setFolio(Integer.parseInt(etFolio.getText().toString()));
                    operations.updateTicket(ticket);
                }else {


                    ticket = new Ticket("", etName.getText().toString(), Integer.parseInt(etPhone.getText().toString()), Integer.parseInt(etFolio.getText().toString()));
                    operations.insertTicket(ticket);
                }
                    //se llaman
                clearData();
                    getTickets();
                    adapter.notifyDataSetChanged();
                    Log.d("Tickets", "Tickets");
                    DatabaseUtils.dumpCursor(operations.getTickets());

            }
        });

    }
//metodo
    private void getTickets(){
        Cursor c =operations.getTickets();
        tickets.clear();
        if(c!=null){
            while (c.moveToNext()){
                tickets.add(new Ticket(c.getString(1),c.getString(2),c.getInt(3),c.getInt(4)));
            }
        }

    }
    //metodo
    private void clearData(){
        etName.setText("");
        etPhone.setText("");
        etFolio.setText("");
        ticket=null;
        /***********si se ocupa***************/
        ticket=new Ticket();
        ticket.setIdTicket("");
    }
}
