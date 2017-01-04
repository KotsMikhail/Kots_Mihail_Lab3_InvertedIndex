import java.io.IOException;
import java.io.InputStream;

public interface DataSource extends Comparable<DataSource> {

    String getName();

    InputStream getData();

}
