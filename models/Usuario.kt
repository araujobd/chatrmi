package models

import java.io.Serializable

data class Usuario(var userId: Int,
                   val login: String,
                   var status: Status) : Serializable
