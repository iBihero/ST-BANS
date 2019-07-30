package me.stanic.bans.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TempoUtils {

    fun getTempo(time: Long): String {
        val varsegundos = time / 1000L % 60L
        val varminutos = time / 60000L % 60L
        val varhoras = time / 3600000L % 24L
        val vardias = time / 86400000L % 7L
        val segundos = varsegundos.toString().replace("-".toRegex(), "")
        val minutos = varminutos.toString().replace("-".toRegex(), "")
        val horas = varhoras.toString().replace("-".toRegex(), "")
        val dias = vardias.toString().replace("-".toRegex(), "")
        if (dias == "0" && horas == "0" && minutos == "0") {
            return "$segundos Segundo(s)"
        }
        if (dias == "0" && horas == "0") {
            return "$minutos:$segundos"
        }
        return if (dias == "0") {
            "$horas:$minutos:$segundos"
        } else "$dias:$horas:$minutos:$segundos"
    }

    companion object {

        private val dtf = DateTimeFormatter.ofPattern("EEEE")
        private val now = LocalDateTime.now()

        private val dtf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        private val now2 = LocalDateTime.now()

        private val dtf3 = DateTimeFormatter.ofPattern("HH:mm")
        private val now3 = LocalDateTime.now()

        private val dia = dtf.format(now)
            .replace("Sunday", "Domingo").replace("Monday", "Segunda-feira")
            .replace("Tuesday", "Terça-feira").replace("Wednesday", "Quarta-feira")
            .replace("Thursday", "Quinta-feira").replace("Friday", "Sexta-feira")
            .replace("Saturday", "Sábado")
        private val data = dtf2.format(now2)
        private val hora = dtf3.format(now3)

        fun getHorario(): String {
            return hora
        }

        fun getDataEDia(): String {
            return "$dia $data"
        }

    }

}