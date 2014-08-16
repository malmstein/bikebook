package com.malmstein.bikebook;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.malmstein.bikebook.api.BikeBookApi;
import com.malmstein.bikebook.api.BikeBookApiFactory;
import com.malmstein.bikebook.model.Index;

import java.net.MalformedURLException;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends Activity implements Observer<Index> {

    BikeBookApi api;
    Index localIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            api = new BikeBookApi(BikeBookApiFactory.defaultClient(getApplicationContext()), AndroidSchedulers.mainThread(), getString(R.string.base_url));
            api.getIndex(this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Index index) {
        localIndex = index;
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
