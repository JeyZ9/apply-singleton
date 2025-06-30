package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * {@code ProductSingleton} เป็นคลาสที่ใช้รูปแบบ Singleton
 * เพื่อจัดการข้อมูลผลิตภัณฑ์ และมีความสามารถในการเชื่อมต่อฐานข้อมูลเพื่อจัดการกับข้อมูลสินค้า
 *
 * <p>คลาสนี้เก็บข้อมูลสินค้าไว้ในตัวแปร instance และเชื่อมต่อฐานข้อมูล MySQL เพื่อเพิ่มหรือตรวจสอบชื่อสินค้า</p>
 *
 * <p>ติดตั้งกับฐานข้อมูล: <code>jdbc:mysql://localhost:3306/product_db</code></p>
 *
 * @author คุณ
 */
public class ProductSingleton implements Manageable {

    private static ProductSingleton instance = null;
    private String product_id;
    private String product_name;
    private int product_price;

    /**
     * กำหนดค่าข้อมูลของสินค้าให้กับ instance นี้
     *
     * @param p_id รหัสสินค้า
     * @param p_name ชื่อสินค้า
     * @param p_price ราคาสินค้า
     */
    public void setProduct(String p_id, String p_name, int p_price) {
        this.product_id = p_id;
        this.product_name = p_name;
        this.product_price = p_price;
    }

    /**
     * Constructor แบบ private เพื่อบังคับใช้ Singleton Pattern
     */
    private ProductSingleton(){}

    /**
     * คืน instance เดียวของ {@code ProductSingleton}
     *
     * @return instance เดียวของคลาสนี้
     */
    public static ProductSingleton getInstance() {
        if(instance == null) {
            instance = new ProductSingleton();
        }
        return instance;
    }

    /**
     * @return รหัสสินค้า (product_id)
     */
    public String getProductId() {
        return product_id;
    }

    /**
     * @return ชื่อสินค้า (product_name)
     */
    public String getProductName() {
        return product_name;
    }

    /**
     * @return ราคาสินค้า (product_price)
     */
    public int getProductPrice() {
        return product_price;
    }

    /**
     * แสดงข้อความ "Hello Singleton" ที่ console
     */
    public void sayHello() {
        System.out.println("Hello Singleton");
    }

    /**
     * แสดงข้อมูลของสินค้าใน console
     */
    public void showProduct() {
        System.out.println("productId: " + product_id +
                " productName: " + product_name +
                " productPrice: " + product_price);
    }

    /**
     * เพิ่มข้อมูลสินค้าใหม่เข้าไปในฐานข้อมูล
     *
     * @param pid รหัสสินค้า
     * @param pname ชื่อสินค้า
     * @param pprice ราคาสินค้า
     */
    public void insertProduct(String pid, String pname, int pprice) {
        Connection myConn = null;
        Statement myStmt = null;

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root" , "root");
            System.out.println("Database connection successful!\n");

            myStmt = myConn.createStatement();
            String sql = "INSERT INTO Product VALUES ('"+pid+"','"+pname+"',"+pprice+")";
            myStmt.executeUpdate(sql);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    /**
     * ดึงชื่อสินค้าจากฐานข้อมูลตามรหัสสินค้า
     *
     * @param pid รหัสสินค้า
     * @return รหัสสินค้าที่พบ (แม้ว่าควรจะ return ชื่อสินค้า)
     */
    public String getProductName(String pid) {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        String productName = "";

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root" , "root");
            System.out.println("Database connection successful!\n");

            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from product where p_id='"+pid+"'");

            while (myRs.next()) {
                productName = myRs.getString("p_id");
            }
            System.out.println("Print in method: " +  productName);

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return productName;
    }

    /**
     * ลบข้อมูลตาม ID (ยังไม่ implement จริง)
     *
     * @param id รหัสสินค้า
     * @return {@code false} เสมอในตอนนี้ (ยังไม่ทำงานจริง)
     */
    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
