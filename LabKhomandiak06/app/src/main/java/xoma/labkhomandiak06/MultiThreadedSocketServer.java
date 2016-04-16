package xoma.labkhomandiak06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MultiThreadedSocketServer {

	ServerSocket myServerSocket;
	boolean ServerOn = true;

	public MultiThreadedSocketServer() {
		try {
			myServerSocket = new ServerSocket(8080);
		} catch (IOException ioe) {
			System.out
					.println("Could not create server socket on port 8080. Quitting.");
			System.exit(-1);
		}

		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		System.out.println("It is now : " + formatter.format(now.getTime()));

		// Successfully created Server Socket. Now wait for connections.
		while (ServerOn) {
			try {
				// Accept incoming connections.
				Socket clientSocket = myServerSocket.accept();

				// Start a Service thread
				ClientServiceThread cliThread = new ClientServiceThread(
						clientSocket);
				cliThread.start();

			} catch (IOException ioe) {
				System.out
						.println("Exception encountered on accept. Ignoring. Stack Trace :");
				ioe.printStackTrace();
			}

		}
		
		try {
			myServerSocket.close();
			System.out.println("Server Stopped");
		} catch (Exception ioe) {
			System.out.println("Problem stopping server socket");
			System.exit(-1);
		}

	}

	public static void main(String[] args) {
		new MultiThreadedSocketServer();
	}

	class ClientServiceThread extends Thread {
		Socket myClientSocket;
		boolean m_bRunThread = true;

		public ClientServiceThread() {
			super();
		}

		ClientServiceThread(Socket s) {
			myClientSocket = s;

		}

		public void run() {

			BufferedReader in = null;
			PrintWriter out = null;

			// Print out details of this connection
			System.out.println("Thread started with name:"+Thread.currentThread().getName());
			System.out.println("Accepted Client Address - "
					+ myClientSocket.getInetAddress().getHostName());

			try {
				in = new BufferedReader(new InputStreamReader(
						myClientSocket.getInputStream()));
				out = new PrintWriter(new OutputStreamWriter(
						myClientSocket.getOutputStream()));

				System.out.println(in.readLine());

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// Clean up
				try {
					in.close();
					out.close();
					myClientSocket.close();
					System.out.println("...Stopped");
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}

	}
}