package tech.demura.testproject.data_layer.cat_image_api.mapper

import tech.demura.testproject.data_layer.cat_image_api.dto.CatImageResponseDto

class CatImageMapper {
    fun mapResponseToUrl(responseDto: CatImageResponseDto): String{
        return "https://cataas.com/cat/${responseDto._id}?type=small"
    }
}