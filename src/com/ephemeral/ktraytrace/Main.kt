package com.ephemeral.ktraytrace

fun usage() {
    println("KTRayTrace [output]")
}

fun main(args: Array<String>) {
    // file path from arguments[0]
    val filename : String
    if (!args.isEmpty()) {
        filename = args[0]
    } else {
        usage()
        return
    }
    println("file : $filename")

    // image
    val image = Image(1200, 720)
    val scene = Scene()
    scene.shapes.add(Sphere(Vector.ZERO, 1.0f))
    for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            val ray = Ray()
            ray.origin = Vector(2.0f * x / image.width - 1.0f, 2.0f * y / image.height - 1.0f, 5.0f)
            ray.direct = Vector(0.0f, 0.0f, -1.0f)

            val hit = scene.intersect(ray, 0.0001f, 10000.0f)
            if (hit !== null) {
                image[x,y] = Color.RED
            } else {
                image[x,y] = Color.BLACK
            }
        }
    }
    image.export(filename)
}