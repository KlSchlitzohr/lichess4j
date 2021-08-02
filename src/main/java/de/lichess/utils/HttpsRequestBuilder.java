package de.lichess.utils;

import de.lichess.LichessClient;
import de.lichess.exceptions.AuthorizationFailedException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpsRequestBuilder {

    private String url;
    private HttpsRequestType httpsRequestType;
    private LichessClient lichessClient;
    private HashMap<String, String> queryParameters;

    public HttpsRequestBuilder(String url, HttpsRequestType httpsRequestType, LichessClient lichessClient) {
        this.url = url;
        this.httpsRequestType = httpsRequestType;
        this.lichessClient = lichessClient;
    }

    public HttpsRequestBuilder setPostParams(HashMap<String, String> queryParameters) {
        this.queryParameters = queryParameters;
        return this;
    }

    public String getResponse() {
        try {
            String urlString = this.url;
            if (queryParameters != null && !queryParameters.isEmpty())
                urlString += "?" + getParamsString(queryParameters);
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer " + lichessClient.getToken());
            connection.setDoOutput(true);
            connection.setRequestMethod(this.httpsRequestType.getRequestType());
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            manageResponseCode(connection);
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void manageResponseCode(HttpsURLConnection httpsURLConnection) {
        try {
            int responseCode = httpsURLConnection.getResponseCode();
            if (responseCode == 401)
                throw new AuthorizationFailedException();
        } catch (IOException | AuthorizationFailedException e) {
            e.printStackTrace();
        }
    }

    private String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }


}
