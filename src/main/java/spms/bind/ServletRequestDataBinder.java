package spms.bind;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Set;

/**
 * Created by wayne on 2017. 3. 19..
 *
 */
public class ServletRequestDataBinder {

	public static Object bind(HttpServletRequest request, Class<?> dataType, String dataName) throws Exception {
		if (isPrimitiveType(dataType)) {
			return createValueObject(dataType, request.getParameter(dataName));
		}

		Set<String> paramNames = request.getParameterMap().keySet();
		Object dataObject = dataType.newInstance();
		Method m;

		for (String paramName : paramNames) {
			m = findSetter(dataType, paramName);

			if (m != null) {
				m.invoke(dataObject, createValueObject(m.getParameterTypes()[0], request.getParameter(paramName)));
			}
		}
		return dataObject;
	}

	private static Method findSetter(Class<?> dataType, String name) {
		Method[] methods = dataType.getMethods();
		String propName;

		for (Method m : methods) {
			if (!m.getName().startsWith("set")) continue;
			propName = m.getName().substring(3);
			if (propName.toLowerCase().equals(name.toLowerCase())) {
				return m;
			}
		}
		return null;
	}

	private static Object createValueObject(Class<?> dataType, String value) {
		if (dataType.getName().equals("int") || dataType == Integer.class) {
			return new Integer(value);
		}

		if (dataType.getName().equals("long") || dataType == Long.class) {
			return new Long(value);
		}

		if (dataType.getName().equals("double") || dataType == Double.class) {
			return new Double(value);
		}

		if (dataType.getName().equals("float") || dataType == Float.class) {
			return new Float(value);
		}

		if (dataType.getName().equals("boolean") || dataType == Boolean.class) {
			return Boolean.valueOf(value);
		}

		if (dataType == Date.class) {
			// FIXME
			return java.sql.Date.valueOf(value);
		}

		return value;
	}

	private static boolean isPrimitiveType(Class<?> dataType) {
		return dataType.getName().equals("int") || dataType == Integer.class ||
			dataType.getName().equals("long") || dataType == Long.class ||
			dataType.getName().equals("float") || dataType == Float.class ||
			dataType.getName().equals("double") || dataType == Double.class ||
			dataType.getName().equals("boolean") || dataType == Boolean.class ||
			dataType == Date.class || dataType == String.class;
	}
}
