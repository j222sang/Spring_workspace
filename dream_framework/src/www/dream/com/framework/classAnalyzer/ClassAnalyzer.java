package www.dream.com.framework.classAnalyzer;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**
 * 어떤 클래스가 주어지면 한계통으로 쭉 올라가서 계속 검사하는것
 * @author kosmo_10
 *
 */
public class ClassAnalyzer {
	// 정보가 담겨있는거 //정보가 담겨
	public static List<AccessibleObject> findFeatureByAnnotation(Class targetClass, Class targetAnno) {
		// Annotation이 달려있는 변수나 메소드를 찾아서 합친것을 반환해줄 리스트
		List<AccessibleObject> ret = new ArrayList<>();
		findFeatureByAnnotation(targetClass, targetAnno, ret);
		return ret;
	}

	// AccessibleObject : Field, Method 두개가 공통으로 사용할 수 있는 속성
	private static void findFeatureByAnnotation(Class targetClass, Class targetAnno, List<AccessibleObject> list) {
		try {
			// 내가 찾고싶은 클래스의 필드들을 가져옴
			Field[] fields = targetClass.getDeclaredFields();
			// 찾은 field에 대하여 반복문을 돈다
			for (Field field : fields) {
				// field중 targetAnno(내가 찾고 싶은 annotation)이 달린것을 찾아 anno에 저장한다
				Annotation anno = field.getAnnotation(targetAnno);
				// 해당필드위에 annotaion 이달려 있는가
				if (anno != null) {
					// 반환할 ret값에 annotation이 달려있는 변수를 추가해준다.
					list.add(field);
				}
			}

			Method[] methods = targetClass.getDeclaredMethods();
			for (Method method : methods) {
				// 해당필드위에 annotaion 이달려 있는가
				Annotation anno = method.getAnnotation(targetAnno);

				if (anno != null) {
					list.add(method);
				}
			}
		} catch (Exception e) {
		}
		
		/** 만약 내 위에 상위클래스가 정의되어 있다면 */
		Class targetSuper = targetClass.getSuperclass();
		// 모든 클래스의 최고 클래스는 object클래스이다
		if (targetSuper != Object.class) {
			findFeatureByAnnotation(targetSuper, targetAnno, list);
		}
	}

}
