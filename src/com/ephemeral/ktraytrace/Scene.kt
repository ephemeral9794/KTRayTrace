package com.ephemeral.ktraytrace

import kotlin.math.*

//fun tonemap(v : Float) : Int = clamp((v.pow(1.0f/2.2f) * 255.0f).toInt(), 0, 255)
fun tonemap(v : Vector) : Color = Color(
        clamp(v.x.pow(1.0f/2.2f), 0.0f, 1.0f),
        clamp(v.y.pow(1.0f/2.2f), 0.0f, 1.0f),
        clamp(v.z.pow(1.0f/2.2f), 0.0f, 1.0f)
)

class Scene(var image : Image, val background : Color) {
    var camera : Camera
    val world : World
    init {
        camera = Camera(Vector(5.0f,5.0f,5.0f), Vector(0.0f, 0.0f, 0.0f), Vector(0.0f, 1.0f, 0.0f), 30.0f, (image.width.toFloat()/image.height))
        world = World()
        world.add(Sphere(Vector(-0.5f, 0.0f, 0.0f), 1.0f))
        world.add(Sphere(Vector(0.5f, 0.0f, 0.0f), 1.0f))
    }
    fun render(near : Float, far : Float) {
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                //val ray = Ray()
                //ray.origin = Vector(2.0f * x / image.width - 1.0f, 2.0f * y / image.height - 1.0f, 5.0f)
                //ray.direct = Vector(0.0f, 0.0f, -1.0f)
                val u = x.toFloat() / (image.width)
                val v = y.toFloat() / (image.height)
                val ray = camera.get(u, v)

                val hit = world.intersect(ray, near, far)
                if (hit !== null) {
                    val normal = abs(hit.normal)
                    image[x,y] = Color(tonemap(normal))
                } else {
                    image[x,y] = background
                }
            }
        }
    }
}