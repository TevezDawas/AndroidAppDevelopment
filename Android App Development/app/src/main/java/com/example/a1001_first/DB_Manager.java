package com.example.a1001_first;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DB_Manager extends SQLiteOpenHelper {



    private final static String DB_NAME = "DB_1";
    private final static int DB_VER = 1;
    ArrayList<Company> CCompanyies = new ArrayList<>();
    ArrayList<Coupon> CCoupons = new ArrayList<>();
    ArrayList<Customer> ACustomers =  new ArrayList<>();
    HashMap<Customer, Coupon> CustomersAndCoupons = new HashMap<>();

    HashMap<Company, Coupon> CCompanyAndCoupons = new HashMap<>();
    Company company2;
    private final static String TBL_COMPANYS = "companys";
    private final static String ID = "id";
    private final static String EMAIL = "email";
    private final static String NAME = "name";
    private final static String PASSWORD = "password";
    private final static String CREATE_TABLE_COMPANYS =
            "CREATE TABLE IF NOT EXISTS " + TBL_COMPANYS +
                    " (" + ID + " text primary key, " +
                    EMAIL + " text  UNIQUE, " +
                    NAME + " text UNIQUE, " +
                    PASSWORD + " text)";

    private final static String TBL_COUPONS = "coupons";
    private final static String IDCoupon = "idcoupon";
    private final static String Companyid = "companyid";
    private final static String category = "catgory";
    private final static String Title = "title";
    private final static String Description = "description";
    private final static String Startdate = "startdate";
    private final static String Enddate = "enddate";
    private final static String Price = "price";
    private final static String Amount = "amount";
    private final static String IMAGE = "image";

    private final static String CREATE_TABLE_COUPONS =
            "CREATE TABLE IF NOT EXISTS " + TBL_COUPONS +
                    " (" + IDCoupon + " text, " +
                    Companyid + " text, " +
                    category + " text  primary key, " +
                    Title + " text, " + Description
                    + Startdate + " text, " + Enddate + " text, " +
                    Price + " text, " + Amount + " text, " + IMAGE + " text, " +
                    " text)";

    private final static String TBL_CUSTOMERS = "customers";
    private final static String IDCoustmoer = "id";
    private final static String FirstName = "firstname";
    private final static String LastName = "lastname";
    private final static String Email = "email";
    private final static String Password = "password";
    private final static String CREATE_TABLE_TBL_CUSTOMERS =
            "CREATE TABLE IF NOT EXISTS " + TBL_CUSTOMERS +
                    " (" + IDCoustmoer + " text , " +
                    FirstName + " text , " +
                    LastName + " text, " +
                    Email + " text primary key, " +
                    Password + " text)";


    private final static String TBL_CUSTOMERANDCOUPON = "CustomerAndCoupon";
    private final static String IDCustomer = "idcu";
    private final static String IDCouponn = "idco";

    private final static String CREATE_TABLE_TBL_CUSTOMERANDCOUPON =
            "CREATE TABLE IF NOT EXISTS " + TBL_CUSTOMERANDCOUPON +
                    " (" + IDCustomer + " text primary key, " +
                    IDCouponn + " text, " +
                    " text)";


    private final static String TBL_COMPANY_AND_COUPON = "CompanyAndCoupon";
    private final static String IDCompanyy = "idco";
    private final static String IDCouponCC = "idcco";

    private final static String CREATE_TABLE_TBL_COMPANY_AND_COUPON =
            "CREATE TABLE IF NOT EXISTS " + TBL_COMPANY_AND_COUPON +
                    " (" + IDCompanyy+ "text primary key, " +
                    IDCouponCC + " text , " +
                    " text)";

//...Singleton.........................................

    private static DB_Manager instance = null;

    private DB_Manager(Context context) {
        super(context, DB_NAME, null, DB_VER);
        try {


        } catch (Exception e) {
            throw e;
        }
    }

    public static DB_Manager getInstance(Context context) {
        if (instance == null) instance = new DB_Manager(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COMPANYS);
        db.execSQL(CREATE_TABLE_COUPONS);
        db.execSQL(CREATE_TABLE_TBL_CUSTOMERS);
        db.execSQL(CREATE_TABLE_TBL_CUSTOMERANDCOUPON);
        db.execSQL(CREATE_TABLE_TBL_COMPANY_AND_COUPON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        db2.execSQL("DROP TABLE IF EXISTS " + TBL_COMPANYS);
        db2.execSQL("DROP TABLE IF EXISTS " + TBL_COUPONS);
        db2.execSQL("DROP TABLE IF EXISTS " + TBL_CUSTOMERS);
        db2.execSQL("DROP TABLE IF EXISTS " + TBL_CUSTOMERANDCOUPON);
        db2.execSQL("DROP TABLE IF EXISTS " + TBL_COMPANY_AND_COUPON);
        onCreate(db2);
    }
//...Singleton.........................................

    public ArrayList<Company> getCompanys() {


        String[] fields = {ID, EMAIL, NAME, PASSWORD};
        String id, email, name, password;

        try {
            Cursor cr = getCursor(TBL_COMPANYS, fields, null);
            if (cr.moveToFirst())
                do {
                    id = cr.getString(0);
                    email = cr.getString(1);
                    name = cr.getString(2);
                    password = cr.getString(3);

                    CCompanyies.add(new Company(id, email, name, password));
                } while (cr.moveToNext());
            return CCompanyies;
        } catch (Exception e) {
            throw e;
        }
    }

    public Company getCompany(String e) {
        for (Company company : getCompanys()) {
            if (e.equals(company.getEmail()))
                return company;
        }
        return null;
    }

    public ArrayList<Coupon> getCoupons() {

        String[] fields = {IDCoupon, Companyid, category, Title,Description,Startdate,Enddate,Price,Amount,IMAGE};
        String idcoupon, companyid, category, title,Description,startdate,enddate,image;
        int amount;
        double price;

        try {
            Cursor cr = getCursor(TBL_COUPONS, fields, null);
            if (cr.moveToFirst())
                do {
                    idcoupon = cr.getString(0);
                    companyid = cr.getString(1);
                    category = cr.getString(2);
                    title = cr.getString(3);
                    Description = cr.getString(4);
                    startdate = cr.getString(5);
                    enddate = cr.getString(6);
                    amount = cr.getInt(7);
                    price = cr.getDouble(8);
                    image = cr.getString(9);

                    CCoupons.add(new Coupon(idcoupon, companyid, category, title,Description,startdate,enddate,amount,price,image));
                } while (cr.moveToNext());
            return CCoupons;
        } catch (Exception e) {
            throw e;
        }
    }

    private Coupon getcoupon(Coupon e) {
        for (Coupon coupon : CCoupons) {
            if (e.getId().equals(coupon.getId()))
                return e;
        }
        return null;
    }

    public ArrayList<Coupon> getCCoupons() {

        return CCoupons;
    }

    public ArrayList<Customer> getCustomers() {


        String[] fields = {IDCoustmoer, FirstName, LastName, Email,Password};
        String id, firstname, lastname, email,password;

        try {
            Cursor cr = getCursor(TBL_CUSTOMERS, fields, null);
            if (cr.moveToFirst())
                do {
                    id = cr.getString(0);
                    firstname = cr.getString(1);
                    lastname = cr.getString(2);
                    email = cr.getString(3);
                    password = cr.getString(4);

                    ACustomers.add(new Customer(id, firstname, lastname, email,password));
                } while (cr.moveToNext());
            return ACustomers;
        } catch (Exception e) {
            throw e;
        }
    }

    public Customer getCustomer(String e) {
        for (Customer customer : getCustomers()) {
            if (e.equals(customer.getEMAIL()))
                return customer;
        }
        return null;
    }

    public ArrayList<Customer> getAACustomers() {

        return ACustomers;
    }


    public HashMap<Customer, Coupon> getCustomersAndCoupons() {
        HashMap<Customer, Coupon> CustomersAndCoupons = new HashMap<Customer, Coupon>();

        String[] fields = {IDCustomer, IDCouponn};
        String idcu, idco;

        try {
            Cursor cr = getCursor(TBL_COMPANYS, fields, null);
            if (cr.moveToFirst())
                do {
                    idcu = cr.getString(0);
                    idco = cr.getString(1);

                    CustomersAndCoupons.put(new Customer(idcu, null, null, null, null), new Coupon(idco, null, null, null, null, null, null, 0, null, null));
                } while (cr.moveToNext());
            return CustomersAndCoupons;
        } catch (Exception e) {
            throw e;
        }
    }

    public Coupon getCouponByTitle(String e) {
        for (Coupon coupon : CCoupons) {
            if (e.equals(coupon.getTitle()))
                return coupon;
        }
        return null;
    }


    public HashMap<Company, Coupon> getCompanysAndCoupons() {

        String[] fields = {IDCompanyy, IDCouponCC};
        String idco, idcco;
        Company company;
        Coupon coupon;

        try {
            Cursor cr = getCursor(TBL_COMPANY_AND_COUPON, fields, null);
            if (cr.moveToFirst())
                do {
                    idco = cr.getString(0);
                    idcco = cr.getString(1);

                    company = new Company(idco, " ", " ", " ");
                    coupon = new Coupon(idcco, " ", " ", " ", " ", " ", " ", 12, 1.00, " ");
                    CCompanyAndCoupons.put(company, coupon);

                } while (cr.moveToNext());
            return CCompanyAndCoupons;
        } catch (Exception e) {
            throw e;
        }
    }


    public HashMap<Company,Coupon> gethashCC() {

        return CCompanyAndCoupons;
    }
    public HashMap<Customer, Coupon> gethashCustomerCoupons() {

        return CustomersAndCoupons;
    }


    //...ADD.........................................
    public void addNewCompany(Company e) throws DataExists{
        if (!companyExists(e)) {
            CCompanyies.add(e);

            ContentValues cv = new ContentValues();
            cv.put(ID, e.getId());
            cv.put(EMAIL, e.getEmail());
            cv.put(NAME, e.getName());
            cv.put(PASSWORD, e.getPassword());


            SQLiteDatabase db = getWritableDatabase();
            db.insert(TBL_COMPANYS, null, cv);
        }
        else
            throw new DataExists("This Company already exists !");
    }

    private boolean companyExists(Company e) {
        for (Company company : CCompanyies) {
            if (e.getEmail().equals(company.getEmail()) )
                return true;
        }

        return false;
    }
    public boolean companyExists2(String e) {
        for (Company company : CCompanyies) {
            if (e.equals(company.getName()) )
                return true;
        }

        return false;
    }

    public void addNewCoupon(Coupon e) throws DataExists{
        if (!CouponExists(e)) {
            CCoupons.add(e);

            ContentValues cv = new ContentValues();
            cv.put(IDCoupon, e.getId());
            cv.put(Companyid, e.getCompany());
            cv.put(category, e.getCategory());
            cv.put(Title, e.getTitle());
            cv.put(Description, e.getDescription());
            cv.put(Startdate, e.getStartDate());
            cv.put(Enddate, e.getEndDate());
            cv.put(Price, e.getPRICE());
            cv.put(Amount, e.getAMOUNT());

            SQLiteDatabase db = getWritableDatabase();
            db.insert(TBL_COUPONS, null, cv);
        }
        else
            throw new DataExists("This Coupons already exists !");
    }

    private boolean CouponExists(Coupon e) {
        for (Coupon coupon : CCoupons ) {
            if(e.getTitle().equals(coupon.getTitle()))
                return true;
        }
        return false;
    }
    public boolean CouponExists2(String e) {
        for (Company c : CCompanyies ) {
            for (Coupon coupon : CCoupons) {
                String name = ("Company: " + "" + c.getName() + " , " + "Coupon: " + " " + coupon.getTitle());
                if (e.equals(name))
                    return true;
            }
        }
        return false;
    }


    public void addNewCustomer(Customer e) throws DataExists{
        if (!CustomerExists(e)) {
            ACustomers.add(e);

            ContentValues cv = new ContentValues();
            cv.put(IDCoustmoer, e.getId());
            cv.put(FirstName, e.getFIRST_NAME());
            cv.put(LastName, e.getLAST_NAME());
            cv.put(Email, e.getEMAIL());
            cv.put(Password, e.getPASSWORD());

            SQLiteDatabase db = getWritableDatabase();
            db.insert(TBL_CUSTOMERS, null, cv);
        }
        else
            throw new DataExists("This Company already exists !");
    }

    private boolean CustomerExists(Customer e) {
        for (Customer customer : ACustomers ) {
            if(e.getEMAIL().equals(customer.getEMAIL()))
                return true;
        }
        return false;
    }

    public boolean CustomerExists2(String e) {
        for (Customer customer : ACustomers ) {
            if(e.equals(customer.getFIRST_NAME()+" "+customer.getLAST_NAME()))
                return true;
        }
        return false;
    }

    public void addNewCustomerCoupon(Customer e1 , Coupon e2) throws DataExists {
        if (!CustomerAndCouponExists(e1,e2)) {
            CustomersAndCoupons.put(e1,e2);

            ContentValues cv = new ContentValues();
            cv.put(EMAIL, e1.getEMAIL());
            cv.put(Title, e2.getTitle());

            SQLiteDatabase db = getWritableDatabase();
            db.insert(TBL_CUSTOMERANDCOUPON, null, cv);
        } else
            throw new DataExists("This Company already exists !");
    }

    private boolean CustomerAndCouponExists(Customer e1 ,Coupon e2) {
        for (Customer customer : CustomersAndCoupons.keySet()) {
            Coupon coupon = CustomersAndCoupons.get(customer);
            if (e1.getEMAIL().equals(customer.getEMAIL()) &&e2.getTitle().equals(coupon.getTitle()) ) {
                return true;
            }
        }
        return false;
    }

    private Coupon getcouponfromcustomer(Customer e) {
        for (Customer customer : CustomersAndCoupons.keySet()) {
            if (e.getId().equals(customer.getId())) {
                return CustomersAndCoupons.get(customer);
            }
        }
        return null;
    }




    public void addNewCouponforCompany(Company e1 , Coupon e2) throws DataExists {
        if (!CompanyAndCouponExists(e1,e2)) {
            CCompanyAndCoupons.put(e1,e2);

            ContentValues cv = new ContentValues();
            cv.put(IDCompanyy, e1.getId());
            cv.put(IDCouponn, e2.getId());

            SQLiteDatabase db = getWritableDatabase();
            db.insert(TBL_COMPANY_AND_COUPON, null, cv);
        } else
            throw new DataExists("This Company already exists !");
    }

    private boolean CompanyAndCouponExists(Company e1 ,Coupon e2) {
        for (Company company : CCompanyAndCoupons.keySet()) {
            Coupon coupon = CCompanyAndCoupons.get(company);
            if (e1.getEmail().equals(company.getEmail()) && e2.getTitle().equals(coupon.getTitle()) ) {
                return true;
            }
        }
        return false;
    }

    private Coupon getcouponfromCompany(Company e) {
        for (Company company : CCompanyAndCoupons.keySet()) {
            if (e.getId().equals(company.getId())) {
                return CustomersAndCoupons.get(company);
            }
        }
        return null;
    }



   /* private Customer getCustomerFromCoupon(Coupon e) {
        for (Customer customer : CustomersAndCoupons.keySet()) {
            Coupon coupon = CustomersAndCoupons.get(customer);     for Compant And Coupons
            if (coupon != null && e.getId().equals(coupon.getId())) {
                return customer;
            }
        }
        return null;
    }*/
    //...UPDATE.........................................



    public void UpdateCompany(Company e , String name,String email,String password) throws DataExists {
        if (companyExists(e)) {
            for(Company company : getCompanys())
            {
                if(e.getEmail().equals(company.getEmail()))
                {
                    company.setId(" ");
                    company.setEmail(email);
                    company.setName(name);
                    company.setPassword(password);

                }
            }

            ContentValues cv = new ContentValues();
            cv.put(ID, " ");
            cv.put(EMAIL, email);
            cv.put(NAME, name);
            cv.put(PASSWORD, password);

            SQLiteDatabase db = getWritableDatabase();
            db.update(TBL_COMPANYS, cv,  EMAIL + "=" + e.getEmail().toString(), null);
        }
        else
            throw new DataExists("Employee not exists !");

    }

    public void UpdateCoupon(Coupon e) throws DataExists {
        if (!CouponExists(e)) {
            for(Coupon Coupon : CCoupons)
            {
                if(e.getId().equals(Coupon.getId()))
                {
                    Coupon.setId(e.getId());
                    Coupon.setCompany(e.getCompany());
                    Coupon.setCategory(e.getCategory());
                    Coupon.setTitle(e.getTitle());
                    Coupon.setDescription(e.getDescription());
                    Coupon.setStartDate(e.getStartDate());
                    Coupon.setEndDate(e.getEndDate());
                    Coupon.setAMOUNT(e.getAMOUNT());
                    Coupon.setPRICE(e.getPRICE());
                    Coupon.setIMAGE(e.getIMAGE());


                }
            }

            ContentValues cv = new ContentValues();

            cv.put(Companyid, e.getId());
            cv.put(category, e.getCategory());
            cv.put(Title, e.getTitle());
            cv.put(Description, e.getDescription());
            cv.put(Startdate, e.getStartDate());
            cv.put(Enddate, e.getEndDate());
            cv.put(Amount, e.getAMOUNT());
            cv.put(Price, e.getPRICE());
            cv.put(IMAGE, e.getIMAGE());

            SQLiteDatabase db = getWritableDatabase();
            db.update(TBL_COUPONS, cv,  ID + "=" + e.getId().toString(), null);
        }
        else
            throw new DataExists("Coupon not exists !");
    }

    public void UpdateCustomer(Customer e ,String fullname,String email , String password) throws DataExists {
        if (CustomerExists(e)) {
            for(Customer customer : getCustomers())
            {
                if(e.getId().equals(customer.getId()))
                {
                    customer.setId(e.getId());
                    customer.setFIRST_NAME(fullname);
                    customer.setLAST_NAME(" ");
                    customer.setEMAIL(email);
                    customer.setPASSWORD(password);

                }
            }

            ContentValues cv = new ContentValues();
            cv.put(IDCoustmoer, e.getId());
            cv.put(FirstName, fullname);
            cv.put(LastName, " ");
            cv.put(Email, email);
            cv.put(Password, password);

            SQLiteDatabase db = getWritableDatabase();
            db.update(TBL_CUSTOMERS, cv,  EMAIL + "=" + e.getEMAIL() , null);
        }
        else
            throw new DataExists("Customer not exists !");

    }

    public void UpdateCustomerAndCoupon(Customer e1,Coupon e2) throws DataExists {
        if (CustomerAndCouponExists(e1,e2)) {
            for (Customer customer : CustomersAndCoupons.keySet()) {
                Coupon coupon = CustomersAndCoupons.get(customer);
                if (e1.getId().equals(customer.getId()) && e2.getId().equals(coupon) ) {
                    customer.setId(e1.getId());
                    coupon.setId(e2.getId());
                }
            }

            ContentValues cv = new ContentValues();
            cv.put(ID, e1.getId());
            cv.put(EMAIL, e2.getId());


            SQLiteDatabase db = getWritableDatabase();
            db.update(TBL_CUSTOMERANDCOUPON, cv, IDCoustmoer + "=" + e1.getId() + " AND " + IDCoupon + "=" + e2.getId(), null);

        } else
            throw new DataExists("Employee not exists !");

    }


    //...DELET.........................................
    public void getdeleteCompany(Company e) throws DataExists {
        if (companyExists(e)) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(TBL_COMPANYS, EMAIL + "=?", new String[]{e.getEmail()});
          //  e.setName(" ");
          //  e.setEmail(" ");

        } else {
            throw new DataExists("Company with ID " + e.getId() + " does not exist!");
        }
    }

    public void deleteCoupon(Coupon e) throws DataExists {
        if (CouponExists(e)) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(TBL_COUPONS, Title + "=?", new String[]{e.getId()});

        } else {
            throw new DataExists("Coupon with ID " + e.getTitle() + " does not exist!");
        }
    }

    public void getdeleteCustomer(Customer e)throws DataExists {
        if (CustomerExists(e)) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(TBL_CUSTOMERS, EMAIL + "=?", new String[]{e.getEMAIL()});
           // e.setFIRST_NAME(" ");
            //e.setLAST_NAME(" ");
           // e.setEMAIL(" ");

        } else {
            throw new DataExists("Customer with ID " + e.getId() + " does not exist!");
        }
    }


    public void deleteCustomerCoupon(Customer e1, Coupon e2) throws DataExists {
        if (CustomerAndCouponExists(e1, e2)) {
            CustomersAndCoupons.remove(e1, e2);

        } else {
            throw new DataExists("This combination of Customer and Coupon does not exist!");
        }
    }


    public void deleteComapnyCoupon(Company e1, Coupon e2) throws DataExists {
        if (CompanyAndCouponExists(e1, e2)) {
            CCompanyAndCoupons.remove(e1, e2);

        } else {
            throw new DataExists("This combination of Customer and Coupon does not exist!");
        }
    }


    private Cursor getCursor(String tableName, String[] fields, String where) {

        String strQry = "SELECT ";
        for (int i = 0; i < fields.length; i++) {
            strQry += fields[i] + " ";
            if (i < fields.length - 1)
                strQry += ",";
        }
        strQry += " FROM " + tableName;
        if (where != null && !where.isEmpty())
            strQry += " " + where;

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cr = db.rawQuery(strQry, null);
            return cr;
        } catch (Exception e) {
            throw e;
        }
    }


}


