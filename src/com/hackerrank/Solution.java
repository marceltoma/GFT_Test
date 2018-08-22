package com.hackerrank;

import java.io.*;
import java.lang.*;

public class Solution {
	public static void main(String[] args) {
		File fileName = new File("banks.txt");
		if (fileName.exists()) {
			// System.out.println( "this file exists " );
			try {
				byte[] buffer = new byte[100];
				FileInputStream inputStream = new FileInputStream(fileName);
				int readLines = -1;
				while ((readLines = inputStream.read(buffer)) != -1) {
					System.out.println(new String(buffer));
				}
				inputStream.close();
			} catch (IOException e) {
				// catch exception
			}
		}
	}
}