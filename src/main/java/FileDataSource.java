import java.io.*;

public class FileDataSource implements DataSource {

    private String name;
    private FileInputStream source;

    FileDataSource(String fileName) throws FileNotFoundException {
        name = fileName;
        source = new FileInputStream(new File(fileName));
    }

    public String getName() {
        return name;
    }

    public InputStream getData() {
        return source;
    }

    public int compareTo(DataSource o) {
        return this.getName().compareTo(o.getName());
    }
}
