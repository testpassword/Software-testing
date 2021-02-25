package world

/**
 * Space ship for moving between planets
 */
data class Ship(val name: String,
                override var location: Planet,
                var crew: Map<Creature, RANK> = mutableMapOf(),
                override var size: Int = 0): Moveable, Comparable<Ship>, Sizeble {

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