package mx.edu.utng.orders.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import mx.edu.utng.orders.model.Customer;
import mx.edu.utng.orders.model.Film;
import mx.edu.utng.orders.model.MethodPayment;
import mx.edu.utng.orders.model.Node;
import mx.edu.utng.orders.model.OrderDetail;
import mx.edu.utng.orders.model.OrderHeader;
import mx.edu.utng.orders.model.Product;
import mx.edu.utng.orders.model.Ticket;


public class DBOperations {
    private static OrderDB db;
    private static DBOperations operations;

  /*
    private static final String JOIN_ORDER_CUSTOMER_METHOD =
            "order_header " +
                    "INNER JOIN customer " +
                    "ON order_header.id_customer = customer.id " +
                    "INNER JOIN method_payment " +
                    "ON order_header.id_method_payment = method_payment.id";*/

    private final String[] orderProj = new String[]{
            OrderDB.Tables.ORDER_HEADER + "."
                    + OrderContract.OrderHeaders.ID,
            OrderContract.OrderHeaders.DATE,
            OrderContract.Customers.FIRSTNAME,
            OrderContract.Customers.LASTNAME,
            OrderContract.MethodsPayment.NAME};

    private DBOperations(){

    }

    public static DBOperations getDBOperations(
            Context context){
        if(operations==null) {
            operations = new DBOperations();
        }
        if(db==null){
            db = new OrderDB(context);
        }
        return operations;
    }
    //Operations of  Orders
    public Cursor getOrders(){
        SQLiteDatabase database = db.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
       // builder.setTables(JOIN_ORDER_CUSTOMER_METHOD);

        return  builder.query(database, orderProj,
                null, null, null, null, null);
    }

    public Cursor getOrderById(String id){
        SQLiteDatabase database = db.getWritableDatabase();
        String selection = String.format("%s=?",
                OrderContract.OrderHeaders.ID);
        String[] selectionArgs = {id};
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
       // builder.setTables(JOIN_ORDER_CUSTOMER_METHOD);
        String[] projection = {
                OrderDB.Tables.ORDER_HEADER+"."
                        + OrderContract.OrderHeaders.ID,
                OrderContract.OrderHeaders.DATE,
                OrderContract.Customers.FIRSTNAME,
                OrderContract.Customers.LASTNAME,
                OrderContract.MethodsPayment.NAME
        };
        return builder.query(database, projection, selection,
                selectionArgs, null, null, null);
    }

    public String insertOrderHeader(OrderHeader orderHeader){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        String idOrderHeader =
                OrderContract.OrderHeaders.generateIdOrderHeader();
        values.put(OrderContract.OrderHeaders.ID, idOrderHeader);
        values.put(OrderContract.OrderHeaders.DATE,
                orderHeader.getDate());
        values.put(OrderContract.OrderHeaders.ID_CUSTOMER,
                orderHeader.getIdCustomer());
        values.put(OrderContract.OrderHeaders.ID_METHOD_PAYMENT,
                orderHeader.getIdMethodPayment());

        database.insertOrThrow(OrderDB.Tables.ORDER_HEADER,
                null, values);
        return idOrderHeader;
    }

    public boolean updateOrderHeader(OrderHeader orderHeader){
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderHeaders.DATE,
                orderHeader.getDate());
        values.put(OrderContract.OrderHeaders.ID_CUSTOMER,
                orderHeader.getIdCustomer());
        values.put(OrderContract.OrderHeaders.ID_METHOD_PAYMENT,
                orderHeader.getIdMethodPayment());

        String whereClause = String.format("%s=?", OrderContract.OrderHeaders.ID);
        String[] whereArgs = {orderHeader.getIdOrderHeader()};

