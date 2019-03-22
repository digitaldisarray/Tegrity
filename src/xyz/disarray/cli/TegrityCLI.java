package xyz.disarray.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TegrityCLI {
	public void run(String[] args) {
		// TODO: Make this project argument based

		Scanner in = new Scanner(System.in);

		String input;
		int result = 0;
		boolean choiceSelected = false;
		while (choiceSelected = false) {
			System.out.println("Options: \n1) Create a config file\n2) Check a config file");
			System.out.print(">");

			input = in.next();

			switch (Integer.parseInt(input)) {
			case 1:
				result = 1;
				choiceSelected = true;
				break;
			case 2:
				result = 2;
				choiceSelected = true;
				break;
			default:
				System.out.println("Invalid input.");
				break;
			}
		}

		in.close();

		if (result == 1) {
			create();
		}

		if (result == 2) {
			check();
		}

	}

	/*
	 * Asks user for directory to config File config = new File checks if it exists
	 * 
	 * creates array list for filePaths creates array List for hashes
	 * 
	 * I should use json files but instead I parse a txt by doing split(":")
	 * 
	 * 
	 */
	private void check() {
		Scanner in = new Scanner(System.in);

		System.out.print("Directory to config file: ");
		File config = new File(in.next());
		if (!config.exists()) {
			System.out.println("Sorry, that file doesn't exist");
			System.exit(1);
		}

		ArrayList<String> filePaths = new ArrayList<>();
		ArrayList<String> hashes = new ArrayList<>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(config));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		String line;
		try {
			while ((line = br.readLine()) != null) {
				filePaths.add(line.split(":")[0]);
				hashes.add(line.split(":")[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Paths loaded: " + filePaths.size());

		List<File> files = null;

		// Load files
		for (String path : filePaths) {
			try (Stream<Path> paths = Files.walk(Paths.get(path))) {
				List<File> filesInFolder = Files.walk(Paths.get(path)).filter(Files::isRegularFile).map(Path::toFile)
						.collect(Collectors.toList());
				for (File f : filesInFolder) {
					files.add(f);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (files == null) {
			System.out.println("No files detected in the given directory(s).");
		}

		// TODO: Actually find hashes and stuff

	}

	private void create() {
		Scanner in = new Scanner(System.in);

	}

	// TODO: Make this project arguments based
	private void help() {
		System.out.println("Args:" + "\n-create <directory> = Creates a config filfe based on a directory"
				+ "\n-check <path-to-config> = Check file integrity based on a config");
	}

	private static String getFileChecksum(MessageDigest digest, File file) throws IOException {
		// Get file input stream for reading the file content
		FileInputStream fis = new FileInputStream(file);

		// Create byte array to read data in chunks
		byte[] byteArray = new byte[1024];
		int bytesCount = 0;

		// Read file data and update in message digest
		while ((bytesCount = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}

		fis.close();

		byte[] bytes = digest.digest();

		// Convert bytes[] (decimal) to hexidecimal
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}
}
