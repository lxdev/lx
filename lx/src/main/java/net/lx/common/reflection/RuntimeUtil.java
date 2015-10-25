package net.lx.common.reflection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimeUtil {

	/**
	 * 执行命令
	 * 
	 * @param commandStr
	 * @return
	 */
	public static boolean executeCommand(String commandStr) {
		if(commandStr==null||commandStr.equals("")){
			return false;
		}
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(commandStr);
		} catch (IOException e) {
			return false;
		} finally {
			runtime.gc();
		}
		return true;
	}
	/**
	 * 执行命令
	 * 
	 * @param commandStr
	 * @return
	 */
	public static boolean executeCommand(String[] commandStrs) {
		if(commandStrs==null){
			return false;
		}
		Process process=null;
		BufferedReader input=null;
		String line = null;
		Runtime runtime = Runtime.getRuntime();
		try {
			process = runtime.exec(commandStrs);
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = input.readLine()) != null)
				System.out.println(line);
			input.close();
			
		} catch (IOException e) {
			return false;
		} finally {
			runtime.gc();
		}
		return true;
	}
}
