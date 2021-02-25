package world

/**
 * Allow object to move from [Planet] to [Planet]
 */
interface Moveable {

    var location: Planet
    infix fun move(dist: Planet)
}


interface Sizeble { var size: Int }