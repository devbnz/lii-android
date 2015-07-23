package com.bnz.loremipsumillu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bnz.loremipsumillu.model.Repo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by anyer on 7/8/15.
 */
class GitHubAdapter extends RecyclerView.Adapter<GitHubViewHolder> {
    private MainActivity mainActivity;
    private List<Repo> mRepoList;

    public GitHubAdapter(MainActivity mainActivity, List<Repo> repoList) {
        this.mainActivity = mainActivity;
        this.mRepoList = repoList;
    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    @Override
    public void onBindViewHolder(GitHubViewHolder gitHubViewHolder, int i) {
        Repo repo = mRepoList.get(i);


        gitHubViewHolder.getTextViewName().setText(repo.title);

        String imageprefix = "https://raw.githubusercontent.com/MarieSchweiz/lorem-ipsum-illustration/gh-pages/png/";
        String imagesuffix = "_360.png";
        String imageurl = imageprefix + repo.file + imagesuffix;

        Picasso.with(mainActivity).load(imageurl)
                .into(gitHubViewHolder.getPicture());
    }

    @Override
    public GitHubViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(com.bnz.loremipsumillu.R.layout.repo, viewGroup, false);

        return new GitHubViewHolder(itemView);
    }

    public List<Repo> getRepoList() {
        return mRepoList;
    }
}
