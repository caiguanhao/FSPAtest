package com.example.fspa_test;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class RandomStringFragment extends ListFragment {

    private static Main activity;
    private static MyAdapter Adapter;

    private PullToRefreshListView mPullToRefreshListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View layout = super.onCreateView(inflater, container, savedInstanceState);

        ListView lv = (ListView) layout.findViewById(android.R.id.list);
        ViewGroup parent = (ViewGroup) lv.getParent();

        int lvIndex = parent.indexOfChild(lv);
        parent.removeViewAt(lvIndex);
        mPullToRefreshListView = new PullToRefreshListView(layout.getContext());
        mPullToRefreshListView.setMode(Mode.BOTH);
        mPullToRefreshListView.setOnRefreshListener(
                new OnRefreshListener2<ListView>(){
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),
                                "Don't pull down the list! Pull up instead.",
                                Toast.LENGTH_LONG).show();
                        mPullToRefreshListView.onRefreshComplete();
                    }
                }, 1000);
            }
            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                new GetDataTask().execute();
            }
        });

        parent.addView(mPullToRefreshListView, lvIndex, lv.getLayoutParams());

        return layout;
    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            for (int i = 0; i< 10; i++) {
                Adapter.add(random_string_please(20));
            }
            mPullToRefreshListView.onRefreshComplete();
            super.onPostExecute(result);
        }
    }

    public static String random_string_please(int LENGTH) {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        char tempChar;
        for (int i = 0; i < LENGTH; i++){
            tempChar = (char) (generator.nextInt(25) + 65);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        activity = (Main) getActivity();

        Adapter = new MyAdapter(activity, R.layout.listitem, new ArrayList<String>());

        setListAdapter(Adapter);

        for (int i = 0; i< 10; i++) {
            Adapter.add(random_string_please(20));
        }

        // Bundle mBundle = getArguments();
        // do something
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // https://github.com/chrisbanes/Android-PullToRefresh/issues/99
        position--;

        Toast.makeText(getActivity(), Adapter.getItem(position), Toast.LENGTH_SHORT).show();
    }

    public class MyAdapter extends ArrayAdapter<String> {

        private ArrayList<String> objects;

        public MyAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            this.objects = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent){

            View v = convertView;

            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.listitem, null);
            }

            String i = objects.get(position);

            if (i != null) {
                TextView name = (TextView) v.findViewById(R.id.item_name);

                if (name != null){
                    name.setText(i);
                }
            }

            // animation to a static list is OK, but not to a dynamic one
            /*Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -3.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f
            );
            animation.setDuration(300);
            animation.setStartOffset(100 * position);
            v.startAnimation(animation);*/

            return v;
        }
    }
}