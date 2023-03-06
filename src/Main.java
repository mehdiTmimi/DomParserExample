import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

	public Main() {
		List<Student> students= readExample();
		for (Student student : students) {
			System.out.println(student);
		}
	}
	private List<Student> readExample() {
		List<Student> students=new Vector<>();
		try {
			File file = new File("data.xml");  
			
			DocumentBuilderFactory factory =
			DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document document = builder.parse(file);
			
			Element root = document.getDocumentElement();
				
			System.out.println(root.getTagName()+" "+root.getNodeType());
			
			NodeList childs = root.getChildNodes();
			
			for (int i = 0; i < childs.getLength(); i++) {
				Node node= childs.item(i);
				if(node.getNodeType()==1)
				{
					Student student=new Student();
					students.add(student);
					
					Element element=(Element) node;
					Element nameElement=(Element) element.getElementsByTagName("name").item(0);
					student.setName(nameElement.getTextContent());
					
					Element subjectElement=(Element) element.getElementsByTagName("subject").item(0);
					student.setSubject(subjectElement.getTextContent());
					
					Element genderElement=(Element) element.getElementsByTagName("gender").item(0);
					student.setGender(genderElement.getTextContent());
					
					Element ageElement=(Element) element.getElementsByTagName("age").item(0);
					student.setAge(Integer.parseInt(ageElement.getTextContent()));
				}
			
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return students;
	}
	public static void main(String[] args) {

		new Main();
	}

}
