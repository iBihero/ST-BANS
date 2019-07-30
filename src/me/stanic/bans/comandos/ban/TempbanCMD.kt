package me.stanic.bans.comandos.ban

import me.stanic.bans.Main
import me.stanic.bans.apis.Bans
import me.stanic.bans.apis.Menus
import me.stanic.bans.utils.BanUtils
import me.stanic.bans.utils.TempoUtils
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TempbanCMD : CommandExecutor {

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
            if (sender.hasPermission(cnfg.getString("Config.permBan"))) {
                if (args.isEmpty() || args.size < 3) {
                    sender.sendMessage(cnfg.getString("Mensagens.argsTempBan").replace("&", "§"))
                } else {
                    var motivo = ""
                    for (i in 2 until args.size) {
                        motivo = motivo + args[i] + " "
                    }
                    BanUtils.nick = args[0]; BanUtils.staffer = sender.name; BanUtils.motivo = motivo; BanUtils.tipo =
                        "Ban"; BanUtils.data = TempoUtils.getDataEDia(); BanUtils.horario =
                        TempoUtils.getHorario(); BanUtils.tempo = args[1].toLong(); BanUtils.id =
                        "#${Bans.all.size + 1}"
                    Menus().confirmarPunicaoBanTemp(
                        sender,
                        args[0],
                        motivo,
                        "Ban",
                        TempoUtils.getDataEDia(),
                        TempoUtils.getHorario(),
                        args[1],
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