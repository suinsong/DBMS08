package com.bit.dbms8.dbcon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bit.dbms8.config.DbConn;

public class BuyerCon {
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
			System.out.println(DbConn.Msg.Msg_Driver_Success);

			conn = DriverManager.getConnection(DbConn.DB_URL, DbConn.DB_User, DbConn.DB_Passwd);
			System.out.println(DbConn.Msg.Msg_Conn_Success);

			
			
			String sql = " insert into buyer (bucode, buname, butel) " + " values(?,?,?) ; ";

			String reader = new String();

			while (true) {
				reader = buffer.readLine();
				if (reader == null)
					break;
				String[] names = reader.split(":");

				int intTEL = (int) (Math.random() * (9999 - 1000) + 1000);
				int intTEL_F = (int) (Math.random() * (9999 - 1000) + 1000);
				String strTEL = "010-" + Integer.toString(intTEL_F) + "-" + Integer.toString(intTEL_F);

				String strcode = Integer.toString(intTEL);

				pSt = conn.prepareStatement(sql);
				pSt.setString(1, strcode);
				pSt.setString(2, names[0]);
				pSt.setString(3, strTEL);
				pSt.executeUpdate();
			}

			buffer.close();
			fileReader.close();

			System.out.println(DbConn.Msg.Msg_DbInsert_Success);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
