package com.ephemeral.ktraytrace

class World(val env : Environment) : IShape {
    //fun add(shape: IShape) = shapes.add(shape)
    override fun intersect(ray : Ray, near : Double, far : Double) : Hit? {
        var hit : Hit? = null
        var max : Double = far
        for (shape in env.shapes) {
            val h = shape.intersect(ray, near, max)
            if (h === null)
                continue
            hit = h
            max = h.t
        }
        return hit
    }
}