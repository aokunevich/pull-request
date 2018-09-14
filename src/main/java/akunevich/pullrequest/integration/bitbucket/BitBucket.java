package akunevich.pullrequest.integration.bitbucket;

import com.google.gson.Gson;
import io.reactivex.Flowable;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Base64;

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


    public static void main(String[] args) {

        String pullRequests = "/rest/api/1.0/projects/VRTLCRD/repos/vrtlcrd/pull-requests";

        String sid = "";
        String password = "";

        try {
           String json = new BitBucket().list(pullRequests, sid, password);

           System.out.println(json);

           new Gson().fromJson(json, RepositoryPullRequests.class);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String list(String url, String sid, String password) throws IOException {
       return Request
                .Get(url)
                .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((sid + ":" + password).getBytes()))
        .execute()
        .returnContent().asString();
    }
}

