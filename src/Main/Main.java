package Main;

import SpreadUtils.WeiboSpreadUtils;
import org.json.JSONObject;

import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException {

        if (args.length > 1) {
            String url = args[0];
            int interval = Integer.valueOf(args[1]);
            String outputpath = null;
            if (args.length > 2) {
                outputpath = args[2];
            }
            JSONObject result = WeiboSpreadUtils.WeiboSpread(url,
                    outputpath, interval);
        } else {
            System.out.println("输入正确的参数：[url] [interval] [outputpath](可选，否则为默认)");
        }
    }

}
