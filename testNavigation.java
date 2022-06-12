// --== CS400 File Header Information ==--
// Name: Weiyu Kong
// Email: wkong32@wisc.edu
// Team: IC
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * Tests the implementation of IC Navigation for the Project Three
 */
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testNavigation {

  @BeforeEach
  /**
   * Initiate a navigation map
   */
  public void createGraph() {
    Navigation.locations = new LinkedList<Location>();
    Navigation.locations.add(new Location("0", false));
    Navigation.locations.add(new Location("1", false));
    Navigation.locations.add(new Location("2", false));
    Navigation.locations.add(new Location("3", false));
    Navigation.locations.add(new Location("4", false));
    Navigation.locations.add(new Location("5", false));
    Navigation.locations.add(new Location("6", false));
    Navigation.locations.add(new Location("7", false));
    Navigation.locations.add(new Location("8", true)); // only the 8th position can provide food

    for (int i = 0; i < Navigation.locations.size(); i++) {
      Navigation.map.insertVertex(Navigation.locations.get(i));
    }
    Navigation.map.insertEdge(Navigation.locations.get(0), Navigation.locations.get(1), 4);
    Navigation.map.insertEdge(Navigation.locations.get(1), Navigation.locations.get(0), 4);
    Navigation.map.insertEdge(Navigation.locations.get(0), Navigation.locations.get(7), 8);
    Navigation.map.insertEdge(Navigation.locations.get(7), Navigation.locations.get(0), 8);
    Navigation.map.insertEdge(Navigation.locations.get(1), Navigation.locations.get(7), 11);
    Navigation.map.insertEdge(Navigation.locations.get(7), Navigation.locations.get(1), 11);
    Navigation.map.insertEdge(Navigation.locations.get(1), Navigation.locations.get(2), 8);
    Navigation.map.insertEdge(Navigation.locations.get(2), Navigation.locations.get(1), 8);
    Navigation.map.insertEdge(Navigation.locations.get(2), Navigation.locations.get(8), 2);
    Navigation.map.insertEdge(Navigation.locations.get(8), Navigation.locations.get(2), 2);
    Navigation.map.insertEdge(Navigation.locations.get(8), Navigation.locations.get(6), 6);
    Navigation.map.insertEdge(Navigation.locations.get(6), Navigation.locations.get(8), 6);
    Navigation.map.insertEdge(Navigation.locations.get(7), Navigation.locations.get(8), 7);
    Navigation.map.insertEdge(Navigation.locations.get(8), Navigation.locations.get(8), 7);
    Navigation.map.insertEdge(Navigation.locations.get(7), Navigation.locations.get(6), 1);
    Navigation.map.insertEdge(Navigation.locations.get(6), Navigation.locations.get(7), 1);
    Navigation.map.insertEdge(Navigation.locations.get(2), Navigation.locations.get(5), 4);
    Navigation.map.insertEdge(Navigation.locations.get(5), Navigation.locations.get(2), 4);
    Navigation.map.insertEdge(Navigation.locations.get(5), Navigation.locations.get(6), 2);
    Navigation.map.insertEdge(Navigation.locations.get(6), Navigation.locations.get(5), 2);
    Navigation.map.insertEdge(Navigation.locations.get(2), Navigation.locations.get(3), 7);
    Navigation.map.insertEdge(Navigation.locations.get(3), Navigation.locations.get(2), 7);
    Navigation.map.insertEdge(Navigation.locations.get(3), Navigation.locations.get(5), 14);
    Navigation.map.insertEdge(Navigation.locations.get(5), Navigation.locations.get(3), 14);
    Navigation.map.insertEdge(Navigation.locations.get(3), Navigation.locations.get(4), 9);
    Navigation.map.insertEdge(Navigation.locations.get(4), Navigation.locations.get(3), 9);
    Navigation.map.insertEdge(Navigation.locations.get(4), Navigation.locations.get(5), 10);
    Navigation.map.insertEdge(Navigation.locations.get(5), Navigation.locations.get(4), 10);
  }

  @Test
  /**
   * Checks whether IllegalArgumentException can be deteced
   */
  public void testFindLocation() {
    try {
      Navigation.findLocation("0");
    } catch (IllegalArgumentException e) {
      assertTrue(false);
    }

    try {
      Navigation.findLocation("9");
      assertTrue(false);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  /**
   * Checks the route from a starting point directly to a same destination
   */
  public void testImpossibleNavigation() {
    try {
      Navigation.navigation(Navigation.locations.get(0), Navigation.locations.get(0)).toString()
          .equals("[0, 7, 6, 5, 4]");
      Navigation.navigation(Navigation.locations.get(0), Navigation.locations.get(0)).toString()
          .equals("[0, 1, 2, 8]");
      assertTrue(false);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  /**
   * Checks the route from a starting point directly to a destination
   */
  public void testDirectNavigation() {
    assertTrue((Navigation.navigation(Navigation.locations.get(0), Navigation.locations.get(4))
        .toString().equals("[0, 7, 6, 5, 4]"))); // test best route from 0 to 4
    assertTrue((Navigation.navigation(Navigation.locations.get(0), Navigation.locations.get(8))
        .toString().equals("[0, 1, 2, 8]"))); // test best route from 0 to 8
    assertTrue((Navigation.navigation(Navigation.locations.get(0), Navigation.locations.get(5))
        .toString().equals("[0, 7, 6, 5]"))); // test best route from 0 to 5
  }

  @Test
  /**
   * Check whether a location can provide food
   */
  public void testProvideFood() {
    assertTrue(!Navigation.locations.get(0).isCanProvideFood()); // 0 can't provide food
    assertTrue(Navigation.locations.get(8).isCanProvideFood()); // 8 can provide food
  }

  @Test
  /**
   * Checks the route from a starting point to several destinations
   */
  public void testIndirectNavigation() {
    List<Location> list = new LinkedList<Location>();
    list.add(Navigation.locations.get(0));
    list.add(Navigation.locations.get(1));
    list.add(Navigation.locations.get(7));
    assertTrue(Navigation.planRoute(new Navigation.Path(Navigation.locations.get(0)), list,
        Navigation.locations.get(0).getName()).distance == 15); // choose the best
                                                                // destination between 1 and
                                                                // 7
    list.clear();
    list.add(Navigation.locations.get(0));
    list.add(Navigation.locations.get(2));
    list.add(Navigation.locations.get(8));
    assertTrue(Navigation.planRoute(new Navigation.Path(Navigation.locations.get(0)), list,
        Navigation.locations.get(0).getName()).distance == 14); // choose the best
                                                                // destination between 2 and
                                                                // 8
    list.clear();
    list.add(Navigation.locations.get(0));
    list.add(Navigation.locations.get(2));
    list.add(Navigation.locations.get(5));
    assertTrue(Navigation.planRoute(new Navigation.Path(Navigation.locations.get(0)), list,
        Navigation.locations.get(0).getName()).distance == 15); // choose the best
                                                                // destination between 2 and
                                                                // 8
    list.clear();
    list.add(Navigation.locations.get(0));
    list.add(Navigation.locations.get(3));
    list.add(Navigation.locations.get(4));
    list.add(Navigation.locations.get(5));
    assertTrue(Navigation.planRoute(new Navigation.Path(Navigation.locations.get(0)), list,
        Navigation.locations.get(0).getName()).distance == 30); // choose the best
                                                                // destination between 3, 4
                                                                // and 5
  }

}
