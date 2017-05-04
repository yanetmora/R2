package mx.edu.utng.orders;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orders.R;

import mx.edu.utng.orders.model.Propertie;
import mx.edu.utng.orders.sqlite.DBOperations;


public class PropertieActivity extends AppCompatActivity {
   /* private EditText etCompanyName;
    private EditText etContactName;
    private EditText etContacTitle;
    private EditText etAddress;
    private EditText etCity;
    private EditText etRegion;
    private EditText etPostalCode;
    private EditText etCountry;
    private EditText etPhone;
    private EditText etFax;
    private Button btSave;
    private DBOperations operations;
    private Propertie propiedates = new Propertie();
    private RecyclerView rvProperties;
    private List<Propertie> properties =new ArrayList<Propertie>();
    private PropertieAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertie);

        //iniciacion de la instancia
        operations=DBOperations.getDBOperations(getApplicationContext());
        propiedates.setId("");


        initComponents();
    }
    private void initComponents(){
        rvProperties=(RecyclerView)findViewById(R.id.rv_propertie_list);
        rvProperties.setHasFixedSize(true);
        LinearLayoutManager layoutManeger=new LinearLayoutManager(this);
        rvProperties.setLayoutManager(layoutManeger);
        //
        //getPropiedades();
        adapter=new PropertieAdapter(properties);
/*
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delete_propertie:
                        operations.deletePropertie(propiedates.get(rvProperties.getChildPosition((View)v.getParent().getParent())).getIdDep());
                        getPropertie();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_deptEmp:

                        Cursor c = operations.getDeptEmpById(propiedates.get(
                                rvProperties.getChildPosition(
                                        (View)v.getParent().getParent())).getIdDep());
                        if (c!=null){
                            if (c.moveToFirst()){
                                propiedates = new DeptEmp
                                        (c.getString(1),c.getString(2),c.getString(3),c.getString(4),
                                                c.getString(5),c.getString(6),c.getString(7)c.getString(8),c.getString(9),c.getString(10));
                            }
                            etCompanyName.setText(propiedates.getCompanyName());
                            etContactName.setText(propiedates.getContacName());
                            etContacTitle.setText(propiedates.getContactTitle());


                        }else{
                            Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
        rvDeptEmps.setAdapter(adapter);

        etCompanyName=(EditText)findViewById(R.id.et_cpn);
        etToDate=(EditText)findViewById(R.id.et_toDate);
        etEmpNo=(EditText)findViewById(R.id.et_empNo);

        btSave=(Button)findViewById(R.id.bt_save);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!deptEmp.getIdDep().equals("")){
                    deptEmp.setFromDate(etFromDate.getText().toString());
                    deptEmp.setToDate(etToDate.getText().toString());
                    deptEmp.setEmpNo(etEmpNo.getText().toString());
                    operations.updateDeptEmp(deptEmp);

                }else {
                    deptEmp = new DeptEmp("", etFromDate.getText().toString(),
                            etToDate.getText().toString(),
                            etEmpNo.getText().toString());

                    operations.insertDeptEmp(deptEmp);
                }
                getDeptEmps();
                clearData();
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void getPropiedades(){
        Cursor c =operations.getDeptEmps();
        deptEmps.clear();
        if(c!=null){
            while (c.moveToNext()){
                deptEmps.add(new DeptEmp(c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)));
            }
        }

    }

    private void clearData(){
        etFromDate.setText("");
        etToDate.setText("");
        etEmpNo.setText("");
        deptEmp=null;
        deptEmp= new DeptEmp();
        deptEmp.setIdDep("");
    } */

}

