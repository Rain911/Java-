
-package Test01;
-
-import java.io.FileOutputStream;
-import java.io.IOException;
-import java.io.InputStream;
-import java.io.OutputStream;
-
-import org.apache.http.*;
-import org.apache.commons.httpclient.HttpException;
-import org.apache.commons.httpclient.HttpStatus;
-import org.apache.commons.httpclient.NameValuePair;
-import org.apache.commons.httpclient.methods.PostMethod;
-
-
-
-
-
-public class RetrivePage {
-	private static org.apache.commons.httpclient.HttpClient httpClient=
-			new org.apache.commons.httpclient.HttpClient();
-	static {
-		httpClient.getHostConfiguration().setProxy("172.17.18.84", 8080);
-	}
-	public static boolean downloadPage(String path) 
-			throws HttpException,IOException
-	{
-		InputStream input= null;
-		OutputStream output= null;
-		//得到POST方法
-		PostMethod postMethod = new PostMethod(path);
-		//设置post方法的参数
-		NameValuePair[] postData = new NameValuePair[2];
-		postData[0] = new NameValuePair("name","lietu");
-		postData[1] = new NameValuePair("password","123456");
-		postMethod.addParameters(postData);
-		//执行，返回状态码
-		int statusCode = httpClient.executeMethod(postMethod);
-		System.out.println(statusCode);
-		//针对状态码进行处理(简单起见，只处理返回值为200的状态码)
-		if(statusCode == HttpStatus.SC_OK)
-		{
-			input = postMethod.getResponseBodyAsStream();
-			//得到文件名
-			String filename = path.substring(path.lastIndexOf('/')+1);
-			
-			//获得文件输出流
-			output = new FileOutputStream(filename);
-			System.out.println(filename);
-			//输出到文件
-			int tempByte = -1;
-			while((tempByte=input.read())>0)
-			{
-				output.write(tempByte);
-				System.out.println(output);
-			}
-			//关闭输出流
-			if(input!=null)
-			{
-				input.close();
-			}
-			if(output!=null)
-			{
-				output.close();
-			}
-			return true;
-		}
-		return false;
-	}
-	
-	/**
-	 * 测试代码
-	 * @param args
-	 */
-	public static void main(String[] args) {
-		System.out.println();
-		//抓取lietu首页，输出
-		try {
-			RetrivePage.downloadPage("http://www.lietu.com/");
-			
-		} catch (HttpException e) {
-			// TODO: handle exception
-			e.printStackTrace();
-			System.out.println(e.toString());
-		}catch (IOException e){
-			e.printStackTrace();
-		}
-	}
-}
@Rain911
Commit changes

    Commit directly to the master branch.
    Create a new branch for this commit and start a pull request. Learn more about pull requests.

    © 2018 GitHub, Inc.
    Terms
    Privacy
    Security
    Status
    Help

    Contact GitHub
    API
    Training
    Shop
    Blog
    About

