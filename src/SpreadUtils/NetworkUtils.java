package SpreadUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URLConnection;

public class NetworkUtils {


    /**
     * 向指定url发送get请求
     *
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getReq(String url) throws MalformedURLException,
            IOException {
        //通知Java您要通过代理进行连接
//		System.getProperties().put("proxySet", "true");
//
//		//指定代理所在的服务器
//		System.getProperties().put("proxyHost", "127.0.0.1");
//
//		//指定代理监听的端口
//		System.getProperties().put("proxyPort", "8087");
        URLConnection conn = new java.net.URL(url).openConnection();
        conn.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        conn.setRequestProperty("Method", "GET");
//		conn.set
        conn.setReadTimeout(5000);
        try {
            conn.connect();
        } catch (SocketTimeoutException ste) {
            long sleepTime = (long) (2 * Math.random() * 1000);
            try {
                Thread.sleep(sleepTime);
                System.out.println("超时，" + sleepTime + "毫秒后重连");
                conn.connect();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        InputStream is = conn.getInputStream();

        ByteArrayOutputStream rbaos = new ByteArrayOutputStream();
        byte[] rbuffer = new byte[128];
        int riLen = -1;
        while (-1 != (riLen = is.read(rbuffer)))
            rbaos.write(rbuffer, 0, riLen);
        final String Ret = new String(rbaos.toByteArray(), "utf-8");
//		new stri
        return Ret;
    }


    /**
     * 向指定url发送get请求
     *
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getReq(String url, String host) throws MalformedURLException,
            IOException {
        //通知Java您要通过代理进行连接
//		System.getProperties().put("proxySet", "true");
//
//		//指定代理所在的服务器
//		System.getProperties().put("proxyHost", "127.0.0.1");
//
//		//指定代理监听的端口
//		System.getProperties().put("proxyPort", "8087");
        URLConnection conn = new java.net.URL(url).openConnection();
        conn.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
        conn.setRequestProperty("Host", host);
        conn.setRequestProperty("Method", "GET");
//        String cookie = FileUtils.File2str("./conf/cookie");
        String cookie = "SUB=_2A256HNkZDeRxGedI4lAU8y7OyTyIHXVZ_udRrDV6PUJbstAKLU_7kW1LHetlptaLNWC3LIUtLbcVgY5g5ibZ4A..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WFhR8XsKz4fOX-kiJ8IYPLv5JpX5o2p; SUHB=0KAAsD_oxdUCvI; SSOLoginState=1461233993; gsid_CTandWM=4uKICpOz5ZcT5YBRfp7ZG76iS3y; _T_WM=19625b40b11aa435089ba8562695c88f; M_WEIBOCN_PARAMS=uicode%3D20000061%26featurecode%3D20000180%26fid%3D3844940318873821%26oid%3D3844940318873821";
        conn.setRequestProperty("Cookie", cookie);
        conn.setReadTimeout(5000);
        for (int i = 0; i < 3; i++) {
            String Ret = null;
            try {
                conn.connect();
                InputStream is = conn.getInputStream();

                ByteArrayOutputStream rbaos = new ByteArrayOutputStream();
                byte[] rbuffer = new byte[128];
                int riLen = -1;
                while (-1 != (riLen = is.read(rbuffer)))
                    rbaos.write(rbuffer, 0, riLen);
                Ret = new String(rbaos.toByteArray(), "utf-8");
//		new stri
                return Ret;
            } catch (SocketTimeoutException ste) {
                long sleepTime = (long) (2 * Math.random() * 1000);
                try {
                    System.out.println("超时，" + sleepTime + "毫秒后重连");
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException ioe) {
                System.out.println(ioe);
                return null;
            }

        }
        return null;
    }

    public static void main(String args[]) {
        try {
            String html = getReq("http://m.weibo.cn/single/rcList?format=cards&id=3836270826512131&type=repost&hot=0&page=10", "m.weibo.cn");
            System.out.println(html);
            html = (String) html.subSequence(1, html.length() - 1);
            JSONTokener tokener = new JSONTokener(html);
            JSONObject root = (JSONObject) tokener.nextValue();
            JSONArray card_group = root.getJSONArray("card_group");
            for (int i = 0; i < card_group.length(); i++) {
                JSONObject card = card_group.getJSONObject(i);
                System.out.println(card);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
