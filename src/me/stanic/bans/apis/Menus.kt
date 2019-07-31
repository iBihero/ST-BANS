package me.stanic.bans.apis

import me.stanic.bans.Main
import me.stanic.bans.utils.ItemBuilder
import me.stanic.bans.utils.TempoUtils
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player

class Menus {

    fun confirmarPunicaoBan(
        p: Player,
        nick: String,
        motivo: String,
        tipo: String,
        data: String,
        horario: String
    ) {
        val inv = Bukkit.createInventory(null, 5 * 9, "§aConfirme a punição - Ban")
        inv.setItem(
            13,
            ItemBuilder(Material.EXP_BOTTLE).setName("§aVerificação").addLores(
                listOf(
                    "",
                    "§bInformações sobre a punição",
                    "",
                    "§fTipo: §e$tipo",
                    "§fPunido(a): §7$nick",
                    "§fStaffer: §7${p.name}",
                    "§fMotivo: §7$motivo",
                    "§fData: §7$data",
                    "§fHorário: §7$horario",
                    "§fDuração: §7Permanente",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            21,
            ItemBuilder(Material.PAPER).setName("§eSilenciosamente").addLores(
                listOf(
                    "",
                    "§fClique para punir sem nenhum anúncio",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            22,
            ItemBuilder(Material.PAPER).setName("§eSem anúncio no Discord").addLores(
                listOf(
                    "",
                    "§fClique para punir sem enviar mensagens para o Discord",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            23,
            ItemBuilder(Material.PAPER).setName("§eAnunciar punição").addLores(
                listOf(
                    "",
                    "§fClique para punir com anúncios em todos os lugares",
                    ""
                )
            ).toItemStack()
        )
        p.openInventory(inv)
    }

    fun confirmarPunicaoBanTemp(
        p: Player,
        nick: String,
        motivo: String,
        tipo: String,
        data: String,
        horario: String,
        tempo: String
    ) {
        val inv = Bukkit.createInventory(null, 6 * 9, "§aConfirme a punição - Tempban")
        inv.setItem(
            13,
            ItemBuilder(Material.EXP_BOTTLE).setName("§aVerificação").addLores(
                listOf(
                    "",
                    "§bInformações sobre a punição",
                    "",
                    "§fTipo: §e$tipo",
                    "§fPunido(a): §7$nick",
                    "§fStaffer: §7${p.name}",
                    "§fMotivo: §7$motivo",
                    "§fData: §7$data",
                    "§fHorário: §7$horario",
                    "§fDuração: §7$tempo | Escolha o tipo nos papeis",
                    ""
                )
            ).toItemStack()
        )

        inv.setItem(
            20,
            ItemBuilder(Material.PAPER).setName("§eSegundos").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo segundos",
                    "§fSem nenhum anúncio",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            21,
            ItemBuilder(Material.PAPER).setName("§eMinutos").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo minutos",
                    "§fSem nenhum anúncio",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            22,
            ItemBuilder(Material.PAPER).setName("§eHoras").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo horas",
                    "§fSem nenhum anúncio",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            23,
            ItemBuilder(Material.PAPER).setName("§eDias").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo dias",
                    "§fSem nenhum anúncio",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            24,
            ItemBuilder(Material.PAPER).setName("§eSemanas").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo semanas",
                    "§fSem nenhum anúncio",
                    ""
                )
            ).toItemStack()
        )

        inv.setItem(
            29,
            ItemBuilder(Material.PAPER).setName("§eSegundos").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo segundos",
                    "§fSem enviar mensagens para o Discord",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            30,
            ItemBuilder(Material.PAPER).setName("§eMinutos").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo minutos",
                    "§fSem enviar mensagens para o Discord",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            31,
            ItemBuilder(Material.PAPER).setName("§eHoras").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo horas",
                    "§fSem enviar mensagens para o Discord",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            32,
            ItemBuilder(Material.PAPER).setName("§eDias").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo dias",
                    "§fSem enviar mensagens para o Discord",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            33,
            ItemBuilder(Material.PAPER).setName("§eSemanas").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo semanas",
                    "§fSem enviar mensagens para o Discord",
                    ""
                )
            ).toItemStack()
        )

        inv.setItem(
            38,
            ItemBuilder(Material.PAPER).setName("§eSegundos").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo segundos",
                    "§fCom anúncios em todos os lugares",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            39,
            ItemBuilder(Material.PAPER).setName("§eMinutos").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo minutos",
                    "§fCom anúncios em todos os lugares",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            40,
            ItemBuilder(Material.PAPER).setName("§eHoras").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo horas",
                    "§fCom anúncios em todos os lugares",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            41,
            ItemBuilder(Material.PAPER).setName("§eDias").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo dias",
                    "§fCom anúncios em todos os lugares",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            42,
            ItemBuilder(Material.PAPER).setName("§eSemanas").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo semanas",
                    "§fCom anúncios em todos os lugares",
                    ""
                )
            ).toItemStack()
        )

        p.openInventory(inv)
    }

