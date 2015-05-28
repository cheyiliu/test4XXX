package com.example.tutorial;

import com.example.tutorial.AddressBookProtos.Person;
import com.example.tutorial.AddressBookProtos.Person.PhoneNumber;
import com.example.tutorial.AddressBookProtos.Person.PhoneType;
import com.google.protobuf.InvalidProtocolBufferException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PhoneNumber pb = PhoneNumber.newBuilder().setNumber("123---adf")
				.setType(PhoneType.HOME).build();
		
		Person john = Person
				.newBuilder()
				.setId(1234)
				.setName("John Doe")
				.setEmail("jdoe@example.com")
				.addPhone(
						Person.PhoneNumber.newBuilder().setNumber("555-4321")
								.setType(Person.PhoneType.HOME)).build();
		
		john = john.toBuilder().addPhone(pb).addPhone(pb).addPhone(pb).addPhone(pb).build();

		System.out.println(john.toString());

		byte[] johnbyte = john.toByteArray();
		try {
			Person john2 = Person.parseFrom(johnbyte);
			System.out.println(john2.toString());
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
