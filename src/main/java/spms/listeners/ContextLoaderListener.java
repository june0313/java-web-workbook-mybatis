package spms.listeners;

// 서버에서 제공하는 DataSource 사용하기

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import spms.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
			String propertiesPath = getPropertiesPath(event);

			applicationContext = new ApplicationContext();
			applicationContext.addBeans("sqlSessionFactory", sqlSessionFactory);
			applicationContext.prepareObjectsByProperties(propertiesPath);
			applicationContext.prepareObjectsByAnnotation("");
			applicationContext.injectDependency();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private String getPropertiesPath(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		return sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
	}

	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}
