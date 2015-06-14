package test4observer;

public class MainTest {

	public static void main(String[] args) {
		basketball();
	}

	private static void basketball() {
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
