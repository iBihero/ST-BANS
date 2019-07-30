package me.stanic.bans.comandos

import me.stanic.bans.Main
import me.stanic.bans.apis.Menus
import me.stanic.bans.utils.BanUtils
import me.stanic.bans.utils.TempoUtils
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CheckPlayerCMD : CommandExecutor {

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
            if (sender.hasPermission(cnfg.getString("Config.permCheck"))) {
                if (args.isEmpty()) {
                    sender.sendMessage(cnfg.getString("Mensagens.argsCheck").replace("&", "§"))
                } else {
                    if (BanUtils.temPunicao(args[0])) {
                        val p = Main.instance!!.cache[args[0]]!!
                        Menus().checkPlayer(
                            sender,
                            args[0],
                            p.motivo,
                            p.tipo,
                            p.data,
                            p.horario,
                            if (p.tempo == null) "Permanente" else TempoUtils().getTempo(p.tempo!! - System.currentTimeMillis()),
                            p.id
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