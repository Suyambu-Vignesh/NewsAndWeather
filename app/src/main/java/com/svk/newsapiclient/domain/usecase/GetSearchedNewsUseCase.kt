package com.svk.newsapiclient.domain.usecase

import com.svk.newsapiclient.data.model.APIResponse
import com.svk.newsapiclient.data.util.Resource
import com.svk.newsapiclient.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
     suspend fun execute(country:String,searchQuery:String,page:Int): Resource<APIResponse>{
         return newsRepository.getSearchedNews(country,searchQuery,page)
     }
}