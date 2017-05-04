package mx.edu.utng.orders.model;


public class Ticket {
    private String idTicket;
    private String name;
    private int phone;
    private int folio;

    public Ticket(String idTicket, String name, int phone, int folio) {
        this.idTicket = idTicket;
        this.name = name;
        this.phone = phone;
        this.folio = folio;

    }
    public Ticket(){
        this("","",0,0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket='" + idTicket + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", folio=" + folio +
                '}';
    }
}
