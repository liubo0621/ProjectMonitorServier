package com.utils;

/**
 * @author Boris
 * @description 
 * 2016Äê9ÔÂ9ÈÕ
 */
public class Constance {
	public static final String STOP_TASK = "TASK:STOP taskId,threadId";
	public static final String MAX_THREAD = "THRead:MAX:NUM threadNum"; 
	public static final String RESTART_SERVER = "SERver:RESTART";
	public static final String RESTART_PROCESS = "PROcess:RESTART";
	public static final String START_PROCESS = "PROcess:START";
	
	public static class CommandStatus{
		public static final int TODO = 1;
		public static final int DOING = 2;
		public static final int DONE = 3;
	}


}
