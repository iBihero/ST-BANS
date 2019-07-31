package me.stanic.bans.eventos

import me.stanic.bans.utils.BanUtils
import me.stanic.bans.utils.MuteUtils
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import java.util.concurrent.TimeUnit

class InventoryEvents : Listener {

    @EventHandler
    fun onConfirmarClickPermBan(e: InventoryClickEvent) {
        val p = e.whoClicked as Player
        if (e.inventory.title == "§aConfirme a punição - Ban") {
            when (e.slot) {
                21 -> BanUtils().run("S", null)
                22 -> BanUtils().run("SDC", null)
                23 -> BanUtils().run("BAN", null)
            }
            e.isCancelled = true
            p.closeInventory()
        }
    }

    @EventHandler
    fun onConfirmarClickTempBan(e: InventoryClickEvent) {
        val p = e.whoClicked as Player
        if (e.inventory.title == "§aConfirme a punição - Tempban") {
            when (e.slot) {
                20 -> BanUtils().run("S", TimeUnit.SECONDS)
                29 -> BanUtils().run("SDC", TimeUnit.SECONDS)
                38 -> BanUtils().run("BAN", TimeUnit.SECONDS)

                21 -> BanUtils().run("S", TimeUnit.MINUTES)
                30 -> BanUtils().run("SDC", TimeUnit.MINUTES)
                39 -> BanUtils().run("BAN", TimeUnit.MINUTES)

                22 -> BanUtils().run("S", TimeUnit.HOURS)
                31 -> BanUtils().run("SDC", TimeUnit.HOURS)
                40 -> BanUtils().run("BAN", TimeUnit.HOURS)

                23 -> BanUtils().run("S", TimeUnit.DAYS)
                32 -> BanUtils().run("SDC", TimeUnit.DAYS)
                41 -> BanUtils().run("BAN", TimeUnit.DAYS)

                24 -> BanUtils().run("S", TimeUnit.DAYS)
                33 -> BanUtils().run("SDC", TimeUnit.DAYS)
                42 -> BanUtils().run("BAN", TimeUnit.DAYS)
            }
            e.isCancelled = true
            p.closeInventory()
        }
    }

    @EventHandler
    fun onConfirmarClickPermMute(e: InventoryClickEvent) {
        val p = e.whoClicked as Player
        if (e.inventory.title == "§aConfirme a punição - Mute") {
            when (e.slot) {
                21 -> MuteUtils().run("S", null)
                22 -> MuteUtils().run("SDC", null)
                23 -> MuteUtils().run("MUTE", null)
            }
            e.isCancelled = true
            p.closeInventory()
        }
    }

    @EventHandler
    fun onConfirmarClickTempMute(e: InventoryClickEvent) {
        val p = e.whoClicked as Player
        if (e.inventory.title == "§aConfirme a punição - Tempmute") {
            when (e.slot) {
                20 -> MuteUtils().run("S", TimeUnit.SECONDS)
                29 -> MuteUtils().run("SDC", TimeUnit.SECONDS)
                38 -> MuteUtils().run("MUTE", TimeUnit.SECONDS)

                21 -> MuteUtils().run("S", TimeUnit.MINUTES)
                30 -> MuteUtils().run("SDC", TimeUnit.MINUTES)
                39 -> MuteUtils().run("MUTE", TimeUnit.MINUTES)

                22 -> MuteUtils().run("S", TimeUnit.HOURS)
                31 -> MuteUtils().run("SDC", TimeUnit.HOURS)
                40 -> MuteUtils().run("BAN", TimeUnit.HOURS)

                23 -> MuteUtils().run("S", TimeUnit.DAYS)
                32 -> MuteUtils().run("SDC", TimeUnit.DAYS)
                41 -> MuteUtils().run("MUTE", TimeUnit.DAYS)

                24 -> MuteUtils().run("S", TimeUnit.DAYS)
                33 -> MuteUtils().run("SDC", TimeUnit.DAYS)
                42 -> MuteUtils().run("MUTE", TimeUnit.DAYS)
            }
            e.isCancelled = true
            p.closeInventory()
        }
    }

    @EventHandler
    fun onCheckClick(e: InventoryClickEvent) {
        if (e.inventory.title.contains("§aPunição dê")) {
            e.isCancelled = true
        }
    }

}