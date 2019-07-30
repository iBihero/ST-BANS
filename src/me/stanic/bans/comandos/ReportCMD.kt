package me.stanic.bans.comandos

import me.stanic.bans.Main
import net.dv8tion.jda.core.EmbedBuilder
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ReportCMD : CommandExecutor {

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
            if (sender.hasPermission(cnfg.getString("Config.permReport"))) {
                if (args.isEmpty() || args.size < 2) {
                    sender.sendMessage(cnfg.getString("Mensagens.argsReport").replace("&", "§"))
                } else {
                    var motivo = ""
                    for (i in 1 until args.size) {
                        motivo = motivo + args[i] + " "
                    }
                    for (staffers in Bukkit.getOnlinePlayers()) {
                        for (msg in cnfg.getStringList("Mensagens.reportNotificacao"))
                            if (staffers.hasPermission(cnfg.getString("Config.permReportAdmin"))) {
                                staffers.sendMessage(
                                    msg.replace("&", "§").replace(
                                        "{nick}",
                                        args[0]
                                    ).replace("{vitima}", sender.name).replace("{motivo}", motivo)
                                )
                            }
                    }
                    val channel = Main.instance!!.dc!!.getTextChannelById(cnfg.getString("Discord.reportChannel"))
                    val embed: EmbedBuilder = EmbedBuilder()
                        .setTitle(cnfg.getString("Discord.reportsMsg.titulo"))
                        .setDescription(
                            cnfg.getString("Discord.reportsMsg.descricao").replace(
                                "{nick}",
                                args[0]
                            ).replace("{vitima}", sender.name).replace("{motivo}", motivo).replace("@n", "\n")
                        )
                    channel.sendMessage(embed.build()).complete()
                    sender.sendMessage(cnfg.getString("Mensagens.reportSucess").replace("&", "§"))
                }
            } else {
                sender.sendMessage(cnfg.getString("Mensagens.semPerm").replace("&", "§"))
            }
        }
        return false
    }

}