import java.io.IOException;

public abstract class Data implements Comparable<Data> {

    private String name;

    abstract String getNextWord() throws IOException;

    public Data(String name) {
        this.name = name;
    }

    public int compareTo(Data o) {
        return name.compareTo(o.name);
    }

}
