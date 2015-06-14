package test4observer;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasketballContext basketContext = new BasketballContext();
		IPerson person1 = new BasketballPlayer();
		IPerson person2 = new BasketballPlayer();

		basketContext.personEnter(person1);
		basketContext.personEnter(person2);

		person1.say("传球给我");
	}

}
