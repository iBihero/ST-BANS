package me.stanic.bans.utils

import me.stanic.bans.Main
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

class LocUtils {

    fun salvar(p: Player, loc: String) {
        val locp = p.location
        Main.cnfg!!.set("Locais.$loc.X", locp.x)
        Main.cnfg!!.set("Locais.$loc.Y", locp.y)
        Main.cnfg!!.set("Locais.$loc.Z", locp.z)
        Main.cnfg!!.set("Locais.$loc.YAW", locp.yaw)
        Main.cnfg!!.set("Locais.$loc.PITCH", locp.pitch)
        Main.cnfg!!.set("Locais.$loc.WORLD", locp.world.name)
        Main.cnfg!!.save(Main.cnfgFile)
    }

    fun teleportar(p: Player, loc: String) {
        val x = Main.cnfg!!.getDouble("Locais.$loc.X")
        val y = Main.cnfg!!.getDouble("Locais.$loc.Y")
        val z = Main.cnfg!!.getDouble("Locais.$loc.Z")
        val yaw = Main.cnfg!!.getDouble("Locais.$loc.YAW")
        val pitch = Main.cnfg!!.getDouble("Locais.$loc.PITCH")
        val world = Bukkit.getWorld(Main.cnfg!!.getString("Locais.$loc.WORLD"))
        val tloc = Location(world, x, y, z)
        tloc.yaw = yaw.toFloat()
        tloc.pitch = pitch.toFloat()
        p.teleport(tloc)
    }

}