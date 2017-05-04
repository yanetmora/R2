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
import mx.edu.utng.orders.model.Node;
import mx.edu.utng.orders.model.Ticket;


public class NodeAdapter extends RecyclerView.Adapter <NodeAdapter.NodeViewHolder> implements View.OnClickListener {
    List<Node> nodes;
    View.OnClickListener listener;

    public NodeAdapter(List<Node> nodes) {
        this.nodes = nodes;
    }
/*************SETTER DE LISTENER******/
    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public NodeAdapter.NodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_node,parent,false);
        NodeAdapter.NodeViewHolder holder=new NodeViewHolder (view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(NodeAdapter.NodeViewHolder holder, int position) {
        holder.tvNodeAssertio.setText(nodes.get(position).getAssertio());
        holder.tvNodeName.setText(nodes.get(position).getName());
        holder.tvNodeTree.setText(nodes.get(position).getTree());
        holder.ivNode.setImageResource(R.mipmap.ic_launcher);

        holder.setListener(this);

    }

    @Override
    public int getItemCount() {
        return nodes.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class NodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cvNode;
        TextView tvNodeAssertio;
        TextView tvNodeName;
        TextView tvNodeTree;
        ImageView ivNode;
        ImageButton btEditNode;
        ImageButton btDeleteNode;
        View.OnClickListener listener;

        public void setListener(View.OnClickListener listener) {
            this.listener = listener;
        }

        public NodeViewHolder(View itemView) {
            super(itemView);
            cvNode=(CardView)itemView.findViewById(R.id.cv_node);
            tvNodeAssertio=(TextView)itemView.findViewById(R.id.tv_node_assertio);
            tvNodeName=(TextView)itemView.findViewById(R.id.tv_node_name);
            tvNodeTree=(TextView)itemView.findViewById(R.id.tv_node_tree);
            ivNode=(ImageView)itemView.findViewById(R.id.iv_node);
            btEditNode=(ImageButton)itemView.findViewById(R.id.bt_edit_node);
            btDeleteNode=(ImageButton)itemView.findViewById(R.id.bt_delet_node);
            btEditNode.setOnClickListener(this);
            btDeleteNode.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onClick(v);
            }
        }
    }
}
