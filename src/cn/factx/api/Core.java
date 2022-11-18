package cn.factx.api;

import cn.factx.api.md5.DigestUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Core {
    protected static final String api_server_url = "https://api.factx.cn";

    protected static String submit(RequestMethod requestMethod, String uri, long appId, String appKey, List<Pair> params, String data) {
        long timestrap = System.currentTimeMillis();
        String src = appId + requestMethod.name() + timestrap + appKey + uri;
        String sign = DigestUtils.md5DigestAsHex(src.getBytes(StandardCharsets.UTF_8));
        System.out.println(src);

        String submitUrl = String.format(api_server_url + uri + "?appId=%s&timestrap=%s&sign=%s", appId, timestrap, sign);
        if (params != null) {
            for (Pair pair : params) {
                try {
                    submitUrl += "&" + pair.getKey() + "=" + URLEncoder.encode(pair.getValue(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        HttpURLConnection con = null;
        try {
            URL obj = new URL(submitUrl);
            con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod(requestMethod.name());
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Connection", "Keep-Alive");

            if (data != null) {
                byte[] bytes = data.getBytes("utf-8");
                con.connect();
                OutputStream out = con.getOutputStream();
                out.write(bytes);
                out.flush();
                out.close();
            }
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                System.out.println("connect failed. responseCode = " + responseCode);
            }
            //打印返回结果
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));) {
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return "连接服务器失败";
    }
}
