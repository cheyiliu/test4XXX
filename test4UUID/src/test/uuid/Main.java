package test.uuid;

import java.util.UUID;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for (int i = 0; i < 5; i++) {
			// TODO Auto-generated method stub
			String str = String.valueOf(System.getProperties().toString()
					+ System.currentTimeMillis())
					+ UUID.randomUUID();
			// System.out.println(str);

			String uuid = MD5.toMD5(str) + "_"
					+ "MarketChannelHelper.getChannelID()";
			System.out.println(uuid);
		}
	}

}