    fun confirmarPunicaoMute(
        p: Player,
        nick: String,
        motivo: String,
        tipo: String,
        data: String,
        horario: String
    ) {
        val inv = Bukkit.createInventory(null, 5 * 9, "§aConfirme a punição - Mute")
        inv.setItem(
            13,
            ItemBuilder(Material.EXP_BOTTLE).setName("§aVerificação").addLores(
                listOf(
                    "",
                    "§bInformações sobre a punição",
                    "",
                    "§fTipo: §e$tipo",
                    "§fPunido(a): §7$nick",
                    "§fStaffer: §7${p.name}",
                    "§fMotivo: §7$motivo",
                    "§fData: §7$data",
                    "§fHorário: §7$horario",
                    "§fDuração: §7Permanente",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            21,
            ItemBuilder(Material.PAPER).setName("§eSilenciosamente").addLores(
                listOf(
                    "",
                    "§fClique para punir sem nenhum anúncio",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            22,
            ItemBuilder(Material.PAPER).setName("§eSem anúncio no Discord").addLores(
                listOf(
                    "",
                    "§fClique para punir sem enviar mensagens para o Discord",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            23,
            ItemBuilder(Material.PAPER).setName("§eAnunciar punição").addLores(
                listOf(
                    "",
                    "§fClique para punir com anúncios em todos os lugares",
                    ""
                )
            ).toItemStack()
        )
        p.openInventory(inv)
    }

    fun confirmarPunicaoMuteTemp(
        p: Player,
        nick: String,
        motivo: String,
        tipo: String,
        data: String,
        horario: String,
        tempo: String
    ) {
        val inv = Bukkit.createInventory(null, 6 * 9, "§aConfirme a punição - Tempmute")
        inv.setItem(
            13,
            ItemBuilder(Material.EXP_BOTTLE).setName("§aVerificação").addLores(
                listOf(
                    "",
                    "§bInformações sobre a punição",
                    "",
                    "§fTipo: §e$tipo",
                    "§fPunido(a): §7$nick",
                    "§fStaffer: §7${p.name}",
                    "§fMotivo: §7$motivo",
                    "§fData: §7$data",
                    "§fHorário: §7$horario",
                    "§fDuração: §7$tempo | Escolha o tipo nos papeis",
                    ""
                )
            ).toItemStack()
        )

        inv.setItem(
            20,
            ItemBuilder(Material.PAPER).setName("§eSegundos").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo segundos",
                    "§fSem nenhum anúncio",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            21,
            ItemBuilder(Material.PAPER).setName("§eMinutos").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo minutos",
                    "§fSem nenhum anúncio",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            22,
            ItemBuilder(Material.PAPER).setName("§eHoras").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo horas",
                    "§fSem nenhum anúncio",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            23,
            ItemBuilder(Material.PAPER).setName("§eDias").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo dias",
                    "§fSem nenhum anúncio",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            24,
            ItemBuilder(Material.PAPER).setName("§eSemanas").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo semanas",
                    "§fSem nenhum anúncio",
                    ""
                )
            ).toItemStack()
        )

        inv.setItem(
            29,
            ItemBuilder(Material.PAPER).setName("§eSegundos").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo segundos",
                    "§fSem enviar mensagens para o Discord",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            30,
            ItemBuilder(Material.PAPER).setName("§eMinutos").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo minutos",
                    "§fSem enviar mensagens para o Discord",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            31,
            ItemBuilder(Material.PAPER).setName("§eHoras").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo horas",
                    "§fSem enviar mensagens para o Discord",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            32,
            ItemBuilder(Material.PAPER).setName("§eDias").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo dias",
                    "§fSem enviar mensagens para o Discord",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            33,
            ItemBuilder(Material.PAPER).setName("§eSemanas").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo semanas",
                    "§fSem enviar mensagens para o Discord",
                    ""
                )
            ).toItemStack()
        )

        inv.setItem(
            38,
            ItemBuilder(Material.PAPER).setName("§eSegundos").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo segundos",
                    "§fCom anúncios em todos os lugares",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            39,
            ItemBuilder(Material.PAPER).setName("§eMinutos").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo minutos",
                    "§fCom anúncios em todos os lugares",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            40,
            ItemBuilder(Material.PAPER).setName("§eHoras").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo horas",
                    "§fCom anúncios em todos os lugares",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            41,
            ItemBuilder(Material.PAPER).setName("§eDias").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo dias",
                    "§fCom anúncios em todos os lugares",
                    ""
                )
            ).toItemStack()
        )
        inv.setItem(
            42,
            ItemBuilder(Material.PAPER).setName("§eSemanas").addLores(
                listOf(
                    "",
                    "§fClique para punir por $tempo semanas",
                    "§fCom anúncios em todos os lugares",
                    ""
                )
            ).toItemStack()
        )

        p.openInventory(inv)
    }

    fun checkPlayer(
        p: Player,
        nick: String
    ) {
        val info = Main.instance!!.cache[nick]!!
        val inv = Bukkit.createInventory(null, 3 * 9, "§aPunição dê $nick")
        inv.setItem(
            13,
            ItemBuilder(Material.EXP_BOTTLE).setName("§a${info.tipo}").addLores(
                listOf(
                    "",
                    "§bInformações sobre a punição",
                    "",
                    "§fStaffer: §7${p.name}",
                    "§fMotivo: §7${info.motivo}",
                    "§fData: §7${info.data}",
                    "§fHorário: §7${info.horario}",
                    "§fDuração: §7${if (info.tempo == null) "Permanente" else TempoUtils().getTempo(info.tempo!! - System.currentTimeMillis())}",
                    "§fID: §7${info.id}",
                    ""
                )
            ).toItemStack()
        )
        p.openInventory(inv)
    }
}