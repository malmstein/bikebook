package com.malmstein.bikebook;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.malmstein.bikebook.api.BikeBookAPI;
import com.malmstein.bikebook.json.responses.IndexResponse;
import com.malmstein.bikebook.json.responses.YearJson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import ly.apps.android.rest.client.Callback;
import ly.apps.android.rest.client.Response;
import ly.apps.android.rest.client.RestClient;
import ly.apps.android.rest.client.RestClientFactory;
import ly.apps.android.rest.client.RestServiceFactory;

public class MainActivity extends Activity {

    BikeBookAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestClient client = RestClientFactory.defaultClient(getApplicationContext());
        api = RestServiceFactory.getService(getString(R.string.base_url), BikeBookAPI.class, client);
        api.getIndex(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Response<IndexResponse> indexResponse) {
                String data = indexResponse.getRawData();
                try {
                    Map<String, Map<String, List<YearJson>>> result = new ObjectMapper().readValue("{\"root\":" + data + "}", Map.class);
                } catch (IOException e) {
                    e.printStackTrace();
                };
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
