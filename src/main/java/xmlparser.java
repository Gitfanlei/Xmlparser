import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

// org.w3c.dom jar 包也是可以解析XML的

public class xmlparser {

    public static ArrayList arrayList =new ArrayList();
    public static ArrayList dbList = new ArrayList();


/*
    public static void queryXml() {
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read("C:\\Users\\81949\\Desktop\\student.xml"); // 路径不要包含中文

            // 获取根元素
            Element root = doc.getRootElement();
            System.out.println("root element:" + root);

            // 构建一个list元素列表，获取root节点下面的子节点，注意 elements 方法，只能获取 子节点（不包含子节点的子节点）
            List<Element> list = root.elements();
            for (Element e : list) {
                System.out.println(e.getName());
            }
            // 获取指定节点下面的子节点
            Element node = root.element("Student");
            List<Element> studentlist = node.elements();

            for (Element e : studentlist) {
                arrayList.add(e.getName());
                System.out.println(e.getName() + ":" + e.getData());  // getname /getdata
            }
            if (arrayList != null) {
                dbList=arrayList;
                System.out.println(dbList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList getArrayList() {
        return dbList;
    }
*/


}
