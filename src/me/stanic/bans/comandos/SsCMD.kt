package me.stanic.bans.comandos

import me.stanic.bans.Main
import me.stanic.bans.utils.LocUtils
import me.stanic.bans.utils.TabUtils
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SsCMD : CommandExecutor {

    private val cnfg = Main.cnfg!!
    private val ss = Main.instance!!.ss

    override fun onCommand(
        sender: CommandSender,
        cmd: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player) {
            sender.sendMessage(cnfg.getString("Mensagens.somenteInGame").replace("&", "§"))
        } else {
            if (sender.hasPermission(cnfg.getString("Config.permSS"))) {
                if (args.isEmpty()) {
                    for (msg in cnfg.getStringList("Mensagens.ssHelp"))
                        sender.sendMessage(msg.replace("&", "§"))
                } else {
                    if (args[0] == "setloc") {
                        LocUtils().salvar(sender, "ScreenShare")
                        sender.sendMessage(cnfg.getString("Mensagens.ssSetSucess").replace("&", "§"))
                        return true
                    }
                    val p = Bukkit.getPlayer(args[0])
                    if (p == null) {
                        sender.sendMessage(cnfg.getString("Mensagens.playerInexistente").replace("&", "§"))
                        return true
                    }
                    if (ss.contains(p)) {
                        ss.remove(p)
                        for (players in Bukkit.getOnlinePlayers()) {
                            for (msg in cnfg.getStringList("Mensagens.ssLiberadaBroadcast"))
                                players.sendMessage(msg.replace("&", "§").replace("{nick}", p.name))
                        }
                        p.kickPlayer(cnfg.getString("Mensagens.ssLiberada").replace("&", "§"))
                        return true
                    }
                    LocUtils().teleportar(p, "ScreenShare")
                    LocUtils().teleportar(sender, "ScreenShare")
                    ss.add(p)
                    p.sendMessage(cnfg.getString("Mensagens.ssPuxada"))
                    for (players in Bukkit.getOnlinePlayers()) {
                        for (msg in cnfg.getStringList("Mensagens.ssPuxadaBroadcast"))
                            players.sendMessage(msg.replace("&", "§").replace("{nick}", p.name))
                    }
                    if (cnfg.getBoolean("ScreenShare.Tab.ativar")) TabUtils().run(p)
                }
            } else {
                sender.sendMessage(cnfg.getString("Mensagens.semPerm").replace("&", "§"))
            }
        }
        return false
    }

}