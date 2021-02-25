package world

/**
 * Represents the living [Creature] in [Space]
 */
open class Creature(val name: String,
                    var type: Space.CREATURE_TYPE,
                    override var location: Planet,
                    override var size: Int = 0): Moveable, Sizeble {

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