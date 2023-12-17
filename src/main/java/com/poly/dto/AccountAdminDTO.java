package com.poly.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.poly.entity.Authority;
import com.poly.entity.BlogPost;
import com.poly.entity.Order;
import com.poly.enums.GenderEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountAdminDTO implements Serializable{
	private String Username;
	private String Name;
	private String Surname;
	private String Email;
	private String Password;
	private String CheckPass;
	private List<String> Rolename;
	private GenderEnum Gender = GenderEnum.UNKNOW;
	
	public AccountAdminDTO(String username, String name, String surname, String email, String password, List<String> rolename) {
		super();
		Username = username;
		Name = name;
		Surname = surname;
		Email = email;
		Password = password;
		Rolename = rolename;
	}
	
}
