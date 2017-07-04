package com.example.administrator.myapplication.common;

import android.util.Base64;
import com.example.administrator.myapplication.LoginAct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/6/15.
 */

public class HttpUtility {

    public static String[] sendGet(String url, String param){
        String[] res = new String[2];
        String token = Base64.encodeToString((LoginAct.name+":"+LoginAct.password).getBytes(), Base64.DEFAULT);

        String responseString = "";
        BufferedReader in = null;
        try {
            String urlNameString = "http://115.29.184.56:8090/api/" + url + param;
            URL realUrl = new URL(urlNameString);

            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("Authorization", "Basic "+token);

            // 建立实际的连接
            connection.connect();

            if (connection.getResponseCode() == 200){
                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    responseString += line;
                }
                if (responseString != "") {
                    res[0] = "success";
                    res[1] = responseString;
                }

            } else {
                res[0] = "fail";
                res[1] = "";
            }

        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return res;
    }

    public static String[] sendPost(String url, String param){
        String[] res = new String[2];
        String responseString = "";
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            URL realUrl = new URL("http://115.29.184.56:8090/api/" + url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            // 发送POST请求必须设置如下两行
            conn.setDoInput(true);
            conn.setDoOutput(true);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();

//            InputStream inputStream;
//
//            int status = conn.getResponseCode();
//
//            Log.d("code", status+"");
//
//            if (status != HttpURLConnection.HTTP_OK)
//                inputStream = conn.getErrorStream();
//            else
//                inputStream = conn.getInputStream();

            if (conn.getResponseCode() == 200)
            {
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    responseString += line;
                }
                if (responseString != ""){
                    res[0] = "success";
                    res[1] = responseString;
                }
            }

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return res;
}
}
