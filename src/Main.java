import java.io.File;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

	public Main() {
		readExample();
	}
	private void readExample() {
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
					Element element=(Element) node;
					System.out.println(element.getTagName());
				}
			
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {

		new Main();
	}

}
