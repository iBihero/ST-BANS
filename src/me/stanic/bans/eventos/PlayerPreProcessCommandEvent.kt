package me.stanic.bans.eventos

import me.stanic.bans.Main
import me.stanic.bans.utils.MuteUtils
import me.stanic.bans.utils.TempoUtils
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class PlayerPreProcessCommandEvent : Listener {

    @EventHandler
    fun onSS(e: PlayerCommandPreprocessEvent) {
        val p = e.player
        if (Main.instance!!.ss.contains(p)) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onChat(e: PlayerCommandPreprocessEvent) {
        val p = e.player
        if (MuteUtils.temPunicao(p.name)) {
            for (cmds in Main.cnfg!!.getStringList("Config.comandosBloqueados"))
                if (e.message.startsWith(cmds)) {
                    e.isCancelled = true
                    val info = Main.instance!!.cache[p.name]!!
                    val tempoDuracao = info.tempo!! - System.currentTimeMillis()
                    for (msg in Main.cnfg!!.getStringList("Mensagens.nPodeFalarNoChat"))
                        p.sendMessage(
                            msg.replace("&", "§").replace(
                                "{nick}",
                                info.nick
                            ).replace("{staffer}", info.staffer).replace(
                                "{motivo}",
                                info.motivo
                            ).replace("{tipo}", info.tipo).replace(
                                "{data}",
                                info.data
                            ).replace(
                                "{horario}",
                                info.horario
                            ).replace(
                                "{tempo}",
                                if (info.tempo == null) "Permanente" else TempoUtils().getTempo(tempoDuracao)
                            ).replace(
                                "{id}",
                                info.id
                            )
                        )
                }
        }
    }

}