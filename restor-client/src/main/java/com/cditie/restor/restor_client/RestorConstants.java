package com.cditie.restor.restor_client;

public class RestorConstants {
	
	public static final String NOTICE_PANEL = "NoticePanel";
	
	public static enum RestorStyleEnum{
		
		TomatoEasy("番茄工作法"),
		
		TomatoHour("番茄小时法");
		
		private String name;
		
		

		private RestorStyleEnum(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	


}

