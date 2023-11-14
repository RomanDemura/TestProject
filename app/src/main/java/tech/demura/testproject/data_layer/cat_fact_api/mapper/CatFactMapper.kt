package tech.demura.testproject.data_layer.cat_fact_api.mapper

import tech.demura.testproject.data_layer.cat_fact_api.dto.CatFactResponseDto
import tech.demura.testproject.domain_layer.news.entites.News
import javax.inject.Inject

class CatFactMapper @Inject constructor() {
    fun mapResponseToNews(responseDto: CatFactResponseDto): News{
        return News(title = responseDto.fact, text = responseDto.fact)
    }
}