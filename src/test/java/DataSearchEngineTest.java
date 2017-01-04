import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.SortedSet;

public class DataSearchEngineTest {

    @Test
    public void simpleTest() {
        String fileName = "src/test/test1.txt";
        try {
            DataSource[] data = new DataSource[1];
            data[0] = new FileDataSource(fileName);
            DataSearchEngine google = new DataSearchEngine.Builder(data).getEngine();
            String[] request = {"it", "is", "what", "it", "is"};
            SortedSet<DataSource> res = google.searchWords(request);
            Assert.assertTrue(res.contains(data[0]));
            request = new String[]{"it", "is", "what", "it", "is", "asdf"};
            res = google.searchWords(request);
            Assert.assertTrue(res.size() == 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void advancedTest() {
        String fileName1 = "src/test/test1.txt";
        String fileName2 = "src/test/test2.txt";
        try {
            DataSource[] data = new DataSource[2];
            data[0] = new FileDataSource(fileName1);
            data[1] = new FileDataSource(fileName2);
            DataSearchEngine google = new DataSearchEngine.Builder(data).getEngine();
            String[] request = {"it", "is", "what"};
            SortedSet<DataSource> res = google.searchWords(request);
            Assert.assertTrue(res.contains(data[0]) && res.contains(data[1]));
            request = new String[]{"it", "is", "what", "banana"};
            res = google.searchWords(request);
            Assert.assertTrue(res.size() == 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void superhardTest() {
        String fileName1 = "src/test/test1.txt";
        String fileName2 = "src/test/test2.txt";
        String fileName3 = "src/test/test3.txt";
        try {
            DataSource[] data = new DataSource[3];
            data[0] = new FileDataSource(fileName1);
            data[1] = new FileDataSource(fileName2);
            data[2] = new FileDataSource(fileName3);
            DataSearchEngine google = new DataSearchEngine.Builder(data).getEngine();
            String[] request = {"it", "is", "what"};
            SortedSet<DataSource> res = google.searchWords(request);
            Assert.assertTrue(res.contains(data[0]) && res.contains(data[1]));
            request = new String[]{"it", "is"};
            res = google.searchWords(request);
            Assert.assertTrue(res.contains(data[0]) && res.contains(data[1]) && res.contains(data[2]));
            request = new String[]{"it", "is", "what", "banana"};
            res = google.searchWords(request);
            Assert.assertTrue(res.size() == 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
