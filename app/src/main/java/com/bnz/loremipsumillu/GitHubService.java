package com.bnz.loremipsumillu;

import com.bnz.loremipsumillu.model.Repo;

import java.util.List;

import retrofit.http.GET;

/**
 * Created by anyer on 7/7/15.
 */
public interface GitHubService {
    @GET("/gh-pages/api.json")
    List<Repo> listRepos();

}
