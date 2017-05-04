package mx.edu.utng.orders.sqlite;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

import mx.edu.utng.orders.model.OrderDetail;



public class OrderDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "order.db";
    private static final int CURRENT_VERSION = 1;
    private final Context context;

    interface Tables{
        String ORDER_HEADER = "order_header";
        String ORDER_DETAIL = "order_detail";
        String PRODUCT = "product";
        /***Tabla Customer**/
        String CUSTOMER = "customer";
        String METHOD_PAYMENT = "method_payment";
        /**1**/
        String TICKET="ticket";
        String NODE="node";

        String   PROPER_TIE = "propertie";

        /***TABLA ESTADO
        String  ESTADO = "estado";**/

        String FILM="film";
    }

    interface References{
        String ID_ORDER_HEADER =
                String.format("REFERENCES %s(%s)" +
                                " ON DELETE CASCADE",
                        Tables.ORDER_HEADER,
                        OrderContract.OrderHeaders.ID);
        String ID_PRODUCT =
                String.format("REFERENCES %s(%s)" +
                                " ON DELETE CASCADE",
                        Tables.PRODUCT,
                        OrderContract.Products.ID);
        /***CUSTOMER**/
        String ID_CUSTOMER =
                String.format("REFERENCES %s(%s)" +
                                " ON DELETE CASCADE",
                        Tables.CUSTOMER,
                        OrderContract.Customers.ID);

        String ID_METHOD_PAYMENT =
                String.format("REFERENCES %s(%s)" +
                                " ON DELETE CASCADE",
                        Tables.METHOD_PAYMENT,
                        OrderContract.MethodsPayment.ID);
/*
        String ID_PROPERTIE =
                String.format("REFERENCES %s(%s)" +
                                " ON DELETE CASCADE",
                        Tables.PROPER_TIE,
                        OrderContract.DeptEmps.ID);**/

    }

    public OrderDB(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_VERSION);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                db.setForeignKeyConstraintsEnabled(true);
            }else{
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format(
                "CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT UNIQUE NOT NULL, %s DATETIME NOT NULL," +
                        "%s TEXT NOT NULL %s, %s TEXT NOT NULL %s)",
                Tables.ORDER_HEADER, BaseColumns._ID,
                OrderContract.OrderHeaders.ID,
                OrderContract.OrderHeaders.DATE,
                OrderContract.OrderHeaders.ID_CUSTOMER,
                References.ID_CUSTOMER,
                OrderContract.OrderHeaders.ID_METHOD_PAYMENT,
                References.ID_METHOD_PAYMENT));

        db.execSQL(String.format(
                "CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL %s," +
                        " %s INTEGER NOT NULL CHECK (%s>0)," +
                        "%s INTEGER NOT NULL %s, %s INTEGER NOT NULL, " +
                        "%s REAL NOT NULL, UNIQUE(%s, %s))",

                Tables.ORDER_DETAIL,
                BaseColumns._ID,
                OrderContract.OrderDetails.ID,
                References.ID_ORDER_HEADER,
                OrderContract.OrderDetails.SEQUENCE,
                OrderContract.OrderDetails.SEQUENCE,
                OrderContract.OrderDetails.ID_PRODUCT,
                References.ID_PRODUCT,
                OrderContract.OrderDetails.QUANTITY,
                OrderContract.OrderDetails.PRICE,
                OrderContract.OrderDetails.ID,
                OrderContract.OrderDetails.SEQUENCE));



        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY" +
                        " AUTOINCREMENT, %s TEXT NOT NULL UNIQUE, %s TEXT NOT NULL," +
                        " %s REAL NOT NULL, %s INTEGER NOT NULL CHECK(%s>=0))",
                Tables.PRODUCT, BaseColumns._ID,
                OrderContract.Products.ID,
                OrderContract.Products.NAME,
                OrderContract.Products.PRICE,
                OrderContract.Products.STOCK,
                OrderContract.Products.STOCK));
/************CUSTOMER TABLA*******************/
        db.execSQL(String.format("CREATE TABLE %s( %s INTEGER PRIMARY KEY" +
                        " AUTOINCREMENT, %s TEXT NOT NULL UNIQUE, %s TEXT" +
                        " NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL," +
                        "%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL" +
                         ",%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL)",
                Tables.CUSTOMER,
                BaseColumns._ID,
                OrderContract.Customers.ID,
                OrderContract.Customers.FIRSTNAME,
                OrderContract.Customers.LASTNAME,
                OrderContract.Customers.PHONE,
                OrderContract.Customers.EMAIL,
                OrderContract.Customers.STREET,
                OrderContract.Customers.ZIP,
                OrderContract.Customers.CITY,
                OrderContract.Customers.COUNTRY,
                OrderContract.Customers.DATEREG,
                OrderContract.Customers.DISCOUNT,
                OrderContract.Customers.STATUS));

