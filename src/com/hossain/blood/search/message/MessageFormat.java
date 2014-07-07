package com.hossain.blood.search.message;

import com.hossain.blood.search.model.Message;

public class MessageFormat {
	
	private static final  String MSG_FIRST = "A patient at ";

	
	public String getMessage(Message message){
		
		String msg = MSG_FIRST + message.getPatLocation()+"," + message.getPatName()+","+" is in need of at least " +
				"1 bag of blood, group "+message.getBloodGroup()+"."+"Please if you can, donate blood it would be most appociated." +
				"Please contcat at "+ message.getContactNo()+".";
		return msg;
	}

}
