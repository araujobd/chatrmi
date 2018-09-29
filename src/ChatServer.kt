import java.rmi.registry.LocateRegistry
import java.rmi.server.UnicastRemoteObject
import kotlin.system.exitProcess

fun main(args :Array<String>) {
  try {
    val chat = Chat()
    val stub = UnicastRemoteObject.exportObject(chat, 0) as ChatInterface
    val registry = LocateRegistry.createRegistry(1099)
    registry.bind("Chat", stub)

    println("Server conected")
  } catch (e: Exception) {
    e.printStackTrace()
    exitProcess(1)
  }
}