        int result = database.update(OrderDB.Tables.ORDER_HEADER, values, whereClause, whereArgs);
        return result>0;
    }

    public boolean deleteOrderHeader(String idOrderHeader){
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause =
                OrderContract.OrderHeaders.ID + "=?";
        String[] whereArgs = {idOrderHeader};

        int result =  database.delete(
                OrderDB.Tables.ORDER_HEADER, whereClause, whereArgs);
        return result > 0;
    }
    //Operations of  OrderDetails
    public Cursor getOrderDetails(){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",
                OrderDB.Tables.ORDER_DETAIL);
        return  database.rawQuery(sql, null);
    }

    public Cursor getOrderDetailById(String id){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s" +
                        " WHERE %s=?", OrderDB.Tables.ORDER_DETAIL,
                OrderContract.OrderHeaders.ID);
        String[] whereArgs = {id};
        return database.rawQuery(sql, whereArgs);
    }

    public String insertOrderDetail(OrderDetail orderDetail){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderDetails.ID,
                orderDetail.getIdOrderHeader());
        values.put(OrderContract.OrderDetails.SEQUENCE,
                orderDetail.getSequence());
        values.put(OrderContract.OrderDetails.ID_PRODUCT,
                orderDetail.getIdProduct());
        values.put(OrderContract.OrderDetails.QUANTITY,
                orderDetail.getQuantity());
        values.put(OrderContract.OrderDetails.PRICE,
                orderDetail.getPrice());

        database.insertOrThrow(OrderDB.Tables.ORDER_DETAIL,
                null, values);
        return String.format("%s#%d",
                orderDetail.getIdOrderHeader(),
                orderDetail.getSequence());
    }

    public boolean updateOrderDetail(OrderDetail orderDetail){
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderDetails.SEQUENCE,
                orderDetail.getSequence());
        values.put(OrderContract.OrderDetails.QUANTITY,
                orderDetail.getQuantity());
        values.put(OrderContract.OrderDetails.PRICE,
                orderDetail.getPrice());

        String whereClause = String.format("%s=? AND %s=?",
                OrderContract.OrderDetails.ID,
                OrderContract.OrderDetails.SEQUENCE);
        String[] whereArgs = {orderDetail.getIdOrderHeader(),
                String.valueOf(orderDetail.getSequence())};

        int result = database.update(OrderDB.Tables.ORDER_DETAIL,
                values, whereClause, whereArgs);
        return result>0;
    }

    public boolean deleteOrderDetail(String idOrderDetail){
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause =
                OrderContract.OrderHeaders.ID + "=?";
        String[] whereArgs = {idOrderDetail};

        int result =  database.delete(
                OrderDB.Tables.ORDER_DETAIL, whereClause, whereArgs);
        return result > 0;
    }
    //Operations of  Products
    public Cursor getProducts(){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",
                OrderDB.Tables.PRODUCT);
        return  database.rawQuery(sql, null);
    }

    public Cursor getProductById(String id){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s" +
                        " WHERE %s=?",
                OrderDB.Tables.PRODUCT,
                OrderContract.Products.ID);
        String[] whereArgs = {id};
        return database.rawQuery(sql, whereArgs);
    }

    public String insertProduct(Product product){
        SQLiteDatabase database = db.getWritableDatabase();
        String idProduct = OrderContract.Products.generateIdProduct();
        ContentValues values = new ContentValues();
        values.put(OrderContract.Products.ID, idProduct);
        values.put(OrderContract.Products.NAME, product.getName());
        values.put(OrderContract.Products.PRICE, product.getPrice());
        values.put(OrderContract.Products.STOCK, product.getStock());
        database.insertOrThrow(OrderDB.Tables.PRODUCT, null, values);
        return idProduct;
    }

    public boolean updateProduct(Product product){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderContract.Products.NAME, product.getName());
        values.put(OrderContract.Products.PRICE, product.getPrice());
        values.put(OrderContract.Products.STOCK, product.getStock());
        String whereClause = String.format("%s=?", OrderContract.Products.ID);
        String[] whereArgs = {product.getIdProduct()};
        int result = database.update(OrderDB.Tables.PRODUCT,
                values, whereClause, whereArgs);
        return result>0;
    }

    public boolean deleteProduct(String idProduct){
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = OrderContract.Products.ID + "=?";
        String[] whereArgs = {idProduct};
        int result =  database.delete(OrderDB.Tables.PRODUCT,
                whereClause, whereArgs);
        return result > 0;
    }

    /******CUSTOMER******/


    // Operations Customers
    public Cursor getCustomers() {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",
                OrderDB.Tables.CUSTOMER);
        return database.rawQuery(sql, null);
    }
    public Cursor getCustomersById(String idCustomer) {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                OrderDB.Tables.CUSTOMER,
                OrderContract.Customers.ID);
        String[] whereArgs ={idCustomer};
        return database.rawQuery(sql, whereArgs);
    }
