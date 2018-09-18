package akunevich.pullrequest.integration.bitbucket;

import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BitBucket {



/*


{
  "size": 12,
  "limit": 25,
  "isLastPage": true,
  "values": [
    {
      "id": 2,
      "version": 0,
      "title": "",
      "description": "",
      "state": "OPEN",
      "open": true,
      "closed": false,
      "author": {
        "user": {
          "name": "",
          "emailAddress": "",
          "id": 111,
          "displayName": ""
        },
        "role": "AUTHOR",
        "approved": false,
        "status": "UNAPPROVED"
      },
      "reviewers": [
        {
          "user": {
            "name": "",
            "emailAddress": "",
            "id": 2,
            "displayName": ""
          },
          "role": "REVIEWER",
          "approved": false,
          "status": "UNAPPROVED"
        }
     ]
    }
  ]
  "start": 0
}

 */


/*
    public static void main(String[] args) {

        String pullRequests = "/rest/api/1.0/projects/VRTLCRD/repos/vrtlcrd/pull-requests";

        String sid = "";
        String password = "";

        try {
            String json = new BitBucket().list(pullRequests, sid, password);

            System.out.println(json);

            new Gson().fromJson(json, PullRequests.class);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
*/


    public PullRequests list(String url, String username, String password) {
        PullRequests result = new PullRequests();

/*
        try {
            result = new Gson().fromJson(doList(url, username, password), PullRequests.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        result.setValues(Stream.of(new PullRequest()).collect(Collectors.toList()));

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

