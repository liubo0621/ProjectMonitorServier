package com.utils;

/**
 * @author Boris
 * @description 
 * 2016Äê9ÔÂ9ÈÕ
 */
public class Constance {
	public static final String STOP_TASK       = "TASK:STOP taskId,threadId";
	public static final String MAX_THREAD      = "THRead:MAX:NUM threadNum"; 
	public static final String RESTART_SERVER  = "SERver:RESTART";
	public static final String RESTART_PROCESS = "PROcess:RESTART";
	public static final String START_PROCESS   = "PROcess:START";
	
	public static class CommandStatus{
		public static final int TODO      = 0x0000010;
		public static final int DOING     = 0x0000011;
		public static final int DONE      = 0x0000012;
		public static final int EXCEPTION = 0x0000013;
	}


}
