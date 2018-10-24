package com.ephemeral.ktraytrace

class World : IShape {
    val shapes : MutableList<IShape> = mutableListOf()
    fun add(obj : IShape) = shapes.add(obj)
    override fun intersect(ray: Ray, near: Float, far: Float): Hit? {
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