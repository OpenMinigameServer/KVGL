package com.voxelgameslib.kvgl

import com.voxelgameslib.voxelgameslib.VoxelGamesLib
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger


@PublishedApi
internal object Bridge { // no way this can be accessed before VGL is loaded

    val vgl: VoxelGamesLib = JavaPlugin.getPlugin(VoxelGamesLib::class.java)

}


inline fun <reified T : Any> logByClass(): Lazy<Logger> = lazy { Logger.getLogger(T::class.java.name) }

inline fun <reified T : Any> injected(): Lazy<T> = lazy { Bridge.vgl.injector.getInstance(T::class.java) }