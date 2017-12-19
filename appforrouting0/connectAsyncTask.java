package com.example.user.appforrouting0;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class connectAsyncTask extends AsyncTask<Void, Void, String> {
    String url;
    connectAsyncTask(String urlPass){
        url = urlPass;
    }

    @Override
    protected String doInBackground(Void... params) {
        JSONParser jParser = new JSONParser();
        String json = jParser.getJSONFromUrl(url);
        return json;
    }
}