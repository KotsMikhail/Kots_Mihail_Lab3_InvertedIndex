import java.io.*;

public class FileData extends Data {

    private BufferedReader source;

    FileData(String fileName) throws FileNotFoundException {
        super(fileName);
        source = new BufferedReader(new FileReader(fileName));
    }

    @Override
    public String getNextWord() throws IOException {
        String word = "";
        int c = source.read();
        while ((c != -1) && ((char) c == ' '))
            c = source.read();
        while ((c != -1) && ((char) c != ' ')) {
            word += (char) c;
            c = source.read();
        }
        return word;
    }

}
