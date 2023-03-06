import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
		//TODO
		saveExample(students,"data2.xml");
	}
	private void saveExample(List<Student> students, String path) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Element racine = document.createElement("students");
			document.appendChild(racine);
			for (Student student : students) {
				Element elementS = document.createElement("student");
				racine.appendChild(elementS);
				
				Element elementName=document.createElement("name");
				Element elementSubject=document.createElement("subject");
				Element elementGender=document.createElement("gender");
				Element elementAge=document.createElement("age");
				
				elementS.appendChild(elementName);
				elementS.appendChild(elementSubject);
				elementS.appendChild(elementGender);
				elementS.appendChild(elementAge);
				
				elementName.setTextContent(student.getName());
				elementSubject.setTextContent(student.getSubject());
				elementGender.setTextContent(student.getGender());
				elementAge.setTextContent(student.getAge()+"");
			}
			
			// ecriture dans un fichier
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
			
		}catch (Exception e) {
			e.printStackTrace();
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
