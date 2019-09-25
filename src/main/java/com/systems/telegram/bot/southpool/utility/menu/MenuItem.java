package com.systems.telegram.bot.southpool.utility.menu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItem {

	private String name;
    private String action;
    private String url;
    
	public MenuItem(String name, String action, String url) {
		super();
		this.name = name;
		this.action = action;
		this.url = url;
	}
    
}
