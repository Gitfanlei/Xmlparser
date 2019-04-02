import com.sun.org.apache.bcel.internal.generic.NEW;

import java.sql.*;
import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;

import com.sun.org.apache.xpath.internal.operations.Variable;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_EXCLUSIONPeer;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class mysqlconnect {
    private static final String URL = "jdbc:mysql://localhost:3306/resourcedb?serverTimezone=UTC";
    private static final String NAME = "root";
    private static final String PASSWORD = "1994";
    public static String firstname;

    public static ArrayList arrayList =new ArrayList();
    public static ArrayList dbList = new ArrayList();
    public static ArrayList dbValue = new ArrayList();
    public static String tableName="Materialsdb";


    //XML解析
    public static void queryXml() {
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read("file:\\"+"C:\\Users\\81949\\Desktop\\钛合金板材.xml"); // 路径不要包含中文  包含中文需添加file:\\表示本地文件

            // 获取根元素
            Element root = doc.getRootElement();
            System.out.println("root element:" + root+"\n");

            // 构建一个list元素列表，获取root节点下面的子节点，注意 elements 方法，只能获取子节点（不包含子节点的子节点）
            List<Element> list = root.elements();
            for (Element e : list) {

                Element node = root.element(e.getName());  // 根据每个root节点的子节点，获取节点下面的子节点

                List<Element> nodeList = node.elements();  // 获取每个子节点下的节点list集合
                for (Element b : nodeList) {               // 根据集合中的每个元素分别输出 节点的name and data;
                    arrayList.add(b.getName());
                    dbValue.add(b.getData());
                }
                System.out.println("\n");

            }
            if (arrayList != null && dbValue != null) {
                dbList = arrayList;
                System.out.println(dbList);
                System.out.println(dbValue + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 数据库创建部分
    public void SqlConnection() {
        // 驱动器启动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // new driver 类方法
        } catch (ClassNotFoundException e) {
            System.out.println("Can't start the Driver");
            e.printStackTrace();
        }

        // 连接命令
        Connection connection = null;

        //sql connect test
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);

            if (!connection.isClosed()) {
                System.out.println("Successful connected to DB!");

                // 启用指令声明
                System.out.println("Start sql query！！！");
                Statement statement = connection.createStatement();
                // sql order execute  Device名支持外部修改

                System.out.println(dbList+"\n");

                if (dbList.size() != 0) {
                    String sql;
                    String sqlorder = new String();
                    for (int i = 0; i < dbList.size(); i++) {
                        if (i == dbList.size() - 1) {
                            sqlorder += "" + dbList.get(i) + " VARCHAR(255) NOT NULL,";
                        } else {
                            sqlorder += "" + dbList.get(i) + " VARCHAR(255) NOT NULL,";
                        }
                    }

                    System.out.println(sqlorder + "\n");

                    // 建表

                    sql = "CREATE table IF NOT EXISTS " + tableName + "(" +
                            sqlorder +
                            "ID INT NOT NULL AUTO_INCREMENT," +
                            "PRIMARY KEY (ID)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
                    System.out.println(sql);

                /*sql = "create table IF NOT EXISTS DeviceDB(" +
                        "dbList.get(0) VARCHAR(255) NOT NULL," +
                        "Dnumber INT NOT NULL," +
                        "Rtype VARCHAR(255) NOT NULL," +
                        "Dbrand VARCHAR(255) NOT NULL," +
                        "Time VARCHAR(255)," +
                        "ID INT NOT NULL AUTO_INCREMENT," +
                        "PRIMARY KEY(ID)"+
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8";*/

                    // 更新
                    statement.executeUpdate(sql);
                    System.out.println("数据库表格创建完成!!" + "\n");
                } else {
                    System.out.println("无法读取文件内容！！！" + "\n");
                    System.exit(0);
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {


                // 数据写入
                Statement statement = connection.createStatement();

                // 转换表头格式
                String dblist = new String();
                for (int i = 0; i < dbList.size(); i++) {
                    if (i == dbList.size() - 1) {
                        dblist += dbList.get(i);
                    } else {
                        dblist += dbList.get(i)+",";
                    }
                }
                System.out.println(dblist);

                //转换插入数据的格式
                String dbvalue =new String();
                for (int i = 0; i < dbValue.size(); i++) {
                    if (i == dbValue.size() - 1) {
                        dbvalue += "\'" + dbValue.get(i) + "\'";
                    } else {
                        dbvalue += "\'" + dbValue.get(i) + "\',";
                    }
                }
                System.out.println(dbvalue);

                // 数据插入
                String sqlWrite = "insert into " + tableName + "(" + dblist + ") values (" + dbvalue + ")";
                System.out.println(sqlWrite);
                statement.executeUpdate(sqlWrite);
                System.out.println("数据插入完成！"+"\n");


            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    // 关闭数据库
                    if (connection != null) {
                        connection.close();

                        System.out.println("5"+2);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("SQL resource table complete!!!");
    }




}
