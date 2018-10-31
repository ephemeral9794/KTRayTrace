package com.ephemeral.ktraytrace

import kotlin.system.measureNanoTime

class Scene(val env : Environment, val image : Image, val back : Color = Color.BLACK) {
    private val world : World
    init {
        world = World(env)
    }
    fun render() {
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val u = x.toDouble() / image.width
                val v = (image.height - y).toDouble() / image.height
                val ray = env.camera.get(u, v)

                val hit = world.intersect(ray, 1e-4, 1e10)
                if (hit !== null) {
                    val color = (hit.obj as Sphere).reflect * dot(hit.normal, -ray.direct)
                    image[x, y] = Color(color.x, color.y, color.z)
                }
            }
        }
    }
}