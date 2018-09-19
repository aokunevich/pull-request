package akunevich.pullrequest.integration.bitbucket;

import com.google.gson.Gson;
import com.intellij.openapi.diagnostic.Logger;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Base64;

public class BitBucket {

    private static final Logger logger = Logger.getInstance(BitBucket.class);

    public PullRequests list(String url, String username, String password) {
        PullRequests result = new PullRequests();

        try {
            result = new Gson().fromJson(doList(url, username, password), PullRequests.class);
        } catch (IOException e) {
            logger.error(e);
        }

        return result;
    }

    private String doList(String url, String username, String password) throws IOException {
        return Request
                .Get(url)
                .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()))
                .execute()
                .returnContent().asString();
    }
}

