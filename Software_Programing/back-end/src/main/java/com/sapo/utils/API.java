package com.sapo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;
/**
 * Class cung cap cac phuong thuc giup gui request len server và nhan du lieu tra ve
 */
public class API {
    /**
     * Thuoc tinh giup format ngay thang theo dinh dang
     */
    public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * Thuoc tinh giup log ra thong tin console
     */
    //private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

    /**
     * Thiet lep connection tu serverr
     * @param url: duong dan toi server can request
     * @param method: gioa thuc toi api
     * @param token: giao thuc toi api
     * @return connection
     * @throws MalformedURLException
     * @throws IOException
     * @throws ProtocolException
     */
    private static HttpURLConnection setupConnection(String url, String method, String token)
            throws MalformedURLException, IOException, ProtocolException {
        //LOGGER.info("Request URL: " + url + "\n");
        URL line_api_url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + token);
        return conn;
    }

    /**
     * Phuong thuc doc du lieu tra ve tu server
     * @param conn: connection to server
     * @return response: phan hoi tra ve tu server
     * @throws IOException
     */
    private static String readResponse(HttpURLConnection conn) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder respone = new StringBuilder(); // ising StringBuilder for the sake of memory and performance
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        respone.append(inputLine + "\n");
        in.close();
        //LOGGER.info("Respone Info: " + respone.substring(0, respone.length() - 1).toString());
        return respone.substring(0, respone.length() - 1).toString();
    }

    /**
     * Phuong thuc hiup goi cac api dang GET
     * @param url: duong dan toi server can request
     * @param token: doan ma bam can cung cap de xac thuc nguoi dung
     * @return response: phan hoi tu server (dang string)
     * @throws Exception
     */
    public static String get(String url, String token) throws Exception {
        // Phan 1: Setup
        HttpURLConnection conn = setupConnection(url, "GET", token);

        // Phan 2: Doc du lieu tra ve tu Server
        return readResponse(conn);
    }

    /**
     * Phuong thuc giup goi cac api dang POST (thanh toan...)
     * @param url: duong dan toi server can request
     * @param data: du lieu dua len server de xu ly (dang JSON)
     * @param token: doan ma can cung cap de xac thuc nguoi dung
     * @return response:
     * @throws IOException
     */
    public static String post(String url, String data, String token) throws IOException {
        allowMethods("PATCH");
        // Phần 1: Setup
        HttpURLConnection conn = setupConnection(url, "PATCH", token);

        // Phần 2: gửi dữ liệu

        Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        writer.write(data);
        writer.close();

        return readResponse(conn);
    }

    /**
     * Phuong thuc cho phep goi cac loai giao thuc API khac nhau nhu PATCH, PUT ...
     * @param methods: giao thuc can cho phep (PATCH, PUT...)
     * @deprecated chi hoat dong voi Java <= 11
     */
    private static void allowMethods(String... methods) {
        try {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
            methodsField.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null/* static field */, newMethods);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
