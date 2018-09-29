import models.Mensagem
import models.Status
import models.Usuario

class Chat(private val usuarios: MutableList<Usuario> = mutableListOf(),
           private val mensagens: MutableList<Mensagem> = mutableListOf())
    : ChatInterface {

  override fun conectar(login: String): Usuario {
    val index = usuarios.indexOfFirst { it.login == login }
    return if (index < 0) {
      val usuario = Usuario(usuarios.size, login, Status.ONLINE)
      usuarios.add(usuario)
      usuario
    } else {
      usuarios[index].status = Status.ONLINE
      usuarios[index]
    }
  }

  override fun desconectar(u: Usuario): Int {
    usuarios.first { it.userId == u.userId }.status = Status.OFFLINE
    return 1
  }

  override fun usuario(userId: Int): Usuario =
      usuarios.first { it.userId == userId }

  override fun usuarios(): List<Usuario> = usuarios

  override fun usuariosLogados(): List<Usuario> = usuarios.filter { it.status == Status.ONLINE }

  override fun mensagem(msgId: Int): Mensagem =
      mensagens.first { it.msgId == msgId }

  override fun mensagens(): List<Mensagem> = mensagens.onEach { it.user.status = usuario(it.user.userId).status }

  override fun falar(mensagem: Mensagem): Boolean {
    mensagem.msgId = mensagens.size
    return mensagens.add(mensagem)
  }

}
