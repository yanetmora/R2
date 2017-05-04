package mx.edu.utng.orders;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orders.adapters.ProductAdapter;
import mx.edu.utng.orders.model.Product;
import mx.edu.utng.orders.sqlite.DBOperations;
import mx.edu.utng.orders.sqlite.OrderContract;

public class ProductActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPrice;
    private EditText etStock;
    private Button btSaveProduct;
    private DBOperations operations;
    private Product product=new Product();
    private RecyclerView rvProducts;
    private  List<Product> products=new ArrayList<Product>();
    private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
       //iniciacion de la isntancia
        operations=DBOperations.getDBOperations(getApplicationContext());
        //inicia vacio
        product.setIdProduct("");

        initComponents();
    }
    private void initComponents(){
        rvProducts=(RecyclerView)findViewById(R.id.rv_product_list);
        rvProducts.setHasFixedSize(true);
        LinearLayoutManager layoutManeger=new LinearLayoutManager(this);
        rvProducts.setLayoutManager(layoutManeger);
       getProducts();
        adapter=new ProductAdapter(products);

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delet_product:
                        operations.deleteProduct(products.get(rvProducts.getChildPosition((View)v.getParent().getParent())).getIdProduct());
                        getProducts();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_product:
                        Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
                        Cursor c=operations.getProductById(products.get(rvProducts.getChildPosition((View)v.getParent().getParent())).getIdProduct());
                        if(c!=null){
                            if(c.moveToFirst()){
                                product=new Product(c.getString(1),c.getString(2),c.getInt(3),c.getFloat(4));
                                etName.setText(product.getName());
                                etStock.setText(String.valueOf(product.getStock()));
                                etPrice.setText(String.valueOf(product.getPrice()));
                            }else{
                                Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    default:
                        break;
                }

                //borrar pasandol un ID
               // operations.deleteProduct(products.get(rvProducts.getChildPosition(v)).getIdProduct());
               // getProducts();
               // products.remove(rvProducts.getChildPosition(v));
                //el cambio del adapter
                //adapter.notifyDataSetChanged();
                //rvProducts.invalidate();
            }


        });
        rvProducts.setAdapter(adapter);

        etName=(EditText)findViewById(R.id.et_name);
        etPrice=(EditText)findViewById(R.id.et_price);
        etStock=(EditText)findViewById(R.id.et_stock);

        btSaveProduct=(Button)findViewById(R.id.bt_save_product);

        btSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!product.getIdProduct().equals("")) {
                product.setName(etName.getText().toString());
                    product.setPrice(Float.parseFloat(etPrice.getText().toString()));
                    product.setStock(Integer.parseInt(etStock.getText().toString()));
                    operations.updateProduct(product);
                }else {


                    product = new Product("", etName.getText().toString(), Integer.parseInt(etStock.getText().toString()), Float.parseFloat(etPrice.getText().toString()));
                    operations.insertProduct(product);
                }
                    //se llaman los productos
                clearData();
                    getProducts();
                    adapter.notifyDataSetChanged();
                    Log.d("Products", "Products");
                    DatabaseUtils.dumpCursor(operations.getProducts());

            }
        });

    }
    private void getProducts(){
        Cursor c =operations.getProducts();
        products.clear();
        if(c!=null){
            while (c.moveToNext()){
                products.add(new Product(c.getString(1),c.getString(2),c.getInt(3),c.getFloat(4)));
            }
        }

    }
    private void clearData(){
        etName.setText("");
        etPrice.setText("");
        etStock.setText("");
        product=null;
       /***********si se ocupa***************/
        product=new Product();
        product.setIdProduct("");
    }
}
