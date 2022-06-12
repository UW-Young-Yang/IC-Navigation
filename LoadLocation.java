public class LoadLocation {

	public static void createMap() {

		Navigation.locations.add(new Location("Grainger Hall", true));// 0
		Navigation.locations.add(new Location("Sewell Social Science Building", false));// 1
		Navigation.locations.add(new Location("Van Vleck Hall", false));// 2
		Navigation.locations.add(new Location("Union South", true));// 3
		Navigation.locations.add(new Location("University Book Store", false));// 4
		Navigation.locations.add(new Location("Memorial union", true));// 5
		Navigation.locations.add(new Location("Ingraham Hall", true));// 6
		Navigation.locations.add(new Location("Grand Central", true));// 7
		Navigation.locations.add(new Location("Computer Science and Statistic", false));// 8
		Navigation.locations.add(new Location("Agriculture Hall", false));// 9

		Navigation.locations.add(new Location("Bascom Hall", false));// 10
		Navigation.locations.add(new Location("Van Hise Hall", false));// 11
		Navigation.locations.add(new Location("Nancy Nicholas Hall", false));// 12
		Navigation.locations.add(new Location("Waters Residence Hall", true));// 13
		Navigation.locations.add(new Location("Hamel Music Center", false));// 14
		Navigation.locations.add(new Location("Vilas Hall", false));// 15
		Navigation.locations.add(new Location("Gordon Dining and Event Center", true));// 16
		Navigation.locations.add(new Location("Memorial Library", false));// 17

		for (int i = 0; i < Navigation.locations.size(); i++) {
			Navigation.map.insertVertex(Navigation.locations.get(i));
		}

		Navigation.map.insertEdge(Navigation.locations.get(0), Navigation.locations.get(7), 322);
		Navigation.map.insertEdge(Navigation.locations.get(1), Navigation.locations.get(6), 29);
		Navigation.map.insertEdge(Navigation.locations.get(2), Navigation.locations.get(0), 300);
		Navigation.map.insertEdge(Navigation.locations.get(3), Navigation.locations.get(9), 966);
		Navigation.map.insertEdge(Navigation.locations.get(4), Navigation.locations.get(2), 805);

		Navigation.map.insertEdge(Navigation.locations.get(14), Navigation.locations.get(16), 220);
		Navigation.map.insertEdge(Navigation.locations.get(14), Navigation.locations.get(17), 233);
		Navigation.map.insertEdge(Navigation.locations.get(5), Navigation.locations.get(17), 179);
		Navigation.map.insertEdge(Navigation.locations.get(5), Navigation.locations.get(10), 361);
		Navigation.map.insertEdge(Navigation.locations.get(1), Navigation.locations.get(10), 160);
		Navigation.map.insertEdge(Navigation.locations.get(10), Navigation.locations.get(17), 508);
		Navigation.map.insertEdge(Navigation.locations.get(14), Navigation.locations.get(15), 171);
		Navigation.map.insertEdge(Navigation.locations.get(4), Navigation.locations.get(17), 87);
		Navigation.map.insertEdge(Navigation.locations.get(4), Navigation.locations.get(15), 271);
		Navigation.map.insertEdge(Navigation.locations.get(0), Navigation.locations.get(15), 138);
		Navigation.map.insertEdge(Navigation.locations.get(7), Navigation.locations.get(8), 268);
		Navigation.map.insertEdge(Navigation.locations.get(3), Navigation.locations.get(8), 128);
		Navigation.map.insertEdge(Navigation.locations.get(7), Navigation.locations.get(9), 642);
		Navigation.map.insertEdge(Navigation.locations.get(9), Navigation.locations.get(12), 129);
		Navigation.map.insertEdge(Navigation.locations.get(12), Navigation.locations.get(11), 193);
		Navigation.map.insertEdge(Navigation.locations.get(11), Navigation.locations.get(13), 150);
		Navigation.map.insertEdge(Navigation.locations.get(1), Navigation.locations.get(11), 155);
		Navigation.map.insertEdge(Navigation.locations.get(2), Navigation.locations.get(6), 108);
		Navigation.map.insertEdge(Navigation.locations.get(10), Navigation.locations.get(6), 97);
		Navigation.map.insertEdge(Navigation.locations.get(2), Navigation.locations.get(10), 79);

		Navigation.map.insertEdge(Navigation.locations.get(7), Navigation.locations.get(0), 322);
		Navigation.map.insertEdge(Navigation.locations.get(6), Navigation.locations.get(1), 29);
		Navigation.map.insertEdge(Navigation.locations.get(0), Navigation.locations.get(2), 300);
		Navigation.map.insertEdge(Navigation.locations.get(9), Navigation.locations.get(3), 966);
		Navigation.map.insertEdge(Navigation.locations.get(2), Navigation.locations.get(4), 805);

		Navigation.map.insertEdge(Navigation.locations.get(16), Navigation.locations.get(14), 220);
		Navigation.map.insertEdge(Navigation.locations.get(17), Navigation.locations.get(14), 233);
		Navigation.map.insertEdge(Navigation.locations.get(17), Navigation.locations.get(5), 179);
		Navigation.map.insertEdge(Navigation.locations.get(10), Navigation.locations.get(5), 361);
		Navigation.map.insertEdge(Navigation.locations.get(10), Navigation.locations.get(1), 160);
		Navigation.map.insertEdge(Navigation.locations.get(17), Navigation.locations.get(10), 508);
		Navigation.map.insertEdge(Navigation.locations.get(15), Navigation.locations.get(14), 171);
		Navigation.map.insertEdge(Navigation.locations.get(17), Navigation.locations.get(4), 87);
		Navigation.map.insertEdge(Navigation.locations.get(15), Navigation.locations.get(4), 271);
		Navigation.map.insertEdge(Navigation.locations.get(15), Navigation.locations.get(0), 138);
		Navigation.map.insertEdge(Navigation.locations.get(8), Navigation.locations.get(7), 268);
		Navigation.map.insertEdge(Navigation.locations.get(8), Navigation.locations.get(3), 128);
		Navigation.map.insertEdge(Navigation.locations.get(9), Navigation.locations.get(7), 642);
		Navigation.map.insertEdge(Navigation.locations.get(12), Navigation.locations.get(9), 129);
		Navigation.map.insertEdge(Navigation.locations.get(11), Navigation.locations.get(12), 193);
		Navigation.map.insertEdge(Navigation.locations.get(13), Navigation.locations.get(11), 150);
		Navigation.map.insertEdge(Navigation.locations.get(11), Navigation.locations.get(1), 155);
		Navigation.map.insertEdge(Navigation.locations.get(6), Navigation.locations.get(2), 108);
		Navigation.map.insertEdge(Navigation.locations.get(6), Navigation.locations.get(10), 97);
		Navigation.map.insertEdge(Navigation.locations.get(10), Navigation.locations.get(2), 79);
	}
}
