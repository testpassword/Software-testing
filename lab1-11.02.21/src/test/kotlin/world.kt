import world.*
import org.junit.jupiter.api.*
import kotlin.test.*
import org.junit.jupiter.api.Test

private fun createPlanet() = Planet("test_planet", 1 to 1, Space.CELESTIAL_BODY.HABITABLE)

private fun createTester() = Creature("tester", Space.CREATURE_TYPE.HUMAN, createPlanet())

private fun createShip() = Ship("test_ship", createPlanet(), emptyMap())

/**
 * Tests for [Ship]
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShipTest {

    @Test fun `Test hire creature to the ship's crew`() {
        val tester = createTester()
        val testShip = createShip()
        testShip hire (tester to Ship.RANK.SLAVE)
        assert(testShip.crew.contains(tester))
    }

    @Test fun `Test fire creature from ship's crew`() {
        val tester = createTester()
        val testShip = createShip()
        testShip fire tester
        assert(testShip.crew.contains(tester).not())
    }

    @Test fun `Test compare two ship's`() {
        val testPlanet = createPlanet()
        val ship1 = Ship("ship1", testPlanet, mapOf(
            Creature("t1", Space.CREATURE_TYPE.HOOLOOVOO, testPlanet) to Ship.RANK.CAPTAIN,
            Creature("t2", Space.CREATURE_TYPE.BETELGEUSIAN, testPlanet) to Ship.RANK.PASSENGER))
        val ship2 = Ship("ship2", createPlanet(), mapOf(
            Creature("t1", Space.CREATURE_TYPE.HOOLOOVOO, testPlanet) to Ship.RANK.CAPTAIN,
            Creature("t2", Space.CREATURE_TYPE.BETELGEUSIAN, testPlanet) to Ship.RANK.PASSENGER,
            Creature("te", Space.CREATURE_TYPE.DENTRASSISS, testPlanet) to Ship.RANK.MECHANIC))
        assertEquals(-1, ship1 compareTo ship2)
    }
        @Test @AfterAll
        fun `Test move all ship's creatures`() {
            val originPlanet = createPlanet()
            val distinationPlanet = Planet("dist_planet", 1 to 1, Space.CELESTIAL_BODY.HABITABLE)
            val testShip = Ship("test_ship", originPlanet, mapOf(
                Creature("t1", Space.CREATURE_TYPE.HOOLOOVOO, originPlanet) to Ship.RANK.JANITOR,
                Creature("t2", Space.CREATURE_TYPE.BETELGEUSIAN, originPlanet) to Ship.RANK.PILOT
            ))
            testShip move distinationPlanet
            assert(testShip.crew.map { it.key.location }.all { it == distinationPlanet })
        }

    @Test fun `Move to current planet`() {
        val earth = Planet("earth", 1 to 1, Space.CELESTIAL_BODY.HABITABLE)
        val testShip = Ship("ship", earth, emptyMap())
        testShip.move(earth)
        assertEquals(earth, testShip.location)
    }
}

/**
 * Tests for [Creature]
 */
class CreatureTest {

    @Test fun `Test creature mutation process`() {
        val tester = createTester()
        val newType = Space.CREATURE_TYPE.DOG
        tester mutate newType
        assertEquals(newType, tester.type, "After mutate type changes incorrect")
    }

    @Test fun `Test move creature to planets`() {
        val tester = createTester()
        assertAll(
            {
                val prohibitedPlanet = Planet("prohibited", 2 to 1, Space.CELESTIAL_BODY.SUN)
                tester move prohibitedPlanet
                assertNotEquals(prohibitedPlanet, tester.location, "The creature unexpectedly moved to an uninhabited planet without technology") },
            {
                val permittedPlanet = Planet("permitted", 2 to 2, Space.CELESTIAL_BODY.HABITABLE)
                tester move permittedPlanet
                assertEquals(permittedPlanet, tester.location, "Tester unexpectedly didn't move to permitted planet")
            }
        )
    }

    @Test fun `Test affect creature process to another object`() {
        val tester = createTester()
        val testObject = createPlanet()
        tester.affect(testObject) { (it as Planet).coordinates = -1 to -1 }
        assertEquals(-1 to -1, testObject.coordinates, "Creature correctly affect on another object")
    }

    @Test fun `Test dog can eat`() {
        val victim = createShip().apply { size = 200 }
        assertAll(
            {
                val bigDog = Dog("big_dog", createPlanet(), 10000)
                bigDog.eat(victim)
                assertEquals(Space.HELL, victim.location)
            },
            {
                val littleDog = Dog("little_dog", createPlanet(), 5)
                assertThrows<Exception> { littleDog.eat(victim) }
            }
        )
    }
}


/**
 * Tests for [Space]
 */
class SpaceTest {

    @Test fun `Test explode space`() {
        val planets = mutableListOf(createPlanet(), createPlanet(), createPlanet())
        val testSpace = Space(planets)
        testSpace.bigBang()
        assert(testSpace.planets.isEmpty())
    }
}