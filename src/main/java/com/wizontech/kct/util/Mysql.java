package com.wizontech.kct.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mysql {
	
	public static boolean pkCheck(Exception e){
      String[] array = e.getCause().toString().split(": ");
      if(array[1].startsWith("Duplicate") && array[1].endsWith("'PRIMARY'")){
         return true;
      }else{
         return false;
      }
   }

}
