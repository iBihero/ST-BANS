package me.stanic.bans.comandos

import me.stanic.bans.Main
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class KickCMD : CommandExecutor {

    private val cnfg = Main.cnfg!!

    override fun onCommand(
        sender: CommandSender,
        cmd: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player) {
            sender.sendMessage(cnfg.getString("Mensagens.somenteInGame").replace("&", "§"))
        } else {
            if (sender.hasPermission(cnfg.getString("Config.permKick"))) {
                if (args.isEmpty() || args.size < 2) {
                    sender.sendMessage(cnfg.getString("Mensagens.argsKick").replace("&", "§"))
                } else {
                    var motivo = ""
                    for (i in 1 until args.size) {
                        motivo = motivo + args[i] + " "
                    }
                    val p = Bukkit.getPlayer(args[0])
                    if (p == null) {
                        sender.sendMessage(cnfg.getString("Mensagens.playerInexistente").replace("&", "§"))
                        return true
                    }
                    p.kickPlayer(
                        cnfg.getString("Mensagens.kickPlayer").replace("&", "§").replace(
                            "{motivo}",
                            motivo
                        ).replace("{staffer}", sender.name).replace("@n", "\n")
                    )
                }
            } else {
                sender.sendMessage(cnfg.getString("Mensagens.semPerm").replace("&", "§"))
            }
        }
        return false
    }

}