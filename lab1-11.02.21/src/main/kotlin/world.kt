/**
 * Allow object to move from [Planet] to [Planet]
 */
interface Moveable { infix fun move(dist: Planet) }


/**
 * Space ship for moving between planets
 */
data class Ship(val name: String, var location: Planet, var crew: Map<Creature, RANK> = mutableMapOf()): Moveable, Comparable<Ship> {

    /**
     * Add [Creature] to [Ship.crew] on [RANK]
     */
    infix fun hire(candidate: Pair<Creature, RANK>) { this.crew = this.crew + candidate }

    /**
     * Remove [Creature] from [Ship.crew]
     */
    infix fun fire(c: Creature) { this.crew = crew - c }

    /**
     * Move [Ship] and all it's [Creature] to another [Planet]
     */
    override infix fun move(dist: Planet) {
        this.location = dist
        crew.forEach { it.key.move(dist) }
    }

    override infix fun compareTo(other: Ship) = this.crew.size - other.crew.size

    /**
     * Available positions on ship
     */
    enum class RANK { CAPTAIN, PILOT, PASSENGER, MECHANIC, SLAVE, JANITOR }
}


/**
 * Contains [Planet] and types of existed object in it
 */
data class Space(val planets: MutableList<Planet>) {

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


/**
 * Represents planet in [Space]
 */
data class Planet(val name: String, var coordinates: Pair<Int, Int>, var type: Space.CELESTIAL_BODY)


/**
 * Repesents the living [Creature] in [Space]
 */
data class Creature(val name: String, var type: Space.CREATURE_TYPE, var location: Planet): Moveable {

    /**
     * Change [Creature.type]
     */
    infix fun mutate(t: Space.CREATURE_TYPE) { this.type = t }

    /**
     * Move [Creature] to another [Planet]. Without [Ship] he can move only to [Space.CELESTIAL_BODY.HABITABLE]
     */
    override infix fun move(dist: Planet) { if (dist.type == Space.CELESTIAL_BODY.HABITABLE) this.location = dist }

    /**
     * Universal action for affecting [Creature] on every possible object
     */
    fun affect(obj: Any, f: (Any) -> Unit): Any { return f(obj) }
}