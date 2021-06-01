package www.dream.com.framework.langPosAnalyzer;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import www.dream.com.framework.classAnalyzer.ClassAnalyzer;
import www.dream.com.framework.test.ContactPoint;

/**
 * 품사 분석기가 정의한 Annotation을 달아 놓은 객체의 속성에 들어있는 정보를 Komoran을 활용하여 품사분석하고 해쉬태그로 활용할
 * 만한 단어들이 몇번 사용되었는지 까지를 ComparablePair의 List로 반환할 것임. 품사 : 2
 */
public class PosAnalyzer {
	private static Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);

	public static Map<String, Integer> getHashTags(Object obj) {
		// 출력에 나올 Map을 담는 배열
		Map<String, Integer> ret = new HashMap<>();
		// annotation이 달려있는 것들을 검사해서 분석기 의 역할
		// obj = 넘겨준 post , 결과를 담을 ret
		getHashTags(obj, ret);
		return ret;
	}

	// 넘겨받은 obj = 넘겨준 post , 결과를 담을 ret을 가지고 있음
	private static void getHashTags(Object obj, Map<String, Integer> map) {
		// 내가 찾고 싶은 @HashTag 달려있는것들을 모두 찾은 집합 //넘겨받은 post의 클래스, HashTarget
		List<AccessibleObject> listFeature = ClassAnalyzer.findFeatureByAnnotation(obj.getClass(), HashTarget.class);
		// @HashTag 이 달려 있는 field와 method의 집합인 listFeature반복문을 돈다
		for (AccessibleObject ao : listFeature) {
			// 내부의 원소가 Field형이라면
			if (ao instanceof Field) {
				Field field = (Field) (ao);
				Class type = field.getType();
				// field에 들어있는 annotation을 가져오는데 어떤 annotation이냐? @HashTarget이 들어있는 annotation
				Annotation anno = type.getAnnotation(HashTarget.class);
				// field의 type이 String이라면
				if (type == String.class) {
					try {
						// setAccessible()은 필드나 메서드의 접근제어 지시자(private)에 의한 제어를 변경한다.
						field.setAccessible(true); // 값을꺼냄
													// post에 들어있는 값을 꺼낸다
						String analysisTargetString = (String) field.get(obj);
						//analyzeHashTag함수에 문자열과 map을넘겨준다
						analyzeHashTag(analysisTargetString, map);// 값을확보했을때
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				} else if (type == List.class) {
					//field의 type이 Party에 존재하는 	private List<ContactPoint> listContactPoint = new ArrayList<>(); 같은 변수에 대한 검사식임.
					try {
						field.setAccessible(true); // 값을꺼냄
						//attachObj 에 field에 존재하는 변수의 값을 꺼낸다 즉 listContactPoint.
						Object attachObj = field.get(obj); 
						//list이니 반복문을 돌아야한다
						for (Object contained : (List) attachObj) {
							// list 내에 있는 각각의 요소들에 대해서 getHashTags실행한다 
							getHashTags(contained, map);
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}

				} else if (anno != null) {
					try {
						// setAccessible()은 필드나 메서드의 접근제어 지시자(private)에 의한 제어를 변경한다.
						field.setAccessible(true); // 값을꺼냄
						Object attachObj = field.get(obj); // writer를 꺼낸다
						for (Object contained : (List) attachObj) {
							getHashTags(attachObj, map);
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}

			} else if (ao instanceof Method) {
				Method method = (Method) (ao);
				Class type = method.getReturnType();
				Annotation anno = type.getAnnotation(HashTarget.class);
				if (type == String.class) {
					try {
						String analysisTargetString = (String) method.invoke(obj, null); // 값을꺼냄
						analyzeHashTag(analysisTargetString, map);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}

				} else if (type == List.class) {
					try {
						Object attachObj = method.invoke(obj, null); // writer를 꺼낸다
						for (Object contained : (List) attachObj) {
							getHashTags(contained, map);
						}
					} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
				} else if (anno != null) {
					try {
						// setAccessible()은 필드나 메서드의 접근제어 지시자(private)에 의한 제어를 변경한다.
						Object attachObj = method.invoke(obj, null); // writer를 꺼낸다
						getHashTags(attachObj, map);
					} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 코모란을 실행을 시키는 함수.
	 * 받은 문자열에 대해서 Token으로 나누고 
	 * 나눈 Token들에 대해서 TargetPos 에 존재하는 속성인 NNG, NNP, SL, SH인지 확인한다
	 * 확인을 하고 쌍방으로 존재하는 토큰에 대해서 ret에 담는데 
	 * containsKey를 이용해서 ret에 담을때 이미 존재하는 key에 대해서는 value의 값을 1 증가시킨다.
	 * @param analysisTargetString
	 * @param ret
	 */
	private static void analyzeHashTag(String analysisTargetString, Map<String, Integer> ret) {
		KomoranResult analyzeResultList = komoran.analyze(analysisTargetString);
		List<Token> tokenList = analyzeResultList.getTokenList();
		for (Token token : tokenList) {
			TargetPos pos = null; // 품사
			try {
				pos = TargetPos.valueOf(token.getPos());
			} catch (Exception e) {
			}
			if (pos != null) {
				String hashTag = token.getMorph();
				// 맵에서 인자로 보낸 키가 있으면 true 없으면 false를 반환한다.
				if (ret.containsKey(hashTag)) {
					ret.put(hashTag, (ret.get(hashTag) + 1));
				} else {
					ret.put(hashTag, 1);
				}
			}
		}
	}

}
