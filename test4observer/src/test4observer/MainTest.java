package test4observer;

public class MainTest {

	public static void main(String[] args) {
		normalCase();
		noisyCase();
	}

	private static void noisyCase() {
		System.out.println("noisyCase__________________________");
		ContextNoisy contextNormal = new ContextNoisy();
		IPerson person1 = new BasketballPlayer(contextNormal, "路人甲");
		IPerson person2 = new BasketballPlayer(contextNormal, "路人乙");

		contextNormal.register(person1);
		contextNormal.register(person2);

		person1.say("传球给我");
		person2.say("你自个玩吧");
		contextNormal.unRegister(person2);
	}

	private static void normalCase() {
		System.out.println("normalCase__________________________");
		ContextNormal contextNormal = new ContextNormal();
		IPerson person1 = new BasketballPlayer(contextNormal, "路人甲");
		IPerson person2 = new BasketballPlayer(contextNormal, "路人乙");

		contextNormal.register(person1);
		contextNormal.register(person2);

		person1.say("传球给我");
		person2.say("你自个玩吧");
		contextNormal.unRegister(person2);
	}
}
