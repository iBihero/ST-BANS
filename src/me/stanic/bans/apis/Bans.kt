package me.stanic.bans.apis

import me.stanic.bans.Main
import java.util.stream.Collectors

class Bans(
    var nick: String,
    var staffer: String,
    var motivo: String,
    var tipo: String,
    var data: String,
    var horario: String,
    var tempo: Long?,
    var id: String
) {

    fun salvar() {
        val c = Main.instance!!.sql!!.connection
        val stmt = c.createStatement()
        val rs = stmt.executeQuery("SELECT * FROM bans")
        stmt.execute("INSERT INTO bans (Nick, Staffer, Motivo, Tipo, Data, Horario, Tempo, ID) VALUES ('$nick','$staffer','$motivo','$tipo','$data','$horario','$tempo','$id');")
        c.close()
        stmt.close()
        rs.close()
    }

    companion object {
        val all: List<Bans>
            get() = Main.instance!!.cache.values.stream().collect(Collectors.toList())
    }

}