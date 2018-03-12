import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	static ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-rpc.xml");
	
	public static void main(String[] args) {
		Object bean = applicationContext.getBean("test");	
		System.out.println(bean);
	}
}
