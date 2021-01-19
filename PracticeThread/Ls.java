package PracticeThread;
import java.io.*;

public class Ls {
	public static void main(String[] args) {
		try {
			Process proc = Runtime.getRuntime().exec("cmd /c more");
			InputStream in = proc.getInputStream();
			OutputStream out = proc.getOutputStream();
			byte buffer[] = new byte[1024];
			int n = -1;
			InputStream fin = null;
			if (args.length > 0)
				fin = new FileInputStream(args[0]);
			else
				fin = System.in;
			while((n = fin.read(buffer)) != -1)
				out.write(buffer, 0, n);
			fin.close();
			out.close();
			while((n = in.read(buffer)) != -1)
				System.out.print(new String(buffer, 0, n));
			in.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
