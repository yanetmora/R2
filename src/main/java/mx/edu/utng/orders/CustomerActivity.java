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

import mx.edu.utng.orders.adapters.CustomerAdapter;
import mx.edu.utng.orders.model.Customer;
import mx.edu.utng.orders.sqlite.DBOperations;



public class CustomerActivity extends AppCompatActivity {
    private EditText etFirtname;
    private EditText etLastname;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etStreet;
    private EditText etZip;
    private EditText etCity;
    private EditText etCountry;
    private EditText etDatereg;
    private EditText etDiscount;
    private EditText etStatus;

    private Button btSaveCustomer;

    private DBOperations operations;
    private Customer customer=new Customer();
    private RecyclerView rvCustomer;
    private List<Customer> customers=new ArrayList<Customer>();
    private CustomerAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        operations=DBOperations.getDBOperations(getApplicationContext());
        customer.setIdCustomer("");
        //metodo
        initComponents();
    }
    private void initComponents() {
        rvCustomer = (RecyclerView) findViewById(R.id.rv_customer_list);
        rvCustomer.setHasFixedSize(true);
        LinearLayoutManager layoutManeger = new LinearLayoutManager(this);
        rvCustomer.setLayoutManager(layoutManeger);
        //uso de metodo
        getCustomers();
        adapter = new CustomerAdapter(customers);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delete_customer:
                        operations.deleteCustomer(customers.get(rvCustomer.getChildPosition((View)v.getParent().getParent())).getIdCustomer());
                        getCustomers();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_customer:
                        Toast.makeText(getApplicationContext(), "Edit Customer", Toast.LENGTH_SHORT).show();
                        Cursor c=operations.getCustomersById(customers.get(rvCustomer.getChildPosition((View)v.getParent().getParent())).getIdCustomer());
                        if(c!=null){
                            if(c.moveToFirst()){
                                customer=new Customer(c.getString(1),c.getString(2),c.getString(3)
                                        ,c.getString(4),
                                        c.getString(5),c.getString(6),c.getString(7),c.getString(8),c.getString(9),c.getString(10),
                                        c.getString(11),c.getString(12));

                                etFirtname.setText(customer.getFirstname());
                                etLastname.setText(customer.getLastname());
                                etPhone.setText(customer.getPhone());
                                etEmail.setText(customer.getEmail());
                                etStreet.setText(customer.getStreet());
                                etZip.setText(customer.getZip());
                                etCity.setText(customer.getCity());
                                etCountry.setText(customer.getCountry());
                                etDatereg.setText(customer.getDatereg());
                                etDiscount.setText(customer.getDiscount());
                                etStatus.setText(customer.getStatus());
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
        ////
        rvCustomer.setAdapter(adapter);

        etFirtname=(EditText)findViewById(R.id.et_firtname);
        etLastname=(EditText)findViewById(R.id.et_lasname);
        etPhone=(EditText)findViewById(R.id.et_phone);
       etEmail=(EditText)findViewById(R.id.et_email);
        etStreet=(EditText)findViewById(R.id.et_street);
        etZip=(EditText)findViewById(R.id.et_zip);
        etCity=(EditText)findViewById(R.id.et_city);
        etCountry=(EditText)findViewById(R.id.et_country);
        etDatereg=(EditText)findViewById(R.id.et_datareg);
        etDiscount=(EditText)findViewById(R.id.et_discount);
        etStatus=(EditText)findViewById(R.id.et_status);


        btSaveCustomer=(Button)findViewById(R.id.bt_save_customer);

        btSaveCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!customer.getIdCustomer().equals("")) {
                    customer.setFirstname(etFirtname.getText().toString());
                    customer.setLastname(etLastname.getText().toString());
                    customer.setPhone(etPhone.getText().toString());
                    customer.setEmail(etEmail.getText().toString());
                    customer.setStreet(etStreet.getText().toString());
                    customer.setZip(etZip.getText().toString());
                    customer.setCity(etCity.getText().toString());
                    customer.setCountry(etCountry.getText().toString());
                    customer.setDatereg(etDatereg.getText().toString());
                    customer.setDiscount(etDiscount.getText().toString());
                    customer.setStatus(etStatus.getText().toString());
                    operations.updateCustomer(customer);
                }else {


                    customer = new Customer("", etFirtname.getText().toString(),
                            etLastname.getText().toString()
                            ,etPhone.getText().toString(),
                            etEmail.getText().toString(),etStreet.getText().toString(),
                            etZip.getText().toString(),
                            etCity.getText().toString(),etCountry.getText().toString(),
                            etDatereg.getText().toString(),etDiscount.getText().toString(),etStatus.getText().toString());
                    operations.insertCustomer(customer);
                }
                //se llaman
                clearData();
                getCustomers();
                adapter.notifyDataSetChanged();
                Log.d("Customer", "Customer");
                DatabaseUtils.dumpCursor(operations.getCustomers());

            }
        });
        //
    }
    //metodo
    private void getCustomers(){
        Cursor c =operations.getCustomers();
        customers.clear();
        if(c!=null){
            while (c.moveToNext()){
                customers.add(new Customer(c.getString(1),c.getString(2),c.getString(3)
                        ,c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8),
                        c.getString(9),c.getString(10),c.getString(11),c.getString(12)));
            }
        }

    }
    //metodo

    private void clearData(){
        etFirtname.setText("");
        etLastname.setText("");
       etPhone.setText("");
        etEmail.setText("");
        etStreet.setText("");
        etZip.setText("");
        etCity.setText("");
        etCountry.setText("");
        etDatereg.setText("");
        etDiscount.setText("");
        etStatus.setText("");




        customer=null;
        /***********si se ocupa***************/
        customer=new Customer();
        customer.setIdCustomer("");
    }
}
