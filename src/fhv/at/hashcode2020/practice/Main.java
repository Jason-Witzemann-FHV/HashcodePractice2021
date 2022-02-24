package fhv.at.hashcode2020.practice;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.*;

public class Main {
    private static List<Book> books = new ArrayList<>();
    private static List<Library> libraries = new ArrayList<>();
    private static int daysForScanning = 0;
    private static List<Book> alreadyScannedBooks= new ArrayList<>();
    private static List<Integer> bookScores = new ArrayList<>();

    public static void main(String[] args) {
        int finalScore = 0;
        List<String> paths = List.of("D:\\Own_Coding_Projects\\HashCode Practice\\src\\fhv\\at\\hashcode2020\\practice\\a_example.txt",
                        "D:\\Own_Coding_Projects\\HashCode Practice\\src\\fhv\\at\\hashcode2020\\practice\\b_read_on.txt",
                        "D:\\Own_Coding_Projects\\HashCode Practice\\src\\fhv\\at\\hashcode2020\\practice\\c_incunabula.txt",
                        "D:\\Own_Coding_Projects\\HashCode Practice\\src\\fhv\\at\\hashcode2020\\practice\\d_tough_choices.txt",
                        "D:\\Own_Coding_Projects\\HashCode Practice\\src\\fhv\\at\\hashcode2020\\practice\\e_so_many_books.txt",
                        "D:\\Own_Coding_Projects\\HashCode Practice\\src\\fhv\\at\\hashcode2020\\practice\\f_libraries_of_the_world.txt");
        for (String filepath: paths) {
            try {
                System.out.println(filepath);
                readFile(filepath);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            } catch (Exception e) {
                e.printStackTrace();
            }

            finalScore += findScore();
            reset();
        }
        System.out.println(finalScore);
    }

    public static void reset() {
        books = new ArrayList<>();
        libraries = new ArrayList<>();
        daysForScanning = 0;
        alreadyScannedBooks= new ArrayList<>();
        bookScores = new ArrayList<>();
    }

    public static int findScore() {
        int remainingDays = daysForScanning;
        int maxScore = 0;
        while (remainingDays > 0 &&
                libraries.size() > 0 &&
                alreadyScannedBooks.size() < books.size()
        ) {
            List<LibraryScore> libraryScores = new ArrayList<>();
            for (Library lib : libraries) {
                LibraryScore score = lib.maxScore(remainingDays);
                libraryScores.add(score);
                //System.out.println(lib.maxScore(remainingDays));
            }
            libraryScores.sort(Comparator.comparing(LibraryScore::getScore).reversed());
            System.out.println(libraryScores.get(0));
            alreadyScannedBooks.addAll(libraryScores.get(0).getBooks());
            remainingDays -= libraryScores.get(0).getUsedDays();
            maxScore += libraryScores.get(0).getScore();
            libraries.remove(libraryScores.get(0).getLibrary());
        }
        System.out.println(maxScore);
        return maxScore;
    }

    public static void readFile(String inputfile) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File(inputfile)));

        // Inits
        String line = br.readLine();
        String[] initValues = line.split(" ");
        daysForScanning = Integer.parseInt(initValues[2]);
        System.out.println("There are " + initValues[0] + " books, " + initValues[1] + " libraries and " + initValues[2] + " days for scanning");

        // Bookscores
        line = br.readLine();

        int id = 0;
        for (String value : line.split(" ")) {
            int score = Integer.parseInt(value);
            Book book = new Book(id, score);
            bookScores.add(score);
            books.add(book);
            id++;
        }
        System.out.println(bookScores);

        // Libraries
        while((line = br.readLine()) != null){
            if(!line.isEmpty()){
                String[] libraryData = line.split(" ");
                Library lib = new Library(libraryData[0], libraryData[1], libraryData[2]);

                line = br.readLine();
                for (String bookData : line.split(" ")) {
                    lib.addBook(books.get(Integer.parseInt(bookData)));
                }
                libraries.add(lib);
            }
            else{
                System.out.println("FUCK");
            }

        }
    }
}
