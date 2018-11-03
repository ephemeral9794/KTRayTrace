package com.ephemeral.ktraytrace

import java.util.*

class Scene(val env : Environment, val image : Image, val back : Color = Color.BLACK) {
    private val world : World
    private val camera : Camera
    init {
        world = World(env)
        camera = Camera(env.from, env.at, env.up, env.fov, (env.width / env.height))
    }
    fun render() {
        val random = Random()
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                var c : Color = Color(0.0, 0.0, 0.0)
                for (s in 0 until env.sample) {
                    val u = (x + random.nextDouble()) / image.width
                    val v = (image.height - y + random.nextDouble()) / image.height
                    val ray = camera.get(u, v)

                    val hit = world.intersect(ray, 1e-4, 1e10)
                    if (hit !== null) {
                        val color = (hit.obj as Sphere).reflect * dot(hit.normal, -ray.direct)
                        //println("[$x, $y] : $color")
                        //image[x, y] = Color(color.x, color.y, color.z)
                        c += Color(color.x, color.y, color.z)
                    }
                }
                image[x, y] = c / env.sample
            }
        }
    }
}