package www.dream.com.framework.util;

public class StringUtil {

   public static int[] convertCommaSepString2IntArr(String ids) {
      String[] splited = ids.split(",");
      int[] ret = new int[splited.length];
      for (int i = 0; i < splited.length; i++) {
         try {
            ret[i] = Integer.parseInt(splited[i]);
         } catch (Exception e) {
         }
      }
      return ret;
   }
//   public static void main(String[] args) {
//	String sss = "2,3,4,5";
//	int[] l = convertCommaSepString2IntArr(sss);
//	for(int a : l) {
//		System.out.print(a);
//	}
	
//}
}