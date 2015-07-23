package com.bnz.loremipsumillu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bnz.loremipsumillu.model.Repo;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RestAdapter restAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.bnz.loremipsumillu.R.layout.activity_main);

       // restAdapter = new RestAdapter.Builder().setEndpoint("https://api.github.com").build();
        restAdapter = new RestAdapter.Builder().setEndpoint("https://raw.githubusercontent.com/MarieSchweiz/lorem-ipsum-illustration").build();

        final GitHubService service = restAdapter.create(GitHubService.class);

        setupRecyclerView();

        new AsyncTask<Void, Void, List<Repo>>(){

            @Override
            protected List<Repo> doInBackground(Void... params) {
                List<Repo> repoList = service.listRepos();

                return repoList;
            }

            @Override
            protected void onPostExecute(List<Repo> repoList) {
                super.onPostExecute(repoList);

                GitHubAdapter adapter = (GitHubAdapter) mRecyclerView.getAdapter();
                adapter.getRepoList().addAll(repoList);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(com.bnz.loremipsumillu.R.id.activity_main_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new GitHubAdapter(this, new ArrayList<Repo>()));
    }
}
