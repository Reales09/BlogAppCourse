package com.reales.blogapp.domain.home

import com.reales.blogapp.core.Result
import com.reales.blogapp.data.model.Post
import com.reales.blogapp.data.remote.home.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {
    override suspend fun getLatestPosts(): Result<List<Post>> = dataSource.getLatestPost()
}