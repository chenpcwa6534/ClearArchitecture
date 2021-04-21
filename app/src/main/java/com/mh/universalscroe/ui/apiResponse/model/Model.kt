package com.mh.universalscroe.ui.apiResponse.model

import com.mh.universalscroe.base.BaseModel

class Model: BaseModel(), IModel{
    companion object{
        fun getInstance() = Model()
    }
}