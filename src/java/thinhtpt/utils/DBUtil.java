/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ThinhTPT
 */
public class DBUtil implements Serializable {

    public static Connection makeConnection()
            throws NamingException, SQLException {
        Context currentContext = new InitialContext();
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");

        DataSource ds = (DataSource) tomcatContext.lookup("LABWEB_DS");
        Connection con = ds.getConnection();
        return con;
    }

    public static String setPreparedStamentForArray(int numParams, String columnName) {
        StringBuilder sb = new StringBuilder();
        if (numParams <= 0) {
            return sb.toString();
        }
        sb.append(" " + columnName + " IN (");
        for (int counter = 0; counter < numParams - 1; counter++) {
            sb.append("?, ");
        }
        sb.append("?) ");
        return sb.toString();
    }

}
