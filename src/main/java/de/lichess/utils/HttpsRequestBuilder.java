package de.lichess.utils;

import de.lichess.LichessClient;
import de.lichess.exceptions.AuthorizationFailedException;
import de.lichess.exceptions.RateLimitingExecption;

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
    private String payload;
    private HashMap<String, String> queryParameters;
    private HashMap<String, String> headerParameters;

    public HttpsRequestBuilder(String url, HttpsRequestType httpsRequestType, LichessClient lichessClient) {
        this.url = url;
        this.httpsRequestType = httpsRequestType;
        this.lichessClient = lichessClient;
    }

    public String getResponse() {
        try {
            String urlString = this.url;
            if (queryParameters != null && !queryParameters.isEmpty())
                urlString += "?" + getParamsString(queryParameters);
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer " + lichessClient.getToken());
            if (headerParameters != null && !headerParameters.isEmpty())
                headerParameters.keySet().forEach(headerParameter ->
                        connection.setRequestProperty(headerParameter,headerParameters.get(headerParameter)));
            connection.setDoOutput(true);
            connection.setRequestMethod(this.httpsRequestType.getRequestType());
            if (payload != null) {
                OutputStream os = connection.getOutputStream();
                os.write(payload.getBytes());
                os.flush();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            manageResponseCode(connection);
            //System.out.println(out.toString());
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
            else if (responseCode == 429)
                throw new RateLimitingExecption();
        } catch (IOException | AuthorizationFailedException | RateLimitingExecption e) {
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

    public HttpsRequestBuilder setPostParams(HashMap<String, String> queryParameters) {
        this.queryParameters = queryParameters;
        return this;
    }

    public HttpsRequestBuilder setHeaderParameters(HashMap<String, String> headerParameters) {
        this.headerParameters = headerParameters;
        return this;
    }

    public HttpsRequestBuilder setPayload(String payload) {
        this.payload = payload;
        return this;
    }

}
