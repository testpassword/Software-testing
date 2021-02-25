package world

class Dog(name: String,
          location: Planet,
          override var size: Int): Creature(name, Space.CREATURE_TYPE.DOG, location, size) {

    fun eat(obj: Sizeble) {
        affect(obj) {
            println("Eating...")
            if (obj is Moveable)
                if (obj.size > this.size) throw Exception("Too big!") else obj.location = Space.HELL
            else throw Exception("Dog is not god")
            return@affect Unit
        }
    }
}