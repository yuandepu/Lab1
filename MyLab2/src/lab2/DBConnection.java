package lab2;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;

public class DBConnection  
{  
    private String dbDriver="com.mysql.jdbc.Driver";   
    private String dbUrl="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_mylab2?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private String dbUser="kj11jjm50m";  
    private String dbPass="4yylm0hzxiw4lhk2yj04x5k5w4l3m43yykk0zjji";  
    public Connection getConn()  
    {  
        Connection conn=null;  
        try  
        {  
            Class.forName(dbDriver).newInstance();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        try  
        {  
            conn = DriverManager.getConnection(dbUrl,dbUser,dbPass);  
        }  
        catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
        return conn;  
    }

public int insert(String sql)  
{  
    int i=0;  
    Connection cnn=getConn();  

    try{  
        PreparedStatement preStmt =cnn.prepareStatement(sql);  
        i=preStmt.executeUpdate();  
    }  
    catch (SQLException e)  
    {  
        e.printStackTrace();  
    }  
    return i;//1为执行成功  
}  
public int update(String sql)
{  
    int i=0;  
    Connection cnn=getConn();  
    try{  
        PreparedStatement preStmt =cnn.prepareStatement(sql);  
        i=preStmt.executeUpdate();  
    }  
    catch (SQLException e)  
    {  
        e.printStackTrace();  
    }  
    return i;//1为执行成功  
}  
public List<String> select(String sql)
{  
    int i;
    Connection cnn = getConn();
    List<String> list = new LinkedList<String>();
    try  
    {  
        Statement stmt = cnn.createStatement();  
        ResultSet rs = stmt.executeQuery(sql);  
        while(rs.next())  
        {  
            for(i = 1;i<=rs.getMetaData().getColumnCount();i++){
            list.add(rs.getString(i));}
        }  
        //可以将查找到的值写入类，然后返回相应的对象  
    }  
    catch (Exception e)  
    {  
        e.printStackTrace();  
    }  
    return list;  
}  
public int delete(String sql)  
{  
    int i=0;  
    Connection conn = getConn();
    try  
    {  
        Statement stmt = conn.createStatement();  
        i = stmt.executeUpdate(sql);  
    }  
    catch (SQLException e)  
    {  
        e.printStackTrace();  
    }  
    return i;//如果返回的是1，则执行成功;  
}  
}