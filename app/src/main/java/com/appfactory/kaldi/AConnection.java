package com.appfactory.kaldi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AConnection
{
    public static void main(String[] args)
    {
        Connection c = RetrieveConnection();
    }

    private static Connection con = null;
    public static Connection RetrieveConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://kaldi-database.cluster-c6ft8jt7lsb0.us-west-2.rds.amazonaws.com:3306/user", "admin", "Gihadi8g");
            System.out.println("hi");
        }
        catch(Exception e){System.out.println(e);}
        return con;
    }
}