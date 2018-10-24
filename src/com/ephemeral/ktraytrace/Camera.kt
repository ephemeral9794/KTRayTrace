package com.ephemeral.ktraytrace

import kotlin.math.tan

class Camera(from : Vector, at : Vector, up : Vector, fov : Float, aspect : Float) {
    private val origin : Vector
    private val u : Vector
    private val v : Vector
    private val w : Vector
    private val halfH : Float
    private val halfW : Float

    init {
        origin = from
        w = (from - at).normalize()
        u = cross(up, w).normalize()
        v = cross(w, u)
        halfH = tan(radian(fov) * 0.5f)
        halfW = aspect * halfH
    }

    fun get(x :Float, y : Float) : Ray {
        val direct = Vector(halfW * (x * 2.0f - 1), halfH * (y * 2.0f - 1), -1.0f).normalize()
        return Ray(origin, u * direct.x + v * direct.y + w * direct.z)
    }

    override fun toString(): String {
        return "[$origin, $u, $v, $w, $halfH, $halfW]"
    }

}