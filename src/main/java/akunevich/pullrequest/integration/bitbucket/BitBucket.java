package akunevich.pullrequest.integration.bitbucket;

import com.google.gson.Gson;
import com.intellij.openapi.diagnostic.Logger;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Base64;

public class BitBucket {

    private static final Logger logger = Logger.getInstance(BitBucket.class);

    private static final String URL_LIST = "{0}/rest/api/1.0/projects/{1}/repos/{2}/pull-requests";

    public PullRequests list(String url, String project, String repository, String username, String password) {
        PullRequests result = new PullRequests();

        String correctedUrl = url;
        if (url.endsWith("/")) {
            correctedUrl = url.substring(0, url.lastIndexOf("/"));
        }

        String generated = MessageFormat.format(URL_LIST, correctedUrl, project, repository);
        try {
            result = new Gson().fromJson(doList(generated, username, password), PullRequests.class);
        } catch (Exception e) {
            logger.error("Can't load list of pull requests from url: " + generated, e);
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

