package test4observer;

public class BasketballPlayer implements IPerson {
	private IContext mContext;
	private String mName;

	public BasketballPlayer(IContext context, String name) {
		mContext = context;
		mName = name;
		System.out.println("new BasketballPlayer, " + mName);
	}

	@Override
	public void say(String content) {
		System.out.println(mName + " say " + content);
		mContext.deliverInfo(this, content);
	}

	@Override
	public void hear(IPerson from, String content) {
		if (from == this) {
			return;// ignore
		}
		System.out.println(mName + " hear " + from.getName() + " " + content);
		if (content.contains("传")) {
			say("我很独的");
			playBasketball();
		}
		if (content.contains("独")) {
			say("我走了， 你自个玩吧");
			mContext.unRegister(this);
		}
	}

	public void playBasketball() {
		System.out.println(mName + " playBasketball ");
	}

	@Override
	public String getName() {
		return mName;
	}

}
