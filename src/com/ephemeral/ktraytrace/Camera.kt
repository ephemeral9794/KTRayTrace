package com.ephemeral.ktraytrace

import kotlin.math.tan

class Camera(val from : Vector, val at : Vector, val up : Vector = Vector.YAXIS, val fov : Double, val aspect : Double){
    val wE : Vector
    val uE : Vector
    val vE : Vector
    init {
        wE = (from - at).normalize()
        uE = cross(up, wE).normalize()
        vE = cross(wE, uE)
    }

    fun get(u : Double, v: Double) : Ray {
        val tf = tan(radian(fov) * 0.5)
        val rpx = 2.0 * u - 1.0
        val rpy = 2.0 * v - 1.0
        val w = Vector(aspect * tf * rpx, tf * rpy, -1.0).normalize()
        return Ray(from, (uE * w.x + vE * w.y + wE * w.z))
    }
}