package com.example.a1001_first;

import android.content.Context;

public class Login {
//tevez
    private static  final String adminUN = "ADMIN";
    private static  final String adminPass = "admin";
    private static  final String customerUN = "CUSTOMER";
    private static  final String customerPass = "customer";
    private static Context context;

    private static Login instance = null;
    private Login(){}

    public static Login getInstance(Context ctx)
    {
        context = ctx;
        if(instance == null) instance = new Login();
        return instance;
    }

    public Object signIn(String userName, String password, UserType userType) {
        switch (userType) {
            case ADMIN:
                if (userName.equals(adminUN) &&
                        password.equals(adminPass))
                    return true;
                return null;
            case CUSTOMER:

                Customer customer = DB_Manager
                        .getInstance(context.getApplicationContext())
                        .getCustomer(userName);

                if (password.equals(customer.getPASSWORD()))
                    return customer;
                if (customer == null) return null;
                else
                    return null;

            case COMPANY:
                Company company = DB_Manager
                        .getInstance(context.getApplicationContext())
                        .getCompany(userName);
                if (company == null) return null;
                if (password.equals(company.getPassword()))
                    return company;
                else
                    return null;
            default:
                return null;
        }
    }
}