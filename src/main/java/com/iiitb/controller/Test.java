package com.iiitb.controller;

import org.hibernate.Session;

import com.iiitb.utils.DBUtils;

public class Test {

	public static void main(String args[]) {
		DBUtils dbUtils=new DBUtils();
		Session session=dbUtils.getSession();
	}
}
