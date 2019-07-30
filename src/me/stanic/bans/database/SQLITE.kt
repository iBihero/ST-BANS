package me.stanic.bans.database

import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class SQLITE {

    val connection: Connection
        @Throws(SQLException::class)
        get() = DriverManager.getConnection("jdbc:sqlite:" + "plugins/ST-BANS/database.db")

    init {
        val f = File("plugins/ST-BANS")
        if (!f.exists()) {
            f.mkdirs()
        }
        try {
            Class.forName("org.sqlite.JDBC")
            val c = DriverManager.getConnection("jdbc:sqlite:" + "plugins/ST-BANS/database.db")
            val stmt = c.createStatement()
            stmt.execute("CREATE TABLE IF NOT EXISTS bans (Nick TEXT, Staffer TEXT, Motivo TEXT, Tipo TEXT, Data TEXT, Horario TEXT, Tempo LONG, ID TEXT)")
            c.close()
            stmt.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}