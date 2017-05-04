package mx.edu.utng.orders.model;


public class MethodPayment {
    private String idMethodPayment;
    private String name;

    public MethodPayment(String idMethodPayment, String name) {
        this.idMethodPayment = idMethodPayment;
        this.name = name;
    }
    public MethodPayment(){
        this("","");
    }

    public String getIdMethodPayment() {
        return idMethodPayment;
    }

    public void setIdMethodPayment(String idMethodPayment) {
        this.idMethodPayment = idMethodPayment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MethodPayment{" +
                "idMethodPayment='" + idMethodPayment + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
