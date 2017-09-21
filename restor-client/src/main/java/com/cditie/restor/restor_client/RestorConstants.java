package com.cditie.restor.restor_client;

public class RestorConstants {
	
	public static final String NOTICE_PANEL = "NoticePanel";
	public static final int DEFAULT_WORK_TIME = 50; //默认工作时间
	public static final int DEFAULT_REST_TIME = 10; //默认休息时间

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

