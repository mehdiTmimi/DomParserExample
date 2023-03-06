import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

public class Main2 {

	public Main2() {
		List<Student> students=new Vector<>();
		students.add(new Student());
		save(students, "asfd");
		List<Student> students2=load("data.xml");
		
		try {
			Method method = getClass().getDeclaredMethod("load",String.class);
			System.out.println(method.getReturnType());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public static void main(String[] args) {
		new Main2();
	}
	public  <T> List<T> load(Class<T> cls,  String path)
	{
		return null;
	}
	public static <T> boolean save(List<T> datas,String path )
	{
		
		System.out.println(datas.get(0).getClass().getSimpleName());
	}
}
