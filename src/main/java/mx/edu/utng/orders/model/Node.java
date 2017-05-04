package mx.edu.utng.orders.model;



public class Node {
    private String idNode;
    private String assertio;
    private String name;
    private  String tree;

    public Node(String idNode, String assertio, String name, String tree) {
        this.idNode = idNode;
        this.assertio = assertio;
        this.name = name;
        this.tree = tree;
    }
    public Node() {
        this("","","","");
    }

    public String getIdNode() {
        return idNode;
    }

    public void setIdNode(String idNode) {
        this.idNode = idNode;
    }

    public String getAssertio() {
        return assertio;
    }

    public void setAssertio(String assertio) {
        this.assertio = assertio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }

    @Override
    public String toString() {
        return "Node{" +
                "idNode='" + idNode + '\'' +
                ", assertio='" + assertio + '\'' +
                ", name='" + name + '\'' +
                ", tree='" + tree + '\'' +
                '}';
    }
}
