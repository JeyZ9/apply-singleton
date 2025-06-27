package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductSingleton {
    private static ProductSingleton instance = null;
    private String product_id;
    private String product_name;
    private int product_price;

    public void setProduct(String p_id, String p_name, int p_price) {
        this.product_id = p_id;
        this.product_name = p_name;
        this.product_price = p_price;
    }

    private ProductSingleton(){}

    public static ProductSingleton getInstance() {
        if(instance == null) {
            instance = new ProductSingleton();
        }
        return instance;
    }

    public String getProductId() {
        return product_id;
    }

    public String getProductName() {
        return product_name;
    }

    public int getProductPrice() {
        return product_price;
    }

    public void sayHello() {
        System.out.println("Hello Singleton");
    }

    public void showProduct() {
        System.out.println("productId: " + product_id + "productName: " + product_name + "productPrice: " + product_price);
    }

    public void insertProduct(String pid, String pname, int pprice) {
        Connection myConn = null;
        Statement myStmt = null;
//		ResultSet myRs = null;
//		String getProductId="";
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root" , "root");

            System.out.println("Database connection successful!\n");

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            String sql = "INSERT INTO Product " +
                    "VALUES ('"+pid+"','"+pname+"',"+pprice+")";
            myStmt.executeUpdate(sql);


            // 4. Process the result set
//			while (myRs.next()) {
//				System.out.println(myRs.getString("p_id") + ", " + myRs.getString("p_name")+ ", " + myRs.getInt("p_price"));
//				getProductId = myRs.getString("p_id");
//				System.out.println(getProductId);
//			}
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    public String getProductName(String pid) {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        String productName="";
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root" , "root");

            System.out.println("Database connection successful!\n");

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from product where p_id='"+pid+"'");

            // 4. Process the result set
            while (myRs.next()) {
                productName = myRs.getString("p_id");
            }
            System.out.println("Print in method: " +  productName);

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

//        System.out.println("productName: " + productName);
        return productName;
    }


}
