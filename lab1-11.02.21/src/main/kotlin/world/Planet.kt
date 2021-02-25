package world

/**
 * Represents planet in [Space]
 */
data class Planet(val name: String,
                  var coordinates: Pair<Int, Int>,
                  var type: Space.CELESTIAL_BODY, override var size: Int = 0): Sizeble