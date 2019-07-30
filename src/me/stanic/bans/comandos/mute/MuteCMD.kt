package me.stanic.bans.comandos.mute

import me.stanic.bans.Main
import me.stanic.bans.apis.Bans
import me.stanic.bans.apis.Menus
import me.stanic.bans.utils.MuteUtils
import me.stanic.bans.utils.TempoUtils
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MuteCMD : CommandExecutor {

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
            if (sender.hasPermission(cnfg.getString("Config.permMute"))) {
                if (args.isEmpty() || args.size < 2) {
                    sender.sendMessage(cnfg.getString("Mensagens.argsMute").replace("&", "§"))
                } else {
                    var motivo = ""
                    for (i in 1 until args.size) {
                        motivo = motivo + args[i] + " "
                    }
                    MuteUtils.nick = args[0]; MuteUtils.staffer = sender.name; MuteUtils.motivo =
                        motivo; MuteUtils.tipo = "Mute"; MuteUtils.data = TempoUtils.getDataEDia(); MuteUtils.horario =
                        TempoUtils.getHorario(); MuteUtils.id = "#${Bans.all.size + 1}"
                    Menus().confirmarPunicaoMute(
                        sender,
                        args[0],
                        motivo,
                        "Mute",
                        TempoUtils.getDataEDia(),
                        TempoUtils.getHorario(),
                        "Permanente",
                        "#${Bans.all.size + 1}"
                    )
                }
            } else {
                sender.sendMessage(cnfg.getString("Mensagens.semPerm").replace("&", "§"))
            }
        }
        return false
    }

}