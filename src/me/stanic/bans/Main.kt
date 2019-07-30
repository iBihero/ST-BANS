package me.stanic.bans

import me.stanic.bans.apis.Bans
import me.stanic.bans.comandos.*
import me.stanic.bans.comandos.ban.BanCMD
import me.stanic.bans.comandos.ban.TempbanCMD
import me.stanic.bans.comandos.ban.UnbanCMD
import me.stanic.bans.comandos.mute.MuteCMD
import me.stanic.bans.comandos.mute.TempmuteCMD
import me.stanic.bans.comandos.mute.UnmuteCMD
import me.stanic.bans.database.SQLITE
import me.stanic.bans.eventos.InventoryEvents
import me.stanic.bans.eventos.PlayerChatEvent
import me.stanic.bans.eventos.PlayerLoginEvent
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.bot.sharding.ShardManager
import org.bukkit.Bukkit
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException

class Main : JavaPlugin() {

    val cache = HashMap<String, Bans>()
    var sql: SQLITE? = null
    val ss = ArrayList<Player>()

    override fun onEnable() {
        instance = this
        sql = SQLITE()
        carregarConfig()
        carregarDados()
        iniciarBot()
        carregarEventos()
        carregarComandos()
        Bukkit.getConsoleSender().sendMessage("§e[ST-BANS] §fativado!")
    }

    private fun carregarEventos() {
        Bukkit.getPluginManager().registerEvents(InventoryEvents(), this)
        Bukkit.getPluginManager().registerEvents(PlayerChatEvent(), this)
        Bukkit.getPluginManager().registerEvents(PlayerLoginEvent(), this)
    }

    private fun carregarComandos() {
        getCommand("ban").executor = BanCMD()
        getCommand("tempban").executor = TempbanCMD()
        getCommand("unban").executor = UnbanCMD()
        getCommand("mute").executor = MuteCMD()
        getCommand("tempmute").executor = TempmuteCMD()
        getCommand("unmute").executor = UnmuteCMD()
        getCommand("kick").executor = KickCMD()
        getCommand("warn").executor = WarnCMD()
        getCommand("checkplayer").executor = CheckPlayerCMD()
        getCommand("report").executor = ReportCMD()
        getCommand("ss").executor = SsCMD()
    }

    private fun carregarConfig() {
        cnfgFile = File(dataFolder, "settings.yml")
        if (!cnfgFile!!.exists()) {
            cnfgFile!!.parentFile.mkdirs()
            saveResource("settings.yml", false)
        }
        cnfg = YamlConfiguration()
        try {
            try {
                cnfg!!.load(cnfgFile)
            } catch (e: InvalidConfigurationException) {
                e.printStackTrace()
            }

            config.options().copyDefaults(true)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun carregarDados() {
        val c = sql!!.connection
        val stmt = c.createStatement()
        val rs = stmt.executeQuery("SELECT * FROM bans")
        while (rs.next()) {
            val nick = rs.getString("Nick")
            val staffer = rs.getString("Staffer")
            val motivo = rs.getString("Motivo")
            val tipo = rs.getString("Tipo")
            val data = rs.getString("Tipo")
            val horario = rs.getString("Horario")
            val tempo = rs.getLong("Tempo")
            val id = rs.getString("ID")
            val ban = Bans(nick, staffer, motivo, tipo, data, horario, tempo, id)
            cache[nick] = ban
        }
        c.close()
        stmt.close()
        rs.close()
    }

    var dc: ShardManager? = null

    private fun iniciarBot() {
        dc = DefaultShardManagerBuilder()
            .setToken(cnfg!!.getString("Discord.token"))
            .build()
    }

    companion object {
        var cnfg: FileConfiguration? = null
            internal set
        internal var cnfgFile: File? = null
        var instance: Main? = null
            private set
    }

}