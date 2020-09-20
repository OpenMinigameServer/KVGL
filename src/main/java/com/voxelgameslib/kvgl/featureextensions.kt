package com.voxelgameslib.kvgl

import com.voxelgameslib.voxelgameslib.api.feature.Feature
import com.voxelgameslib.voxelgameslib.api.feature.features.MapFeature
import kotlin.reflect.KClass

// Look into changing base method names
var MapFeature.shouldUnload: Boolean
    get() = shouldUnload()
    set(value) = setShouldUnload(value)


fun depend(vararg clazz: KClass<out Feature>): List<Class<out Feature>> { // Not sure why this doesn't coerce the type automatically...
    return clazz.map { it.java }
}