package com.svk.newsapiclient.domain.usecase

import com.svk.newsapiclient.data.model.Article
import com.svk.newsapiclient.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
  suspend fun execute(article: Article)=newsRepository.saveNews(article)
}