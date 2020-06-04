package lambdasinaction.chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class C3ExecuteAround {

	public static final String CHAP_3_DATA_TXT = "lambdasinaction/chap3/data.txt";

	public static void main(String... args) throws IOException {

		// method we want to refactor to make more flexible
		String result = processFileLimited(CHAP_3_DATA_TXT);
		System.out.println(result);

		System.out.println("---");

		// added by ll6
		String oneLine = processFile(CHAP_3_DATA_TXT, bufferedReader -> bufferedReader.readLine());
		System.out.println(oneLine);

		System.out.println("---2---");
		String twoLines = processFile(CHAP_3_DATA_TXT, bufferedReader -> bufferedReader.readLine() + "\n"
				+ bufferedReader.readLine()
		);
		System.out.println(twoLines);

	}

	public static String processFileLimited(String fileName) throws IOException {
		try (BufferedReader br =
					 new BufferedReader(new FileReader(fileName))) {
			return br.readLine();
		}
	}

	// added by lala
	public interface BufferedReaderProcessor {
		public String readFile(BufferedReader br) throws IOException;
	}

	//
	public static String processFile(String fileName, BufferedReaderProcessor brp) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			return brp.readFile(br);
		}
	}
}
