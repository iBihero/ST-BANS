package me.stanic.bans.utils

import me.stanic.bans.Main
import me.stanic.bans.apis.Bans
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.TextChannel
import org.bukkit.Bukkit
import java.util.concurrent.TimeUnit

class BanUtils {

    companion object {
        var nick = ""
        var staffer = ""
        var motivo = ""
        var tipo = ""
        var data = ""
        var horario = ""
        var tempo: Long? = null
        var id = ""

        //Verificando se tem uma punição

        fun temPunicao(p: String): Boolean {
            return Main.instance!!.cache.containsKey(p)
        }

    }

    private val main = Main.instance!!

    fun run(tipoAnuncio: String, tipoTempo: TimeUnit?) {
        val staff = Bukkit.getPlayer(staffer)
        var tempoDuracao = 0L

        if (temPunicao(nick)) {
            staff.sendMessage(Main.cnfg!!.getString("Mensagens.jaTemPunicao").replace("&", "§"))
            return
        }

        //Criando uma nova punição
        if (tempo == null) {
            val ban = Bans(
                nick,
                staffer,
                motivo,
                tipo,
                data,
                horario,
                tempo,
                id
            )
            main.cache[nick] = ban
            ban.salvar()
        } else {
            val time = TimeUnit.MILLISECONDS.convert(tempo!!, tipoTempo)
            val soma = System.currentTimeMillis() + time
            val total = System.currentTimeMillis() - soma
            tempoDuracao = total
            val ban = Bans(
                nick,
                staffer,
                motivo,
                tipo,
                data,
                horario,
                soma,
                id
            )
            main.cache[nick] = ban
            ban.salvar()
        }

        //Enviar a mensagem de sucesso para o Staffer
        for (msg in Main.cnfg!!.getStringList("Mensagens.banAplicado")) staff.sendMessage(
            msg.replace("&", "§").replace(
                "{nick}",
                nick
            ).replace("{staffer}", staffer).replace(
                "{motivo}",
                motivo
            ).replace("{tipo}", tipo).replace(
                "{data}",
                data
            ).replace(
                "{horario}",
                horario
            ).replace("{tempo}", if (tempo == null) "Permanente" else TempoUtils().getTempo(tempoDuracao)).replace(
                "{id}",
                id
            )
        )

        //Pegando a mensagem do kick
        val mensagem = Main.cnfg!!.getString("Mensagens.kickBan").replace("&", "§").replace("@n", "\n").replace(
            "{nick}",
            nick
        ).replace("{staffer}", staffer).replace(
            "{motivo}",
            motivo
        ).replace("{tipo}", tipo).replace(
            "{data}",
            data
        ).replace(
            "{horario}",
            horario
        ).replace("{tempo}", if (tempo == null) "Permanente" else TempoUtils().getTempo(tempoDuracao)).replace(
            "{id}",
            id
        )

        //Verificando se a pessoa punida existe, caso não esteja online vai pular o método de kick
        Bukkit.getPlayer(nick)?.kickPlayer(mensagem)

        //Anunciando a punição
        when (tipoAnuncio) {
            "S" -> return
            "SDC" -> banSDC(
                nick,
                staffer,
                motivo,
                tipo,
                data,
                horario,
                tempo,
                tipoTempo,
                id
            )
            "BAN" -> ban(
                nick,
                staffer,
                motivo,
                tipo,
                data,
                horario,
                tempo,
                tipoTempo,
                id
            )
        }
    }

    //Métodos de anúncio

    private fun banSDC(
        nick: String,
        staffer: String,
        motivo: String,
        tipo: String,
        data: String,
        horario: String,
        tempo: Long?,
        tipoTempo: TimeUnit?,
        id: String
    ) {
        var tempoDuracao = 0L
        if (tempo != null) {
            val time = TimeUnit.MILLISECONDS.convert(Companion.tempo!!, tipoTempo)
            val soma = System.currentTimeMillis() + time
            val total = System.currentTimeMillis() - soma
            tempoDuracao = total
        }
        for (players in Bukkit.getOnlinePlayers()) {
            for (msg in Main.cnfg!!.getStringList("Mensagens.broadcastPunicao"))
                players.sendMessage(
                    msg.replace("&", "§").replace(
                        "{nick}",
                        nick
                    ).replace("{staffer}", staffer).replace("{motivo}", motivo).replace("{tipo}", tipo).replace(
                        "{data}",
                        data
                    ).replace("{horario}", horario).replace(
                        "{tempo}",
                        if (tempo == null) "Permanente" else TempoUtils().getTempo(tempoDuracao)
                    ).replace("{id}", id)
                )
        }
    }

    private fun ban(
        nick: String,
        staffer: String,
        motivo: String,
        tipo: String,
        data: String,
        horario: String,
        tempo: Long?,
        tipoTempo: TimeUnit?,
        id: String
    ) {
        var tempoDuracao = 0L
        var tempoDuracaoDC = 0L
        if (tempo != null) {
            val time = TimeUnit.MILLISECONDS.convert(Companion.tempo!!, tipoTempo)
            val soma = System.currentTimeMillis() + time
            val total = System.currentTimeMillis() - soma
            tempoDuracaoDC = total
        }
        for (players in Bukkit.getOnlinePlayers()) {
            for (msg in Main.cnfg!!.getStringList("Mensagens.broadcastPunicao"))
                players.sendMessage(
                    msg.replace("&", "§").replace(
                        "{nick}",
                        nick
                    ).replace("{staffer}", staffer).replace("{motivo}", motivo).replace("{tipo}", tipo).replace(
                        "{data}",
                        data
                    ).replace("{horario}", horario).replace(
                        "{tempo}",
                        if (tempo == null) "Permanente" else TempoUtils().getTempo(tempoDuracao)
                    ).replace("{id}", id)
                )
        }
        val channel: TextChannel = main.dc!!.getTextChannelById(Main.cnfg!!.getString("Discord.punicaoChannel"))
        val embed: EmbedBuilder = EmbedBuilder()
            .setTitle(Main.cnfg!!.getString("Discord.bansMsg.titulo"))
            .setDescription(
                Main.cnfg!!.getString("Discord.bansMsg.descricao").replace("&", "§").replace("@n", "\n").replace(
                    "{nick}",
                    nick
                ).replace("{staffer}", staffer).replace("{motivo}", motivo).replace("{tipo}", tipo).replace(
                    "{data}",
                    data
                ).replace("{horario}", horario).replace(
                    "{tempo}",
                    TempoUtils().getTempo(tempoDuracaoDC)
                ).replace("{id}", id)
            )
        channel.sendMessage(embed.build()).complete()
    }

}