package bobsrockets {
  package navigation {
    //in package bobsrockets.navigation
    private[bobsrockets] class Navigator {
      // No need to say bobsrockets.navigation.StarMap
      val map = new StarMap
      /*A modifier
      of the form private[X] or protected[X] means that access is private or
      protected “up to” X , where X designates some enclosing package, class or
      singleton object.
      */
      protected[navigation] def useStarChart(): Unit = {}

      class LegOfJourney {
        private[Navigator] val distance = 100
      }

      private[this] var speed = 200
    }
    class StarMap

    package tests {
      //in package bobsrockets.navigation.tests
      class NavigatorSuite
    }

    package launch {
      class Booster1
    }

    class MissionControl {
      val booster1 = new launch.Booster1
      val booster2 = new bobsrockets.launch.Booster2
      val booster3 = new _root_.launch.Booster3
    }
  }

  class Ship {
    // No need to say bobsrockets.navigation.Navigator
    val nav = new navigation.Navigator
  }

  package fleets {
    class Fleet {
      // No need to say bobsrockets.Ship
      def addShip() { new Ship }
    }
  }

  package launch {
    class Booster2

    import navigation._
    object Vehicle {
      private[launch] val guide = new Navigator
    }
  }
}
