package com.bit.dbms8.dbcon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bit.dbms8.config.DbConn;

public class NamesCon {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pSt = null;

		String namesFile = "src/com/bit/dbms/doc/names.txt";
		FileReader fileReader = null;
		BufferedReader buffer = null;

		try {

			fileReader = new FileReader(namesFile);
			buffer = new BufferedReader(fileReader);
			
			Class.forName(DbConn.DB_Driver);
			System.out.println(DbConn.Msg.Msg_Conn_Success);

			conn = DriverManager.getConnection(DbConn.DB_URL, DbConn.DB_User, DbConn.DB_Passwd);

			String reader = new String();

			String sql = " insert into names values(0,?,?,?); ";
			pSt = conn.prepareStatement(sql);

			int cnt = 0;
			while (true) {
				reader = buffer.readLine();
				if (reader == null)
					break;
				System.out.println(++cnt);
				// 0:영문이름, 1:한글발음, 2:의미
				String[] names = reader.split(":");
				pSt.setString(1, names[0]);
				pSt.setString(2, names[1]);
				pSt.setString(3, names[2]);
				pSt.executeUpdate();
			}
			buffer.close();
			fileReader.close();
			
			
			System.out.println(DbConn.Msg.Msg_DbInsert_Success);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
