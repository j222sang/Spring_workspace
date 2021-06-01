package www.dream.com.framework.langPosAnalyzer;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import www.dream.com.framework.classAnalyzer.ClassAnalyzer;

/**
 * 품사 분석기가 정의한 Annotation을 달아 놓은 객체의 속성에 들어있는 정보를 Komoran을 활용하여 
 * 품사분석하고 해쉬태그로 활용할 만한 단어들이 몇번 사용되었는지 까지를 ComparablePair의 List로 반환할 것임.
 * 품사 : 2
 */
public class PosAnalyzer {
	private static Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
	
	public static Map<String, Integer> getHashTags(Object obj){
		//출력에 나올 Map을 담는 배열
		Map<String, Integer> ret = new HashMap<>();
		// annotation이 달려있는 것들을 검사해서 분석기 의 역할
		// HashTarget.class 의 역할? - 
		getHashTags(obj, ret);
		return ret; 
	}
	private static void getHashTags(Object obj, Map<String, Integer> map){
		//
		List<AccessibleObject> listFeature = ClassAnalyzer.findFeatureByAnnotation(obj.getClass(), HashTarget.class);
		
		for(AccessibleObject ao : listFeature) {
			if (ao instanceof Field) {
				Field field = (Field)(ao);
				Class type = field.getType();
				Annotation anno = type.getAnnotation(HashTarget.class);
				if (type == String.class) {
					try {
						//setAccessible()은 필드나 메서드의 접근제어 지시자(private)에 의한 제어를 변경한다.
						field.setAccessible(true);	//값을꺼냄
						String analysisTargetString = (String) field.get(obj);
						analyzeHashTag(analysisTargetString, map);//값을확보했을때
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}else if(type == List.class) {
					try {
						field.setAccessible(true);	//값을꺼냄
						Object attachObj = field.get(obj); //writer를 꺼낸다
						for (Object contained : (List)attachObj) {
								getHashTags(contained, map);
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					
				}
				else if (anno != null) {
					try {
						//setAccessible()은 필드나 메서드의 접근제어 지시자(private)에 의한 제어를 변경한다.
						field.setAccessible(true);	//값을꺼냄
						Object attachObj = field.get(obj); //writer를 꺼낸다
						for (Object contained : (List)attachObj) {
								getHashTags(attachObj, map);
							}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			
			}else if(ao instanceof Method) {
				Method method = (Method)(ao);
				Class type = method.getReturnType();
				Annotation anno = type.getAnnotation(HashTarget.class);
				if (type == String.class) {
					try {
						String analysisTargetString =(String) method.invoke(obj, null); //값을꺼냄
						analyzeHashTag(analysisTargetString, map);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
					
				}
				else if (type == List.class) {
					try {
						Object attachObj = method.invoke(obj, null); //writer를 꺼낸다
						for (Object contained : (List)attachObj) {
								getHashTags(contained, map);
						}
					} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
				} else if (anno != null) {
					try {
						//setAccessible()은 필드나 메서드의 접근제어 지시자(private)에 의한 제어를 변경한다.
						Object attachObj = method.invoke(obj, null); //writer를 꺼낸다
						getHashTags(attachObj, map);
					} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	private static void analyzeHashTag(String analysisTargetString, Map<String, Integer> ret) {
		KomoranResult analyzeResultList = komoran.analyze(analysisTargetString);
		String strToAnalyze = "아버지가 품에 네오를 안고 아버지가 방으로 들어가신다";
		List<Token> tokenList = analyzeResultList.getTokenList();
		for (Token token : tokenList) {
			TargetPos pos = null;	//품사
			try {
				pos = TargetPos.valueOf(token.getPos());
			}catch(Exception e) {
			}
			if (pos != null) {
				String hashTag = token.getMorph();
				//맵에서 인자로 보낸 키가 있으면 true 없으면 false를 반환한다.
				if (ret.containsKey(hashTag)) {
					ret.put(hashTag, (ret.get(hashTag) + 1));
				}else {
					ret.put(hashTag, 1);
				}
			}
		}
	}
	
	
}
