// --== CS400 File Header Information ==--
// Name: Tong Jiao
// Email: tjiao4@wisc.edu
// Team: IC
// Role: Back End Developer
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

public class Location {
  private String name; // name of a certain location
  private boolean canProvideFood; // can this location provide food

  /**
   * A constructor to create a location with a name and specifies if it can provide food.
   * 
   * @param name           Name of the location
   * @param canProvideFood Can this location provide food
   */
  public Location(String name, boolean canProvideFood) {
    this.name = name;
    this.canProvideFood = canProvideFood;
  }

  /**
   * Returns the name of this location
   * 
   * @return name of this location
   */
  public String getName() {
    return name;
  }

  /**
   * Returns true if this location can provide food. False otherwise.
   * 
   * @return true if this location can provide food. False otherwise.
   */
  public boolean isCanProvideFood() {
    return canProvideFood;
  }

  @Override
  public String toString() {
    return name;
  }

}
