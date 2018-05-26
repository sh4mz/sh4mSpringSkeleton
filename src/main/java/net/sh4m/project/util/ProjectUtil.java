/**
 * @author ssaleh
 *
 * Created date 1 Aug 2017
 */
package net.sh4m.project.util;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import net.sh4m.project.enums.StatusEnum;

public class ProjectUtil {

	/**
	 * @param status
	 */
	public static int getStatus(StatusEnum status) {

		if (status.equals(StatusEnum.success)){
			return 1;
		} else if(status.equals(StatusEnum.error)){
			return 0;
		} else if(status.equals(StatusEnum.partial)){
			return 2;
		}

		return 0;
	}

	public static String quote(String s) {
	    return new StringBuilder()
	        .append('\'')
	        .append(s)
	        .append('\'')
	        .toString();
	}

	/**
	 * @param string
	 * @param headerValue
	 * @return
	 */
	public static String[] splitString(String chr, String headerValue) {
		return headerValue.split(Pattern.quote(chr));
	}
	
	public static String md5HashVal(String value) {     
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(value.getBytes());
		    byte[] digest = md.digest();
		    String myHash = DatatypeConverter
		      .printHexBinary(digest).toUpperCase();
		    return myHash;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return null;
	}

	/**
	 * @return
	 */
	public static XMLGregorianCalendar getCurrentDateTimeXML() {
		String FORMATER = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        
		DateFormat format = new SimpleDateFormat(FORMATER);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date date = new Date();
		XMLGregorianCalendar xmlDate = null;
		GregorianCalendar gc = new GregorianCalendar();

		gc.setTime(date);
		try{
		    xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(date));
		}
		catch(Exception e){
		    return null;
		}
		return xmlDate.normalize();
	}

	/**
	 * @param str
	 * @return
	 */
	public static String removeAlphabet(String str) {
		return str.replaceAll("[^0-9]", "");
	}
	
	/**
     * @param str
     * @return
     */
    public static String removeFirstAlphabet(String str) {
        return str.replaceAll("^[a-zA-Z]", "");
    }

	/**
	 * @param characterToLead
	 * @param width
	 * @param number value
	 * @return String
	 */
	public static String leadCharToNum(String characterToLead, int width, int val) {
		String fullVal = String.format("%" + characterToLead + width +"d", val);
		return fullVal;
	}
	
	/**
	   * Compares two Strings, and returns the portion where they differ.
	   * (More precisely, return the remainder of the second String,
	   * starting from where it's different from the first.)
	   *
	   * For example,
	   * <code>difference("i am a machine", "i am a robot") -> "robot"</code>.
	   *
	   * <pre>
	   * StringUtils.difference(null, null) = null
	   * StringUtils.difference("", "") = ""
	   * StringUtils.difference("", "abc") = "abc"
	   * StringUtils.difference("abc", "") = ""
	   * StringUtils.difference("abc", "abc") = ""
	   * StringUtils.difference("ab", "abxyz") = "xyz"
	   * StringUtils.difference("abcde", "abxyz") = "xyz"
	   * StringUtils.difference("abcde", "xyz") = "xyz"
	   * </pre>
	   *
	   * @param str1  the first String, may be null
	   * @param str2  the second String, may be null
	   * @return the portion of str2 where it differs from str1; returns the
	   * empty String if they are equal
	   * @since 2.0
	   */
	  public static String difference(String str1, String str2) {
	      if (str1 == null) {
	          return str2;
	      }
	      if (str2 == null) {
	          return str1;
	      }
	      int at = indexOfDifference(str1, str2);
	      if (at == -1) {
	          return "";
	      }
	      return str2.substring(at);
	  }
	  /**
	   * Compares two Strings, and returns the index at which the
	   * Strings begin to differ.
	   *
	   * For example,
	   * <code>indexOfDifference("i am a machine", "i am a robot") -> 7</code>
	   *
	   * <pre>
	   * StringUtils.indexOfDifference(null, null) = -1
	   * StringUtils.indexOfDifference("", "") = -1
	   * StringUtils.indexOfDifference("", "abc") = 0
	   * StringUtils.indexOfDifference("abc", "") = 0
	   * StringUtils.indexOfDifference("abc", "abc") = -1
	   * StringUtils.indexOfDifference("ab", "abxyz") = 2
	   * StringUtils.indexOfDifference("abcde", "abxyz") = 2
	   * StringUtils.indexOfDifference("abcde", "xyz") = 0
	   * </pre>
	   *
	   * @param str1  the first String, may be null
	   * @param str2  the second String, may be null
	   * @return the index where str2 and str1 begin to differ; -1 if they are equal
	   * @since 2.0
	   */
	  public static int indexOfDifference(String str1, String str2) {
	      if (str1 == str2) {
	          return -1;
	      }
	      if (str1 == null || str2 == null) {
	          return 0;
	      }
	      int i;
	      for (i = 0; i < str1.length() && i < str2.length(); ++i) {
	          if (str1.charAt(i) != str2.charAt(i)) {
	              break;
	          }
	      }
	      if (i < str2.length() || i < str1.length()) {
	          return i;
	      }
	      return -1;
	  }

	

	/**
	 * @param parameters
	 * @return
	 */
	public static String getParamsString(Map<String, String> params) {
		StringBuilder result = new StringBuilder();
		 
        for (Map.Entry<String, String> entry : params.entrySet()) {
          try {
        	  result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
        	  result.append("=");
              result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
              result.append("&");
          	} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
          	}
          
        }
 
        String resultString = result.toString();
        System.out.println(resultString.substring(0, resultString.length() - 1));
        return resultString.length() > 0
          ? resultString.substring(0, resultString.length() - 1)
          : resultString;
	}

	
	/**
	 * @param url
	 * @return
	 */
	public static String changeBackSlash(String url) {
		url = url.replace("\\", "/");
		return url;
	}

	/**
	 * @param selRef
	 * @return
	 */
	public static List<Map<String, Object>> splitExcelRange(String data) {
		List<Map<String,Object>> mapping = new ArrayList<Map<String,Object>>(); 
		
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher(data);
		List<String> squareBracket = new ArrayList<String>();
		int instanceCount = 1;
		while(m.find()) {
			Map<String,Object> eachMapping = new HashMap<String,Object>();
			squareBracket.add(m.group(1));
		    //System.out.println(m.group(1));
			eachMapping.put("mapping", m.group(1));
			eachMapping.put("instance", instanceCount);
			eachMapping.put("priority", true);
			mapping.add(eachMapping);
			instanceCount++;
		}
		
		String newData = data.replaceAll("\\[(.*?)\\]", "");
		//System.out.println(newData);
		Map<String,Object> eachMapping = new HashMap<String,Object>();
		eachMapping.put("mapping", newData);
		eachMapping.put("instance", instanceCount);
		eachMapping.put("priority", false);
		mapping.add(eachMapping);
		return mapping;
	}

	/**
	 * @param data
	 * @return
	 */
	public static boolean checkExcelRowOrColType(String data) {
		boolean colType = false;
		if(data.contains(",")){
			String[] dataSplit = data.split(",");
			if(dataSplit[0].contains(":")){
				String[] rangeSplit = dataSplit[0].split(":");
				colType = checkColType(rangeSplit[0],rangeSplit[1]);
			} else {
				colType = checkColType(dataSplit[0],dataSplit[1]);
			}
		}
		return colType;
	}
	
	private static boolean checkColType(String address1, String address2) {
		if(removeAlphabet(address1).equals(removeAlphabet(address2))){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param p
	 * @return
	 */
	public static String arrayToStringNewLine(List<String> stringArray) {
		String newString = "";
		for(String eachString : stringArray){
			newString += eachString + "\n";
		}
		return newString;
	}

	/**
	 * @param contentValue
	 * @return
	 */
	public static Collection<? extends String> stringSplitByNewLine(String contentValue) {
		String lines[] = contentValue.split("\\r?\\n");
		return Arrays.asList(lines);
	}

	/**
	 * @param string
	 * @return
	 */
	public static String smbToFileVolumesPath(String urlStr) {
		if (urlStr.startsWith("file:////Volumes/")){
			return urlStr;
		}
		if(urlStr.startsWith("smb://")){
			urlStr = urlStr.replace("smb://", "file://");
		}
		
		try {
			URL aURL = new URL(urlStr);
			String host = aURL.getHost();
			if(host != null && !host.isEmpty()){
				urlStr = urlStr.replace(host, "Volumes");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urlStr;
	}

	public static boolean checkImageType(String eachFile) {
		String newFileName = eachFile.toLowerCase();
		if(newFileName.endsWith(".jpg") || newFileName.endsWith(".jpeg") || 
				newFileName.endsWith(".bmp") || newFileName.endsWith(".png") ||
				newFileName.endsWith(".bitmap")) {
			return true;
		}
		return false;
	}

	/**
	 * @param brandValue
	 * @return
	 */
	public static String removeHtmlTag(String val) {
		val = val.replace("<p>", "").replace("</p>", "")
				.replace("<b>", "").replace("</b>", "")
				.replace("<i>", "").replace("</i>", "")
				.replace("<u>", "").replace("</u>", "");
		return val;
	}

	/**
	 * @param bodyContent
	 * @return
	 */
	public static String removePandAddNewLine(String bodyContent) {
		//bodyContent.replaceAll("<br/>", "\n");
//		if(bodyContent.startsWith("<p>")){
//			bodyContent = bodyContent.substring(3, bodyContent.length());
//		}
//		if(bodyContent.endsWith("</p>")){
//			bodyContent = bodyContent.substring(0, bodyContent.length()-4);
//		}
//		
//		bodyContent = removeTagNBetweenTagWithVal(bodyContent, "</p>", "<p>","</p><p>");
//		
//		if(bodyContent.contains("</p><p>")){
//			bodyContent = bodyContent.replaceAll("</p><p>", "\n");
//		}
		final Pattern pattern = Pattern.compile("<p>(.+?)</p>",Pattern.DOTALL);
		final Matcher matcher = pattern.matcher(bodyContent);
		StringBuilder newString = new StringBuilder();
		while (matcher.find()) {
	        if(newString.length() > 0){
	        	newString.append("\n")
	        	.append(matcher.group());
	        } else {
	        	newString.append(matcher.group());
	        }
	    }
		if (newString.length() > 0){
			bodyContent = newString.toString();
		}
		
		if(bodyContent.startsWith("<p>")){
			bodyContent = bodyContent.substring(3, bodyContent.length());
		}
		if(bodyContent.endsWith("</p>")){
			bodyContent = bodyContent.substring(0, bodyContent.length()-4);
		}
		
		bodyContent = removeTagNBetweenTagWithVal(bodyContent, "</p>", "<p>","</p><p>");
		
		if(bodyContent.contains("</p><p>")){
			bodyContent = bodyContent.replaceAll("</p><p>", "\n");
		}
		
		
		return bodyContent;
	}

	/**
	 * @param contentValue
	 * @return
	 */
	public static String addPandNewLineP(String contentValue) {
		if(!contentValue.startsWith("<p>")){
			contentValue = "<p>" + contentValue;
		}
		if(!contentValue.endsWith("</p>")){
			contentValue = contentValue + "</p>";
		}
		contentValue = contentValue.replaceAll("\\r?\\n", "</p><p>");
		return contentValue;
	}

	/**
	 * @param sourceFileName
	 * @return
	 */
	public static String changeXmlToHtmlExt(String fileName) {
		if(fileName.endsWith(".xml")){
			fileName = fileName.replaceAll(".xml", ".html");
		} 
		return fileName;
	}

	/**
	 * @param sourceFileName
	 * @return
	 */
	public static String changeXMLToPDFExt(String fileName) {
		if(fileName.endsWith(".xml")){
			fileName = fileName.replaceAll(".xml", ".pdf");
		} 
		return fileName;
	}

	/**
	 * @param string
	 * @return
	 */
	public static String getCurrentDateFormat(String format) {
		if(format == null || format.isEmpty()){
			format = "dd-MM-yyyy";
		}
		LocalDateTime localDate = LocalDateTime.now();//For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		String formattedString = localDate.format(formatter);
		return formattedString;
	}

	/**
	 * 
	 * @param diffStr
	 * @param tag1
	 * @param tag2
	 * @return
	 */
	public static String removeTagNBetweenTag(String diffStr, String tag1, String tag2) {
		final String regex = "(?s)" + tag1 + ".*?" + tag2;
		diffStr = diffStr.replaceAll(regex, "");
		return diffStr;
	}
	
	/**
	 * 
	 * @param diffStr
	 * @param tag1
	 * @param tag2
	 * @param newVal
	 * @return
	 */
	public static String removeTagNBetweenTagWithVal(String diffStr, String tag1, String tag2, String newVal) {
		final String regex = "(?s)" + tag1 + ".*?" + tag2;
		diffStr = diffStr.replaceAll(regex, newVal);
		return diffStr;
	}

	public static String removeStartWithChar(String charToRemove, String text) {
		while(text.startsWith(charToRemove)) {
			text = text.substring(1, text.length());
		}
		return text;
	}

	public static String getDateTimeString(long date) {
		String FORMATER = "dd-MM-yyyy HH:mm:ss";
        
		DateFormat format = new SimpleDateFormat(FORMATER);
		//format.setTimeZone(TimeZone.getTimeZone("GMT"));
		format.setTimeZone(TimeZone.getDefault());
		Date creationDate=new Date(date);
		
		return format.format(creationDate);
	}

	public static String diffReplaceSymbol(String text) {
		text = text.replace("\\s", "&#8852;");
		text = text.replace(" ", "&#8852;");
		text = text.replace("\n", "&#182;");
		text = text.replace("\r", "&#182;");
		text = text.replace("\\r", "&#182;");
		text = text.replace("\r\n", "&#182;");
		text = text.replace("\\r\\n", "&#182;");
        text = text.replace("\\n", "&#182;");
		text = text.replace("<br>","&boxul;");
		text = text.replace("<br/>","&boxul;");
		text = text.replace("&#13;", "&#182;");
		return text;
	}

	public static String removeCellRefSheetName(String sheetName, String cellRef) {
		return cellRef.replace(sheetName, "");
	}

	public static String removeFirstAndLastPTag(String val) {
		if(val.startsWith("<p>")) val = val.substring(3);
		if(val.endsWith("</p>")) val = val.substring(0, val.length() - 4); 
		return val;
	}

	public static String addFirstAndLastPTag(String val) {
		val = "<p>" + val + "</p>";
		return val;
	}
	
	public static String replaceTagToCharacter(String text) {
		text = text.replaceAll("<b>", "Ƃ");
		text = text.replaceAll("</b>", "ƃ");
		text = text.replaceAll("<i>", "Ȉ");
		text = text.replaceAll("</i>", "ȉ");
		text = text.replaceAll("<u>", "Ȕ");
		text = text.replaceAll("</u>", "ȕ");
		return text;
	}
	public static String replaceCharacterToTaq(String text) {
		text = text.replaceAll("Ƃ","<b>");
		text = text.replaceAll("ƃ","</b>");
		text = text.replaceAll("Ȉ","<i>");
		text = text.replaceAll("ȉ","</i>");
		text = text.replaceAll("Ȕ","<u>");
		text = text.replaceAll("ȕ","</u>");
		return text;
	}

	public static String getAlphabet(String text) {
		return text.replaceAll("[0-9]*?\\-", "");
	}
	
}