/***********TODOS LOS CAMPOS*********/
    public String insertCustomer(Customer customer) {
        SQLiteDatabase database = db.getWritableDatabase();
        String idCustomer = OrderContract.Customers.generateIdCustomer();
        ContentValues values = new ContentValues();
        values.put(OrderContract.Customers.ID, idCustomer);
        values.put(OrderContract.Customers.FIRSTNAME, customer.getFirstname());
        values.put(OrderContract.Customers.LASTNAME, customer.getLastname());
        values.put(OrderContract.Customers.PHONE, customer.getPhone());
        values.put(OrderContract.Customers.EMAIL, customer.getEmail());
        values.put(OrderContract.Customers.STREET, customer.getStreet());
        values.put(OrderContract.Customers.ZIP, customer.getZip());
        values.put(OrderContract.Customers.CITY, customer.getCity());
        values.put(OrderContract.Customers.COUNTRY, customer.getCountry());
        values.put(OrderContract.Customers.DATEREG, customer.getDatereg());
        values.put(OrderContract.Customers.DISCOUNT, customer.getDiscount());
        values.put(OrderContract.Customers.STATUS, customer.getStatus());

        return database.insertOrThrow(OrderDB.Tables.CUSTOMER,
                null, values) > 0 ? idCustomer : null;
    }

    public boolean updateCustomer(Customer customer) {
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderContract.Customers.FIRSTNAME,
                customer.getFirstname());
        values.put(OrderContract.Customers.LASTNAME,
                customer.getLastname());
        values.put(OrderContract.Customers.PHONE,
                customer.getPhone());
        values.put(OrderContract.Customers.EMAIL, customer.getEmail());
        values.put(OrderContract.Customers.STREET, customer.getStreet());
        values.put(OrderContract.Customers.ZIP, customer.getZip());
        values.put(OrderContract.Customers.CITY, customer.getCity());
        values.put(OrderContract.Customers.COUNTRY, customer.getCountry());
        values.put(OrderContract.Customers.DATEREG, customer.getDatereg());
        values.put(OrderContract.Customers.DISCOUNT, customer.getDiscount());
        values.put(OrderContract.Customers.STATUS, customer.getStatus());



        String whereClause = String.format("%s=?",
                OrderContract.Customers.ID);
        final String[] whereArgs = {customer.getIdCustomer()};
        int result = database.update(OrderDB.Tables.CUSTOMER,
                values, whereClause, whereArgs);
        return result > 0;
    }
    public boolean deleteCustomer(String idCustomer) {
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = String.format("%s=?",
                OrderContract.Customers.ID);
        final String[] whereArgs = {idCustomer};
        int result = database.delete(OrderDB.Tables.CUSTOMER, whereClause, whereArgs);
        return result > 0;
    }
