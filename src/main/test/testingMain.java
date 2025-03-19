package main.test;

import com.William.Logger.LoggerInfo;
import com.William.Logger.*;


public class testingMain {
	public static class testings {
		public int age;
		public String name;
		public testings() {};
		@Override
		public String toString() {
			return "Age:"+ this.age +" Name: " + this.name;
		}
	}
	public static void main(String args[]) {
		 LoggerInfo info = new LoggerInfo(new LoggerConsole());
		 testings test = new testings();

		 info.logState(test.toString());
		 
		 test.age=19;
		 test.name="William";
		 
		 info.logState(test.toString());
		 info.logTest(x->x.age>18, test,"Make sure age is above 18");
		 
	}
}
