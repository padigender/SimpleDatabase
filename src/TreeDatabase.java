/**
 * Created by pkyasaram on 10/19/15.
 */
import java.util.*;
public class TreeDatabase extends DataBase {

    TreeDatabase(){
        nameIndex = new TreeMap<String,String>();
        valueIndex = new TreeMap<String,Integer>();
    }

    public TreeMap<String, String> getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(TreeMap<String, String> nameIndex) {
        this.nameIndex = nameIndex;
    }

    public TreeMap<String, Integer> getValueIndex() {
        return valueIndex;
    }

    public void setValueIndex(TreeMap<String, Integer> valueIndex) {
        this.valueIndex = valueIndex;
    }

    // for name and value
    protected TreeMap<String,String> nameIndex;
    // for value and the count
    protected TreeMap<String,Integer> valueIndex;
}
