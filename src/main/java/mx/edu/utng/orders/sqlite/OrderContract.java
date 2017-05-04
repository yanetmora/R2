package mx.edu.utng.orders.sqlite;

import java.util.UUID;



public class OrderContract {
    //nombres de las tablas
    interface OrderHeaderColumns{
        String ID = "id";
        String DATE = "record_date";
        String ID_CUSTOMER = "id_customer";
        String ID_METHOD_PAYMENT = "id_method_payment";
    }

    interface OrderDetailColumns{
        String ID = "id";
        String SEQUENCE = "sequence";
        String ID_PRODUCT = "id_product";
        String QUANTITY = "quantity";
        String PRICE = "price";
    }

    interface ProductColumns{
        String ID = "id";
        String NAME = "name";
        String PRICE = "price";
        String STOCK = "stock";
    }
            /**CUSTOMER **/
    interface  CustomerColumns{
        String ID = "id";
        String FIRSTNAME = "firstname";
        String LASTNAME = "lastname";
        String PHONE = "phone";
        String EMAIL="email";
        String STREET="street";
        String ZIP="zip";
        String CITY="city";
        String COUNTRY="country";
        String DATEREG="datereg";
        String DISCOUNT="discount";
        String STATUS="status";
    }

    interface MethodPaymentColumns{
        String ID = "id";
        String NAME = "name";
    }
    /************** TICKET ********/
    interface  TicketColumns{
        String ID="id";
        String NAME="name";
        String PHONE="phone";
        String FOLIO="folio";
    }
    interface NodeColumns{
        String ID="id";
        String ASSERTIO="assertio";
        String NAME="name";
        String TREE="tree";
    }
/*
    interface EstadoColumns{
        String IDESTADO="id";
        String DESCRIPCION="descripcion";
        String NUMEROHABITANTES="numerohabitantes";
        String PLATILLO="platillo";
        String TRAJE="traje";
        String DENSIDAD="densidadpoblacion";

    }*/


    interface  FilmColumns{
        String ID = "id";
        String TITLE = "title";
        String DESCRIPTION = "description";
        String RELEASE_YEAR = "realase_year";
        String LANGUAGE="language";
        String RENTAL_DURATION="rental_duration";
        String RENTAL_RATE="rental_rate";
        String LENGTH="rengh";
        String REPLACEMENT_COST="replacement_cost";
        String RATING="rating";
        String LAST_UPDATE="last_update";
        String SPECIAL_FEATURES="special_features";
        String FULLTEXT="fulltext";
    }


    /***PROPERTY
    interface PropertieColums{

        String  ID ="id";
        String  COMPANY_NAME= "compan_yname";
        String  CONTAC_NAME= "contact_name";
        String  CONTACT_TITLE = "contac_ttitle";
        String  ADDRESS= "address";
        String  CITY= "city";
        String  REGION = "region";
        String  POSTAL_CODE ="postal_code";
        String  COUNTRY= "phone";
        String  PHONE= "phone";
        String  FAX= "fax";

    }**/

    public static class OrderHeaders implements OrderHeaderColumns{
        public static String generateIdOrderHeader(){
            return  "OH-"+ UUID.randomUUID().toString();
        }
    }

    public static class OrderDetails implements OrderDetailColumns{

    }

    public static class Products implements ProductColumns{
        public static String generateIdProduct(){
            return  "PR-"+ UUID.randomUUID().toString();
        }
    }
/****CUSTOMER ID generar*/
    public static class Customers implements CustomerColumns{
        public static String generateIdCustomer(){
            return  "CU-"+ UUID.randomUUID().toString();
        }
    }

    public static class MethodsPayment implements MethodPaymentColumns{
        public static String generateIdMethodPayment(){
            return  "MP-"+ UUID.randomUUID().toString();
        }
    }
    /***Generar id TICKET********************/
    public static  class Tickets implements TicketColumns{
        public static  String generateIdTicket(){
            return  "TK-"+UUID.randomUUID().toString();
        }
    }
    public static  class Nodes implements NodeColumns{
        public static  String generateIdNode(){
            return  "NO-"+UUID.randomUUID().toString();
        }
    }
/*
    public static  class Estado implements EstadoColumns{
        public static  String generateIdEstado(){
            return  "ES-"+UUID.randomUUID().toString();
        }
    }*/

    public static class Film implements FilmColumns {
        public static String generateIdFilm() {
            return "FL-" + UUID.randomUUID().toString();
        }
    }
    /***PROPERTY

    public static class Propertie implements PropertieColums{
        public static String generateIdPropertie(){
            return  "PR-"+ UUID.randomUUID().toString();
        }
    }**/
    private OrderContract() {

    }
}
