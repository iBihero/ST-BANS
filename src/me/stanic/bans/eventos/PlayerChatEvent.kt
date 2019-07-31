package me.stanic.bans.eventos

import me.stanic.bans.Main
import me.stanic.bans.utils.TempoUtils
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class PlayerChatEvent : Listener {

    @EventHandler
    fun onChat(e: AsyncPlayerChatEvent) {
        val p = e.player
        if (Main.instance!!.cache.containsKey(p.name)) {
            val info = Main.instance!!.cache[p.name]!!
            if (info.tempo != null) {
                if (System.currentTimeMillis() >= info.tempo!!) {
                    val c = Main.instance!!.sql!!.connection
                    val stmt = c.createStatement()
                    val stmt2 = c.createStatement()
                    val rs = stmt.executeQuery("SELECT * FROM bans WHERE Nick='${p.name}'")
                    if (rs.next()) {
                        stmt2.execute("DELETE FROM bans WHERE Nick='${p.name}'")
                        Main.instance!!.cache.remove(p.name)
                    }
                    c.close()
                    stmt.close()
                    stmt2.close()
                    rs.close()
                } else {
                    val tempoDuracao = info.tempo!! - System.currentTimeMillis()
                    e.isCancelled = true
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
                            ).replace("{tempo}", TempoUtils().getTempo(tempoDuracao)).replace(
                                "{id}",
                                info.id
                            )
                        )
                }
            } else {
                e.isCancelled = true
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
                        ).replace("{tempo}", "Permanente").replace(
                            "{id}",
                            info.id
                        )
                    )
            }
        }
    }

}