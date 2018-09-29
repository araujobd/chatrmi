import models.Mensagem
import models.Usuario
import java.rmi.Remote
import java.rmi.RemoteException

interface ChatInterface : Remote
{
  @Throws(RemoteException::class) fun conectar(login: String): Usuario
  @Throws(RemoteException::class) fun desconectar(u: Usuario): Int
  @Throws(RemoteException::class) fun usuario(userId: Int): Usuario
  @Throws(RemoteException::class) fun usuarios(): List<Usuario>
  @Throws(RemoteException::class) fun usuariosLogados(): List<Usuario>
  @Throws(RemoteException::class) fun mensagem(msgId: Int): Mensagem
  @Throws(RemoteException::class) fun mensagens(): List<Mensagem>
  @Throws(RemoteException::class) fun falar(mensagem: Mensagem): Boolean
}