package me.stanic.bans.utils

import me.stanic.bans.Main
import net.minecraft.server.v1_8_R3.ChatComponentText
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TabUtils {

    private val cnfg = Main.cnfg!!
    var tab = false

    fun run(p: Player) {
        val packet = PacketPlayOutPlayerListHeaderFooter()
        object : BukkitRunnable() {
            override fun run() {
                val header = ChatComponentText(
                    cnfg.getString("ScreenShare.Tab.Header")
                        .replace("&", "§")
                        .replace("@n", "\n")
                        .replace(
                            "{data}",
                            TempoUtils.getDataEDia()
                        )
                        .replace("{horario}", TempoUtils.getHorario())
                )
                val footer = ChatComponentText(
                    cnfg.getString("ScreenShare.Tab.Footer")
                        .replace("&", "§")
                        .replace("@n", "\n")
                        .replace(
                            "{data}",
                            TempoUtils.getDataEDia()
                        )
                        .replace("{horario}", TempoUtils.getHorario())
                )
                try {
                    val a = packet.javaClass.getDeclaredField("a")
                    a.isAccessible = true
                    val b = packet.javaClass.getDeclaredField("b")
                    b.isAccessible = true

                    tab = when {
                        tab -> {
                            b.set(packet, footer)
                            false
                        }
                        else -> {
                            b.set(packet, footer)
                            true
                        }
                    }
                    a.set(packet, header)

                    if (Bukkit.getOnlinePlayers().isEmpty())
                        return
                    (p as CraftPlayer).handle.playerConnection.sendPacket(packet)
                } catch (e: NoSuchFieldException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
            }

        }.runTaskTimer(Main.instance!!, 0, 80)
    }

}