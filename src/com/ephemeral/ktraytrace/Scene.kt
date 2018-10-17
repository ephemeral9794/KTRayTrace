package com.ephemeral.ktraytrace

class Scene : Shape {
    val shapes : MutableList<Shape> = mutableListOf()
    override fun intersect(ray : Ray, near : Float, far : Float) : Hit? {
        var hit : Hit? = null
        var max : Float = far
        for (shape in shapes) {
            val h = shape.intersect(ray, near, max)
            if (h === null) continue
            hit = h
            max = hit.t
        }
        return hit
    }
}