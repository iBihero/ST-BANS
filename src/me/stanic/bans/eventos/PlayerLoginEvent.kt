package me.stanic.bans.eventos

import me.stanic.bans.Main
import me.stanic.bans.utils.TempoUtils
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class PlayerLoginEvent : Listener {

    @EventHandler
    fun loginEvent(e: PlayerLoginEvent) {
        val p = e.player
        if (Main.instance!!.cache.containsKey(p.name)) {
            val info = Main.instance!!.cache[p.name]!!
            if (info.tipo == "Ban") {
                e.result = PlayerLoginEvent.Result.KICK_OTHER
                if (info.tempo == null) {
                    e.kickMessage =
                        Main.cnfg!!.getString("Mensagens.kickBan").replace("&", "§").replace("@n", "\n").replace(
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
                } else {
                    if (System.currentTimeMillis() >= info.tempo!!) {
                        val c = Main.instance!!.sql!!.connection
                        val stmt = c.createStatement()
                        val stmt2 = c.createStatement()
                        val rs = stmt.executeQuery("SELECT * FROM bans WHERE Nick='${p.name}'")
                        if (rs.next()) {
                            stmt2.execute("DELETE FROM bans WHERE Nick='${p.name}'")
                        }
                        c.close()
                        stmt.close()
                        stmt2.close()
                        rs.close()
                    } else {
                        val tempoDuracao = info.tempo!! - System.currentTimeMillis()
                        e.kickMessage =
                            Main.cnfg!!.getString("Mensagens.kickBan").replace("&", "§").replace("@n", "\n").replace(
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
                    }
                }
            }
        }
    }

}