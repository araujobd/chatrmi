import models.Mensagem
import models.Usuario
import java.rmi.registry.LocateRegistry
import kotlin.system.exitProcess

fun main(args: Array<String>) {
  if (args.isEmpty()) {
    println("Passe o ip como argumento!")
    exitProcess(1)
  }
  try {
    val registry = LocateRegistry.getRegistry(1099)
    val chat = registry.lookup("Chat") as ChatInterface

    val usuario = chat.conectar(login())
    var opcao: Int
    do {
      opcao = menu()
      when(opcao) {
        0 -> chat.desconectar(usuario)
        1 -> mostrarUsuariosLogados(chat)
        2 -> falar(chat, usuario)
        3 -> mostrarMensagens(chat)
        else -> println("Opção Inválida")
      }
    } while(opcao != 0)
  } catch (e: Exception) {
    e.printStackTrace()
    exitProcess(1)
  }
}

fun menu(): Int {
  println("\nEscolha uma opção:")
  println("\t0 - Desconectar")
  println("\t1 - Mostrar Usuários Conectados")
  println("\t2 - Falar")
  println("\t3 - Ler Mensagens")
  print("Opção: ")
  val mensagem = readLine()
  println()
  return try {
    mensagem?.toInt() ?: -1
  } catch (e: NumberFormatException) {
    -1
  }
}

fun login(): String {
  var s: String?
  do {
    print("Digite o login: ")
    s = readLine()
  } while (s.isNullOrBlank())
  return s!!
}

fun mostrarMensagens(chat: ChatInterface) =
  with(chat.mensagens()) {
    if (isEmpty())
      println("Não há mensagens!")
    else
      forEach { msg ->
        println("${msg.user.login}(${msg.user.status}): ${msg.text}")
      }
  }

fun mostrarUsuariosLogados(chat: ChatInterface) =
  chat.usuariosLogados().forEach {
    println(it.login)
  }

fun falar(chat: ChatInterface, user: Usuario) {
  print("Digite a mensagem: ")
  val s = readLine()
  s?.let { chat.falar(Mensagem(-1, user, it)) }
}