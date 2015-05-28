package com.example.tutorial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.tutorial.Person.PhoneNumber;
import com.example.tutorial.Person.PhoneType;
import com.squareup.wire.Wire;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 构建对象
		PhoneNumber pb = new PhoneNumber.Builder().number("123---adf")
				.type(PhoneType.HOME).build();
		PhoneNumber pb2 = new PhoneNumber.Builder().number("123---adf")
				.type(PhoneType.MOBILE).build();

		List<PhoneNumber> phones = new ArrayList<PhoneNumber>();
		phones.add(pb);
		phones.add(pb2);

		Person john = new Person.Builder().id(1234).name("John Doe")
				.email("jdoe@example.com").phone(phones).build();

		System.out.println(john.toString());

		// 序列化
		byte[] johnbyte = john.toByteArray();

		// 反序列化
		Wire wire = new Wire();
		try {
			Person john2 = wire.parseFrom(johnbyte, Person.class);
			System.out.println(john2.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
