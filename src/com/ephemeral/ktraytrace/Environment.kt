package com.ephemeral.ktraytrace

data class Environment(
        val from : Vector,
        val at : Vector,
        val up : Vector = Vector.YAXIS,
        val fov : Double,
        val width : Double,
        val height : Double,
        val sample : Int,
        val shapes : MutableList<IShape>
)