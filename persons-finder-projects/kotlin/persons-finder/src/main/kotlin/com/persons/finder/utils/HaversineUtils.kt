package com.persons.finder.utils

object HaversineUtils {

    private const val EARTH_RADIUS = 6371

    private fun haversine(value: Double): Double {
        return Math.pow(Math.sin(value / 2), 2.0)
    }

    fun calculateDistance(
        startLat: Double,
        startLong: Double,
        endLat: Double,
        endLong: Double
    ): Double {
        val dLat = Math.toRadians(endLat - startLat)
        val dLong = Math.toRadians(endLong - startLong)

        val startLatRad = Math.toRadians(startLat)
        val endLatRad = Math.toRadians(endLat)

        val a = haversine(dLat) + Math.cos(startLatRad) * Math.cos(endLatRad) * haversine(dLong)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

        return EARTH_RADIUS * c
    }
}
