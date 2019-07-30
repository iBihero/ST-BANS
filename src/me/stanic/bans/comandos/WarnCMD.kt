package me.stanic.bans.comandos

import me.stanic.bans.Main
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class WarnCMD : CommandExecutor {

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
            if (sender.hasPermission(cnfg.getString("Config.permWarn"))) {
                if (args.isEmpty() || args.size < 2) {
                    sender.sendMessage(cnfg.getString("Mensagens.argsWarn").replace("&", "§"))
                } else {
                    val p = Bukkit.getPlayer(args[0])
                    if (p == null) {
                        sender.sendMessage(cnfg.getString("Mensagens.playerInexistente"))
                        return true
                    }
                    var motivo = ""
                    for (i in 1 until args.size) {
                        motivo = motivo + args[i] + " "
                    }
                    for (msg in Main.cnfg!!.getStringList("Mensagens.warnPlayer"))
                        p.sendMessage(
                            msg.replace("&", "§").replace("{motivo}", motivo).replace(
                                "{staffer}",
                                sender.name
                            ).replace("{nick}", args[0])
                        )
                    for (msg in cnfg.getStringList("Mensagens.warnBroadcast"))
                        for (players in Bukkit.getOnlinePlayers()) players.sendMessage(
                            msg.replace(
                                "&",
                                "§"
                            ).replace("{motivo}", motivo).replace("{staffer}", sender.name).replace("{nick}", args[0])
                        )
                    sender.sendMessage(
                        cnfg.getString("Mensagens.warnSucess").replace("&", "§").replace(
                            "{motivo}",
                            motivo
                        ).replace("{staffer}", sender.name).replace("{nick}", args[0])
                    )
                }
            } else {
                sender.sendMessage(cnfg.getString("Mensagens.semPerm").replace("&", "§"))
            }
        }
        return false
    }

}