/******************************/
    // Operation Method of payment
    public Cursor getMethodsPayment() {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",
                OrderDB.Tables.METHOD_PAYMENT);
        return database.rawQuery(sql, null);
    }

    public Cursor getMethodsPaymentById(String idMethodPayment) {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                OrderDB.Tables.METHOD_PAYMENT,
                OrderContract.MethodsPayment.ID);
        String[] whereArgs = {idMethodPayment};
        return database.rawQuery(sql, null);
    }
    public String insertMethodPayment(MethodPayment methodPayment) {
        SQLiteDatabase database = db.getWritableDatabase();

        String idMethodPayment = OrderContract.MethodsPayment.generateIdMethodPayment();

        ContentValues values = new ContentValues();
        values.put(OrderContract.MethodsPayment.ID, idMethodPayment);
        values.put(OrderContract.MethodsPayment.NAME, methodPayment.getName());

        return database.insertOrThrow(
                OrderDB.Tables.METHOD_PAYMENT, null,
                values) > 0 ? idMethodPayment : null;
    }

    public boolean updateMethodPayment(MethodPayment methodPayment) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderContract.MethodsPayment.NAME,
                methodPayment.getName());
        String whereClause = String.format("%s=?",
                OrderContract.MethodsPayment.ID);
        String[] whereArgs = {methodPayment.getIdMethodPayment()};
        int result = database.update(
                OrderDB.Tables.METHOD_PAYMENT, values,
                whereClause, whereArgs);
        return result > 0;
    }

    public boolean deleteMethodPayment(String idMethodPayment) {
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = String.format("%s=?", OrderContract.MethodsPayment.ID);
        String[] whereArgs = {idMethodPayment};
        int result = database.delete(OrderDB.Tables.METHOD_PAYMENT, whereClause, whereArgs);
        return result > 0;
    }
    /*********TICKET OPERATIONS**********/

    public Cursor getTickets(){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT" +
                " * FROM %s",
                OrderDB.Tables.TICKET);
        return  database.rawQuery(sql, null);
    }

    public Cursor getTicketById(String id){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s" +
                        " WHERE %s=?",
                OrderDB.Tables.TICKET,
                OrderContract.Tickets.ID);
        String[] whereArgs = {id};
        return database.rawQuery(sql, whereArgs);
    }

    public String insertTicket(Ticket ticket){
        SQLiteDatabase database = db.getWritableDatabase();
        String idTicket = OrderContract.Tickets.generateIdTicket();
        ContentValues values = new ContentValues();

        values.put(OrderContract.Tickets.ID,idTicket);
        values.put(OrderContract.Tickets.NAME,ticket.getName());
        values.put(OrderContract.Tickets.PHONE,ticket.getPhone());
        values.put(OrderContract.Tickets.FOLIO,ticket.getFolio());

        database.insertOrThrow(OrderDB.Tables.TICKET, null, values);
        return idTicket;
    }

    public boolean updateTicket(Ticket ticket){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(OrderContract.Tickets.NAME,ticket.getName());
        values.put(OrderContract.Tickets.PHONE,ticket.getPhone());
        values.put(OrderContract.Tickets.FOLIO,ticket.getFolio());

        String whereClause = String.format("%s=?", OrderContract.Tickets.ID);
        String[] whereArgs = {ticket.getIdTicket()};
        int result = database.update(OrderDB.Tables.TICKET,
                values, whereClause, whereArgs);
        return result>0;
    }

    public boolean deleteTicket(String idTicket){
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = OrderContract.Tickets.ID + "=?";
        String[] whereArgs = {idTicket};
        int result =  database.delete(OrderDB.Tables.TICKET,
                whereClause, whereArgs);
        return result > 0;
    }

    /***********NODE**************/


    public Cursor getNodes(){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT" +
                        " * FROM %s",
                OrderDB.Tables.NODE);
        return  database.rawQuery(sql, null);
    }

    public Cursor getNodeById(String id){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s" +
                        " WHERE %s=?",
                OrderDB.Tables.NODE,
                OrderContract.Nodes.ID);
        String[] whereArgs = {id};
        return database.rawQuery(sql, whereArgs);
    }

    public String insertNodes(Node node){
        SQLiteDatabase database = db.getWritableDatabase();
        String idNode = OrderContract.Nodes.generateIdNode();
        ContentValues values = new ContentValues();

        values.put(OrderContract.Nodes.ID,idNode);
        values.put(OrderContract.Nodes.ASSERTIO,node.getAssertio());
        values.put(OrderContract.Nodes.NAME,node.getName());
        values.put(OrderContract.Nodes.TREE,node.getTree());

        database.insertOrThrow(OrderDB.Tables.NODE, null, values);
        return idNode;
    }

    public boolean updateNode(Node node){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(OrderContract.Nodes.ASSERTIO,node.getAssertio());
        values.put(OrderContract.Nodes.NAME,node.getName());
        values.put(OrderContract.Nodes.TREE,node.getTree());


        String whereClause = String.format("%s=?", OrderContract.Nodes.ID);
        String[] whereArgs = {node.getIdNode()};
        int result = database.update(OrderDB.Tables.NODE,
                values, whereClause, whereArgs);
        return result>0;
    }

    public boolean deleteNode(String idNode){
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = OrderContract.Nodes.ID + "=?";
        String[] whereArgs = {idNode};
        int result =  database.delete(OrderDB.Tables.NODE,
                whereClause, whereArgs);
        return result > 0;
    }

