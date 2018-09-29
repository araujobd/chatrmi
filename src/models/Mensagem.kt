package models

import java.io.Serializable

data class Mensagem(var msgId: Int,
                    val user: Usuario,
                    val text: String) : Serializable
