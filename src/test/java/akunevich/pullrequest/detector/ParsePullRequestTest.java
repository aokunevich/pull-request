package akunevich.pullrequest.detector;

import akunevich.pullrequest.integration.bitbucket.PullRequests;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ParsePullRequestTest {


    private static final String JSON_PULL_REQUEST = "" +
            "{" +
            "    \"size\": 1," +
            "    \"limit\": 25," +
            "    \"isLastPage\": true," +
            "    \"values\": [" +
            "        {" +
            "            \"id\": 101," +
            "            \"version\": 1," +
            "            \"title\": \"Talking Nerdy\"," +
            "            \"description\": \"It’s a kludge, but put the tuple from the database in the cache.\"," +
            "            \"state\": \"OPEN\"," +
            "            \"open\": true," +
            "            \"closed\": false," +
            "            \"createdDate\": 1359075920," +
            "            \"updatedDate\": 1359085920," +
            "            \"fromRef\": {" +
            "                \"id\": \"refs/heads/feature-ABC-123\"," +
            "                \"repository\": {" +
            "                    \"slug\": \"my-repo\"," +
            "                    \"name\": null," +
            "                    \"project\": {" +
            "                        \"key\": \"PRJ\"" +
            "                    }" +
            "                }" +
            "            }," +
            "            \"toRef\": {" +
            "                \"id\": \"refs/heads/master\"," +
            "                \"repository\": {" +
            "                    \"slug\": \"my-repo\"," +
            "                    \"name\": null," +
            "                    \"project\": {" +
            "                        \"key\": \"PRJ\"" +
            "                    }" +
            "                }" +
            "            }," +
            "            \"locked\": false," +
            "            \"author\": {" +
            "                \"user\": {" +
            "                    \"name\": \"tom\"," +
            "                    \"emailAddress\": \"tom@example.com\"," +
            "                    \"id\": 115026," +
            "                    \"displayName\": \"Tom\"," +
            "                    \"active\": true," +
            "                    \"slug\": \"tom\"," +
            "                    \"type\": \"NORMAL\"" +
            "                }," +
            "                \"role\": \"AUTHOR\"," +
            "                \"approved\": true," +
            "                \"status\": \"APPROVED\"" +
            "            }," +
            "            \"reviewers\": [" +
            "                {" +
            "                    \"user\": {" +
            "                        \"name\": \"jcitizen\"," +
            "                        \"emailAddress\": \"jane@example.com\"," +
            "                        \"id\": 101," +
            "                        \"displayName\": \"Jane Citizen\"," +
            "                        \"active\": true," +
            "                        \"slug\": \"jcitizen\"," +
            "                        \"type\": \"NORMAL\"" +
            "                    }," +
            "                    \"lastReviewedCommit\": \"7549846524f8aed2bd1c0249993ae1bf9d3c9998\"," +
            "                    \"role\": \"REVIEWER\"," +
            "                    \"approved\": true," +
            "                    \"status\": \"APPROVED\"" +
            "                }" +
            "            ]," +
            "            \"participants\": [" +
            "                {" +
            "                    \"user\": {" +
            "                        \"name\": \"harry\"," +
            "                        \"emailAddress\": \"harry@example.com\"," +
            "                        \"id\": 99049120," +
            "                        \"displayName\": \"Harry\"," +
            "                        \"active\": true," +
            "                        \"slug\": \"harry\"," +
            "                        \"type\": \"NORMAL\"" +
            "                    }," +
            "                    \"role\": \"PARTICIPANT\"," +
            "                    \"approved\": true," +
            "                    \"status\": \"APPROVED\"" +
            "                }," +
            "                {" +
            "                    \"user\": {" +
            "                        \"name\": \"dick\"," +
            "                        \"emailAddress\": \"dick@example.com\"," +
            "                        \"id\": 3083181," +
            "                        \"displayName\": \"Dick\"," +
            "                        \"active\": true," +
            "                        \"slug\": \"dick\"," +
            "                        \"type\": \"NORMAL\"" +
            "                    }," +
            "                    \"role\": \"PARTICIPANT\"," +
            "                    \"approved\": false," +
            "                    \"status\": \"UNAPPROVED\"" +
            "                }" +
            "            ]," +
            "            \"links\": {" +
            "                \"self\": [" +
            "                    {" +
            "                        \"href\": \"http://link/to/pullrequest\"" +
            "                    }" +
            "                ]" +
            "            }" +
            "        }" +
            "    ]," +
            "    \"start\": 0" +
            "}";

    @Test
    public void parsePullRequests() {
        try {
            new Gson().fromJson(JSON_PULL_REQUEST, PullRequests.class);
        } catch (Throwable e) {
            Assert.fail();
        }
    }
}
