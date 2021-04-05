package com.reales.blogapp.domain.home

import com.reales.blogapp.core.Result
import com.reales.blogapp.data.model.Post

interface HomeScreenRepo {
    suspend fun getLatestPosts(): Result<List<Post>>
}