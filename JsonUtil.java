package com.web.json;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.web.mode.ExecuteResult;
import com.web.mode.ModelFieldType;

public class JsonUtil {

	public static boolean fieldNameExists(String fieldName, String[] fieldNames) {
		// 默认返回全部字段
		if (fieldNames == null) {
			return true;
		}

		for (String fn : fieldNames) {
			if (fn.equals(fieldName)) {
				return true;
			}
		}
		return false;
	}

	public static String getFieldValue(Object object, Field field)
			throws Exception {
		// 调用 get 方法
		String fn = field.getName();
		String fieldName = fn.substring(0, 1).toUpperCase() + fn.substring(1); // 将属性的首字符大写，方便构造get，set方法
		// System.out.print(obj.getClass().getName() +
		// ",field name:" + fieldName);
		Object value = null;
		Method m = object.getClass().getMethod("get" + fieldName);
		if (m != null) {
			value = m.invoke(object);
		} else {
			value = field.get(object);
		}
		return value != null ? value.toString() : "";
	}

	public static StringBuffer getJSON(ArrayList list, String[] fieldNames) {
		StringBuffer json = new StringBuffer();
		int k = 0;
		// System.out.println("[");
		json.append("[\n");
		for (Object o : list) {
			if (ModelFieldType.getModelType(o.getClass().getSimpleName()) == ModelFieldType.MFT_STRING) {
				if (k > 0) {
					// System.out.print(",");
					json.append(",");
				}
				json.append("\"" + o.toString() + "\"");
			} else {
				if (k > 0) {
					// System.out.print(",");
					json.append(",\n");
				}
				json.append("{");
				json.append(JsonUtil._getJSON(o.getClass(), o, fieldNames));
				json.append("}");
			}
			k++;
		}
		json.append("\n]");
		// System.out.println("]");
		return json;
	}

	public static StringBuffer _getJSON(Class<?> cls, Object object,
			String[] fieldNames) {
		StringBuffer sb = new StringBuffer();

		if ("Object".equals(cls.getSimpleName())) {
			return sb;
		} else {
			sb.append(JsonUtil._getJSON(cls.getSuperclass(), object, fieldNames));
			try {
				Field[] fields = cls.getDeclaredFields();
				for (Field field : fields) {
 
//					System.out.println(cls.getSimpleName() + "="
//							+ field.getName() + "="
//							+ field.getType().getSimpleName());

					field.setAccessible(true);

					switch (ModelFieldType.getModelType(field.getType()
							.getSimpleName())) {
					case ModelFieldType.MFT_INT:
					case ModelFieldType.MFT_FLOAT:
					case ModelFieldType.MFT_LONG:
					case ModelFieldType.MFT_DOUBLE:
					case ModelFieldType.MFT_BYTE:
						if (JsonUtil.fieldNameExists(field.getName(),
								fieldNames)) {
							if (sb.length() > 0) {
								sb.append(",");
							}
							sb.append(String.format("\"%s\":%s",
									field.getName(),
									JsonUtil.getFieldValue(object, field)));
						}
						break;
					case ModelFieldType.MFT_ARRAYLIST:
						if (JsonUtil.fieldNameExists(field.getName(),
								fieldNames)) {
							if (sb.length() > 0) {
								sb.append(",");
							}
							ArrayList list = (ArrayList) field.get(object);
							sb.append(String.format("\"%s\":%s",
									field.getName(),
									JsonUtil.getJSON(list, fieldNames)));
						}
						break;
					case ModelFieldType.MFT_OBJECT:
						// sb.append(getJSON(field.get(object)));
						break;

					case ModelFieldType.MFT_STRING:
						if (JsonUtil.fieldNameExists(field.getName(),
								fieldNames)) {
							if (sb.length() > 0) {
								sb.append(",");
							}
							sb.append(String.format("\"%s\":\"%s\"",
									field.getName(),
									JsonUtil.getFieldValue(object, field)));
						}

						break;
					case ModelFieldType.MFT_DATE:
						break;

					default:
						// 自定义对象
						if (sb.length() > 0) {
							sb.append(",");
						}

						sb.append(String.format("\"%s\":%s", field.getName(),
								JsonUtil.getJSON(field.get(object), fieldNames)));

						break;
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

		return sb;
	}

	public static StringBuffer getJSON(Object object) {
		return JsonUtil.getJSON(object, null);
	}

	public static StringBuffer getJSON(Object object, String[] fieldNames) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(JsonUtil._getJSON(object.getClass(), object, fieldNames));
		sb.append("}");

		return sb;
	}

	public static StringBuffer getJSON(ArrayList list) {
		return JsonUtil.getJSON(list, null);
	}

	public static StringBuffer getEasyUIData(int total, ArrayList list) {
		return getEasyUIData(total, null, list);
	}

	public static StringBuffer getEasyUIData(int total, String[] fieldNames,
			ArrayList list) {
		StringBuffer json = new StringBuffer();

		json.append("{\"total\":" + total + ",");
		json.append("\"rows\":");
		// arrayToUpper(fieldNames);
		json.append(JsonUtil.getJSON(list, fieldNames));

		json.append("}");
		return json;
	}

	public static void main(String[] args) throws Exception {
		String[] fieldNames = { "result", "msg", "value1" };
		ExecuteResult item = new ExecuteResult("SUCCESS", "SUCCESS");
		StringBuffer sb = JsonUtil.getJSON(item, fieldNames);
		System.out.println(sb);
	}

}