/***********AQUI TERMINA ***********/
        db.execSQL(String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY" +
                        " AUTOINCREMENT, %s TEXT NOT NULL UNIQUE, %s TEXT NOT NULL)",
                Tables.METHOD_PAYMENT,
                BaseColumns._ID,
                OrderContract.MethodsPayment.ID,
                OrderContract.MethodsPayment.NAME));

        /*****************TICKET***************/
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY" +
                        " AUTOINCREMENT, %s TEXT NOT NULL UNIQUE, %s TEXT NOT NULL," +
                        " %s INTEGER NOT NULL,%s INTEGER NOT NULL)",
                Tables.TICKET,
                BaseColumns._ID,
                OrderContract.Tickets.ID,
                OrderContract.Tickets.NAME,
                OrderContract.Tickets.PHONE,
                OrderContract.Tickets.FOLIO
             ));

        db.execSQL(String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY" +
                        " AUTOINCREMENT, %s TEXT NOT NULL UNIQUE, %s TEXT NOT NULL," +
                        " %s TEXT NOT NULL,%s TEXT NOT NULL)",
                Tables.NODE,
                BaseColumns._ID,
                OrderContract.Nodes.ID,
                OrderContract.Nodes.ASSERTIO,
                OrderContract.Nodes.NAME,
                OrderContract.Nodes.TREE
        ));
        db.execSQL(String.format("CREATE TABLE %s( %s INTEGER PRIMARY KEY" +
                        " AUTOINCREMENT, %s TEXT NOT NULL UNIQUE, %s TEXT" +
                        " NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL," +
                        "%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL" +
                        ",%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL)",
                Tables.FILM,
                BaseColumns._ID,
                OrderContract.Film.ID,
                OrderContract.Film.TITLE,
                OrderContract.Film.DESCRIPTION,
                OrderContract.Film.RELEASE_YEAR,
                OrderContract.Film.LANGUAGE,
                OrderContract.Film.RENTAL_DURATION,
                OrderContract.Film.RENTAL_RATE,
                OrderContract.Film.LENGTH,
                OrderContract.Film.REPLACEMENT_COST,
                OrderContract.Film.RATING,
                OrderContract.Film.LAST_UPDATE,
                OrderContract.Film.SPECIAL_FEATURES,
                OrderContract.Film.FULLTEXT));


        /************ESTADO TABLA**************
        db.execSQL(String.format("CREATE TABLE %s( %s INTEGER PRIMARY KEY" +
                        " AUTOINCREMENT, %s TEXT NOT NULL UNIQUE, %s TEXT" +
                        " NOT NULL, %s INTEGER NOT NULL, %s TEXT NOT NULL," +
                        "%s TEXT NOT NULL,%s INTEGER NOT NULL)",
                Tables.ESTADO,
                BaseColumns._ID,
                OrderContract.Estado.IDESTADO,
                OrderContract.Estado.DESCRIPCION,
                OrderContract.Estado.NUMEROHABITANTES,
                OrderContract.Estado.PLATILLO,
                OrderContract.Estado.TRAJE,
                OrderContract.Estado.DENSIDAD));******/


        /**PROPERTY
        db.execSQL(String.format("CREATE TABLE %s" +
                        "(%s INTEGER PRIMARY KEY" + " AUTOINCREMENT, " +
                        "%s TEXT NOT NULL UNIQUE, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL)",

                Tables.PROPER_TIE,
                BaseColumns._ID,
                OrderContract.Propertie.ID,
                OrderContract.Propertie.COMPANY_NAME,
                OrderContract.Propertie.CONTAC_NAME,
                OrderContract.Propertie.CONTACT_TITLE,
                OrderContract.Propertie.ADDRESS,
                OrderContract.Propertie.CITY,
                OrderContract.Propertie.REGION,
                OrderContract.Propertie.POSTAL_CODE,
                OrderContract.Propertie.COUNTRY,
                OrderContract.Propertie.PHONE,
                OrderContract.Propertie.FAX));

         **/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Tables.ORDER_HEADER);
        db.execSQL("DROP TABLE IF EXISTS "+Tables.ORDER_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS "+Tables.PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS "+Tables.METHOD_PAYMENT);
        /**********TABLA*****/
        db.execSQL("DROP TABLE IF EXISTS "+Tables.CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS "+Tables.TICKET);
        db.execSQL("DROP TABLE IF EXISTS "+Tables.NODE);
        db.execSQL("DROP TABLE IF EXISTS "+Tables.FILM);
        db.execSQL("DROP TABLE IF EXISTS "+Tables.PROPER_TIE);
        /**********************************************ESTADO*************
        db.execSQL("DROP TABLE IF EXISTS "+Tables.ESTADO);***/
        onCreate(db);

    }

}