/***********************FILM*****************/
public Cursor getFilms() {
    SQLiteDatabase database = db.getReadableDatabase();
    String sql = String.format("SELECT * FROM %s",
            OrderDB.Tables.FILM);
    return database.rawQuery(sql, null);
}
    public Cursor getFilmsById(String idFilm) {
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                OrderDB.Tables.FILM,
                OrderContract.Film.ID);
        String[] whereArgs ={idFilm};
        return database.rawQuery(sql, whereArgs);
    }
    /***********TODOS LOS CAMPOS*********/
    public String insertFilm(Film film) {
        SQLiteDatabase database = db.getWritableDatabase();
        String idFilm = OrderContract.Film.generateIdFilm();
        ContentValues values = new ContentValues();
        values.put(OrderContract.Film.ID, idFilm);
        values.put(OrderContract.Film.TITLE, film.getTitle());
        values.put(OrderContract.Film.DESCRIPTION, film.getDescription());
        values.put(OrderContract.Film.RELEASE_YEAR, film.getReleaseYear());
        values.put(OrderContract.Film.LANGUAGE, film.getLanguage());
        values.put(OrderContract.Film.RENTAL_DURATION, film.getRentalDuration());
        values.put(OrderContract.Film.RENTAL_RATE, film.getRentalRate());
        values.put(OrderContract.Film.LENGTH, film.getLenght());
        values.put(OrderContract.Film.REPLACEMENT_COST, film.getReplacementCost());
        values.put(OrderContract.Film.RATING, film.getRating());
        values.put(OrderContract.Film.LAST_UPDATE, film.getLastUpdate());
        values.put(OrderContract.Film.SPECIAL_FEATURES, film.getSpecialFeatures());
        values.put(OrderContract.Film.FULLTEXT, film.getFulltext());


        return database.insertOrThrow(OrderDB.Tables.FILM,
                null, values) > 0 ? idFilm : null;
    }

    public boolean updateFilm(Film film) {
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderContract.Film.TITLE, film.getTitle());
        values.put(OrderContract.Film.DESCRIPTION, film.getDescription());
        values.put(OrderContract.Film.RELEASE_YEAR, film.getReleaseYear());
        values.put(OrderContract.Film.LANGUAGE, film.getLanguage());
        values.put(OrderContract.Film.RENTAL_DURATION, film.getRentalDuration());
        values.put(OrderContract.Film.RENTAL_RATE, film.getRentalRate());
        values.put(OrderContract.Film.LENGTH, film.getLenght());
        values.put(OrderContract.Film.REPLACEMENT_COST, film.getReplacementCost());
        values.put(OrderContract.Film.RATING, film.getRating());
        values.put(OrderContract.Film.LAST_UPDATE, film.getLastUpdate());
        values.put(OrderContract.Film.SPECIAL_FEATURES, film.getSpecialFeatures());
        values.put(OrderContract.Film.FULLTEXT, film.getFulltext());


        String whereClause = String.format("%s=?",
                OrderContract.Film.ID);
        final String[] whereArgs = {film.getIdFilm()};
        int result = database.update(OrderDB.Tables.FILM,
                values, whereClause, whereArgs);
        return result > 0;
    }
    public boolean deleteFilm(String idFilm) {
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = String.format("%s=?",
                OrderContract.Film.ID);
        final String[] whereArgs = {idFilm};
        int result = database.delete(OrderDB.Tables.FILM, whereClause, whereArgs);
        return result > 0;
    }







    /**************PROPERTY***********
    //Operations of  Propertie
    public Cursor getPropertie(){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",
                OrderDB.Tables.PROPER_TIE);
        return  database.rawQuery(sql, null);
    }

    public Cursor getPropertyById(String id){
        SQLiteDatabase database = db.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s" +
                        " WHERE %s=?",
                OrderDB.Tables.PROPER_TIE,
                OrderContract.Propertie.ID);
        String[] whereArgs = {id};
        return database.rawQuery(sql, whereArgs);
    }***/

/*
    public String insertProperty(Propertie propertie){
        SQLiteDatabase database = db.getWritableDatabase();
        String idPropertie = OrderContract.Propertie.generateIdPropertie();
        ContentValues values = new ContentValues();
        values.put(OrderContract.DeptEmps.ID, idDeptEmp);
        values.put(OrderContract.DeptEmps.FROMDATE, deptEmp.getFromDate());
        values.put(OrderContract.DeptEmps.TODATE, deptEmp.getToDate());
        values.put(OrderContract.DeptEmps.EMPNO, deptEmp.getEmpNo());
        database.insertOrThrow(OrderDB.Tables.DEPT_EMP, null, values);
        return idDeptEmp;
    }


    public boolean updatePropertie(Propertie propertie){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderContract.DeptEmps.FROMDATE, propertie.getFromDate());
        values.put(OrderContract.DeptEmps.TODATE, propertie.getToDate());
        values.put(OrderContract.DeptEmps.EMPNO, propertie.getEmpNo());
        String whereClause = String.format("%s=?", OrderContract.DeptEmps.ID);
        String[] whereArgs = {propertie.getIdDep()};
        int result = database.update(OrderDB.Tables.DEPT_EMP,
                values, whereClause, whereArgs);
        return result>0;
    }

    public boolean deletePropertie(String idDepEmp) {
        SQLiteDatabase database = db.getWritableDatabase();
        String whereClause = OrderContract.DeptEmps.ID + "=?";
        String[] whereArgs = {idDepEmp};
        int result = database.delete(OrderDB.Tables.DEPT_EMP,
                whereClause, whereArgs);
        return result > 0;
    }
*/
    /***************CUSTOMER****************/

    public SQLiteDatabase getDb() {
        return db.getWritableDatabase();
    }



}
