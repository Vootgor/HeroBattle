package com.storyteller.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/narrator")
class NarratorController {

    @GetMapping
    fun getNarration(
        @RequestParam heroName: String,
        @RequestParam weapon: String,
        @RequestParam background: String,
        @RequestParam clothes: String
    ): NarratorComments {

        val introductions = listOf(
            "$heroName, выросший из $background, появился на поле битвы. Его $clothes придавали уверенности.",
            "История $background закалила $heroName. В $clothes и с $weapon он был готов к испытаниям.",
            "$heroName, чей путь начался в $background, вышел вперёд. Его $clothes говорили о мужестве."
        )

        val attacks = listOf(
            "$heroName, помня уроки $background, бросился в атаку с $weapon.",
            "$heroName нанёс удар $weapon, словно сам $background вел его рукой.",
            "$heroName поднял $weapon, и сила $background отразилась в его движении."
        )

        val defeats = listOf(
            "$heroName пал, но даже в поражении память о $background жила в его $clothes.",
            "Поражение постигло $heroName. Однако наследие $background не исчезло.",
            "$heroName опустил $weapon. Его путь из $background обернулся тяжёлым испытанием."
        )

        val wins = listOf(
            "$heroName победил. Его $weapon сиял, а прошлое из $background стало источником силы.",
            "$heroName стоял в $clothes как победитель. Его история $background теперь вдохновляла других.",
            "$heroName торжествовал. Каждый удар $weapon был отголоском $background."
        )

        return NarratorComments(
            onIntroduction = introductions.random(),
            onAttack = attacks.random(),
            onDefeat = defeats.random(),
            onWin = wins.random()
        )
    }
}

data class NarratorComments(
    val onIntroduction: String,
    val onAttack: String,
    val onDefeat: String,
    val onWin: String
)
