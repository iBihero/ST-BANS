package me.stanic.bans.comandos.mute

import me.stanic.bans.Main
import me.stanic.bans.utils.BanUtils
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class UnmuteCMD : CommandExecutor {

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
            if (sender.hasPermission(cnfg.getString("Config.permUnmute"))) {
                if (args.isEmpty()) {
                    sender.sendMessage(cnfg.getString("Mensagens.argsUnmute").replace("&", "§"))
                } else {
                    if (BanUtils.temPunicao(args[0])) {
                        val c = Main.instance!!.sql!!.connection
                        val stmt = c.createStatement()
                        val stmt2 = c.createStatement()
                        val rs = stmt.executeQuery("SELECT * FROM bans WHERE Nick='${args[0]}'")
                        if (rs.next()) {
                            stmt2.execute("DELETE FROM bans WHERE Nick='${args[0]}'")
                        }
                        c.close()
                        stmt.close()
                        stmt2.close()
                        rs.close()
                        Main.instance!!.cache.remove(args[0])
                        Bukkit.getPlayer(args[0])?.sendMessage(cnfg.getString("Mensagens.unmutePlayer"))
                        sender.sendMessage(
                            cnfg.getString("Mensagens.unmuteSucess").replace("&", "§").replace(
                                "{nick}",
                                args[0]
                            )
                        )
                    } else {
                        sender.sendMessage(cnfg.getString("Mensagens.naoTemPunicao").replace("&", "§"))
                    }
                }
            } else {
                sender.sendMessage(cnfg.getString("Mensagens.semPerm").replace("&", "§"))
            }
        }
        return false
    }

}