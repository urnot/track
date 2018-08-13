package com.sevenstudio.track.test;

import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
public class Crawer {
    public static void getHzae(){
        try {
            String result =httpConnectionWithNoAuth("http://aviation.nmc.cn/f/proxy/get_haze","","");
            JSONObject object=JSON.parseObject(result);
            JSONArray jsonObj = object.getJSONArray("contours");
//            JSONObject jsonObj = JSON.parseObject(jsonStr);
            for(int i =0;i<jsonObj.size();i++){
                JSONObject j = jsonObj.getJSONObject(i);
                JSONArray latlon = j.getJSONArray("latAndLong");
                System.out.println(latlon.size());

                for(int x=0;x<latlon.size();x++){
                    System.out.println("hhhhh");
                    System.out.println(latlon.getJSONArray(x).get(0)+"!!!!"+latlon.getJSONArray(x).get(1));
                }
                System.out.println(j.get("symbol"));
            }
            System.out.println(jsonObj.size());
            System.out.println(jsonObj.size());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static String httpConnectionWithNoAuth(String url, String addr, String json) throws Exception {
        System.out.println("请求参数:\r\n" + json);
        System.out.println("请求地址:" + url + addr);
        HttpClient client = new HttpClient();

        long s = System.currentTimeMillis();
        GetMethod postMethod = new GetMethod(url + addr);

        postMethod.addRequestHeader("Content-Type", "application/json;charset=UTF-8");
        client.executeMethod(postMethod);
        long t = System.currentTimeMillis();
        String result = postMethod.getResponseBodyAsString();
        System.out.println("执行时长：" + (t - s) + "ms");
//        System.out.println("返回结果：" + JSON.parseObject(result));
        postMethod.releaseConnection();
        return result;
    }

    public static void main(String[] args) {
        getHzae();
        try {
            httpConnectionWithNoAuth("http://aviation.nmc.cn/f/proxy/get_haze","","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
