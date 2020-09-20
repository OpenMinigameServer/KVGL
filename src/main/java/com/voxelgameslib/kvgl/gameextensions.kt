package com.voxelgameslib.kvgl

import com.voxelgameslib.voxelgameslib.api.feature.Feature
import com.voxelgameslib.voxelgameslib.api.game.AbstractGame
import com.voxelgameslib.voxelgameslib.api.game.Game
import com.voxelgameslib.voxelgameslib.api.game.GameMode
import com.voxelgameslib.voxelgameslib.api.phase.Phase

// Maybe change the "get" behaviour of this, or maybe just make a function?
inline var Game.minAndMaxPlayers: Int
    get() = if (minPlayers == maxPlayers) minPlayers else -1
    set(value) {
        minPlayers = value
        maxPlayers = value
    }


inline fun <reified T : Game> newGameMode(name: String): GameMode {
    return GameMode(name, T::class.java)
}


inline fun <reified T : Phase> Game.createPhase(block: (T) -> Unit = { }): T {
    return createPhase(T::class.java).apply(block)
}

inline fun <reified T : Feature> Game.createFeature(phase: Phase): T {
    return createFeature(T::class.java, phase)
}

inline fun AbstractGame.buildPhases(first: Phase? = null, block: PhaseBuilder.() -> Unit) {
    PhaseBuilder(first, this).apply(block)
}


class PhaseBuilder @PublishedApi internal constructor(first: Phase?, private val absGame: AbstractGame) {

    private var prev: Phase? = first

    init {
        prev?.let { absGame.activePhase = it }
    }


    operator fun Phase.unaryPlus() {
        if (prev == null) {
            absGame.activePhase = this
        } else {
            prev?.setNextPhase(this)
        }

        prev = this
    }

}