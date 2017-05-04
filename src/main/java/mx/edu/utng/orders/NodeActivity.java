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

import mx.edu.utng.orders.adapters.NodeAdapter;
import mx.edu.utng.orders.model.Node;
import mx.edu.utng.orders.sqlite.DBOperations;


public class NodeActivity extends AppCompatActivity {
    private EditText etAssertio;
    private EditText etName;
    private EditText etTree;
    private Button btSaveNode;
    private DBOperations operations;
    private Node node=new Node();
    private RecyclerView rvNodes;
    private List<Node> nodes=new ArrayList<Node>();
    private NodeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);
        operations=DBOperations.getDBOperations(getApplicationContext());
        node.setIdNode("");
        initComponents();
    }
    private void initComponents(){
        rvNodes=(RecyclerView)findViewById(R.id.rv_node_list);
        rvNodes.setHasFixedSize(true);
        LinearLayoutManager layoutManeger=new LinearLayoutManager(this);
        rvNodes.setLayoutManager(layoutManeger);
        //
        getNodes();
        adapter=new NodeAdapter(nodes);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 switch (v.getId()){
                    case R.id.bt_delet_node:

                        operations.deleteNode(nodes.get(rvNodes.getChildPosition((View)v.getParent().getParent())).getIdNode());
                        getNodes();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_node:

                        Cursor c=operations.getNodeById(nodes.get(rvNodes.getChildPosition((View)v.getParent().getParent())).getIdNode());
                        if(c!=null){
                            if(c.moveToFirst()){
                                node=new Node(c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                                etAssertio.setText(node.getAssertio());
                                etName.setText(node.getName());
                                etTree.setText(node.getTree());

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
        rvNodes.setAdapter(adapter);
        etAssertio=(EditText)findViewById(R.id.et_assertio);
        etName=(EditText)findViewById(R.id.et_node_name);
        etTree=(EditText)findViewById(R.id.et_tree);
        btSaveNode=(Button)findViewById(R.id.bt_save_node);
        btSaveNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if(!node.getIdNode().equals("")) {
                      node.setAssertio(etAssertio.getText().toString());
                      node.setName(etName.getText().toString());
                      node.setTree(etTree.getText().toString());
                    operations.updateNode(node);
                }else {


                    node = new Node("", etAssertio.getText().toString(),etName.getText().toString(),etTree.getText().toString());
                    operations.insertNodes(node);
                }
                clearData();
                    getNodes();
                    adapter.notifyDataSetChanged();
                    Log.d("Nodes", "Nodes");
                    DatabaseUtils.dumpCursor(operations.getNodes());
            }
        });

    }

    //metodos
    private void getNodes(){
        Cursor c =operations.getNodes();
        nodes.clear();
        if(c!=null){
            while (c.moveToNext()){
                nodes.add(new Node(c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }
        }

    }
    private void clearData(){
        etAssertio.setText("");
        etName.setText("");
        etTree.setText("");
        node=null;

    node=new Node();
    node.setIdNode("");
}
}
