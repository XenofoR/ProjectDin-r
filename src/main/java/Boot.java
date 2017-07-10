public class Boot {

    public static void main(String[] args) {

	Suggestion s = new Suggestion();

	s.setFood("burger");
	s.setResturant("gnarlyBurger");
	s.addParticipant("Kim Hansson");

	System.out.println(s.toString());
    }
}
