package world

/**
 * Contains [Planet] and types of existed object in it
 */
data class Space(val planets: MutableList<Planet>,
                 override var size: Int = Int.MAX_VALUE): Sizeble {

    companion object { val HELL = Planet("Hell", -1 to -1, Space.CELESTIAL_BODY.SUN) }

    /**
     * Destroy all planets
     */
    fun bigBang() = planets.clear()

    /**
     * Types of [Planet]
     */
    enum class CELESTIAL_BODY { STAR, SUN, HABITABLE }

    /**
     * Types of [Creature]
     */
    enum class CREATURE_TYPE { HUMAN, DOG, VOGON, JATRAVARTID, GOLGAFRINCHAN, HOOLOOVOO, BETELGEUSIAN, DENTRASSISS }
}