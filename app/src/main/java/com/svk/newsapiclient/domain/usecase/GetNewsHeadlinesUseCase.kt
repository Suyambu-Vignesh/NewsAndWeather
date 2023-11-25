package com.svk.newsapiclient.domain.usecase

import com.svk.newsapiclient.data.model.APIResponse
import com.svk.newsapiclient.data.util.Resource
import com.svk.newsapiclient.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(country: String, page: Int): Resource<APIResponse> {
        return newsRepository.getNewsHeadlines(country, page)
    }
}
