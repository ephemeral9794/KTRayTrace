package com.ephemeral.ktraytrace

data class Environment(
        val from : Vector,
        val at : Vector,
        val up : Vector = Vector.YAXIS,
        val fov : Double,
        var samples : Int,
        val shapes : MutableList<IShape>
) {
    companion object {
        val Default = Environment(
                from = Vector(5.0,5.0,5.0),
                at = Vector(0.0,0.0,0.0),
                up = Vector.YAXIS,
                fov = 30.0,
                samples = 32,
                shapes = mutableListOf(
                        Sphere(Vector( -0.5, 0.0, 0.0), 1.0, Vector(1.0, 0.0, 0.0)),
                        Sphere(Vector(0.5, 0.0, 0.0), 1.0, Vector(0.0, 1.0, 0.0))
                )
        )
        val CornellBox = Environment(
                from = Vector(50.0,52.0,295.6),
                at = Vector(50.0,51.957388,294.6),
                up = Vector.YAXIS,
                fov = 30.0,
                samples = 1,
                shapes = mutableListOf(
                        Sphere(Vector(-1e5 + 1,40.8,81.6), 1e5, Vector(0.75,0.25,0.25)),
                        Sphere(Vector(1e5 + 99,40.8,81.6), 1e5, Vector(0.25,0.25,0.75)),
                        Sphere(Vector(50.0,40.8,-1e5), 1e5, Vector(0.75)),
                        Sphere(Vector(50.0,-1e5,81.6), 1e5, Vector(0.75)),
                        Sphere(Vector(50.0,1e5 + 81.6,81.6), 1e5, Vector(0.75)),
                        Sphere(Vector(27.0,16.5,47.0), 16.5, Vector(0.999)),
                        Sphere(Vector(73.0,16.5,78.0), 16.5, Vector(0.999)),
                        Sphere(Vector(50.0,681.6 - 0.27,81.6), 600.0, Vector())
                )
        )
    }
}