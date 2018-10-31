package com.ephemeral.ktraytrace

data class Ray(var origin : Vector, var direct : Vector) {
    constructor() : this(Vector(), Vector())
}