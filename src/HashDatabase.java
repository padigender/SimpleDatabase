/**
 * Created by pkyasaram on 10/19/15.
 */

import java.util.HashMap;

public class HashDatabase extends DataBase {

    HashDatabase(){
        nameIndex = new HashMap<String,String>();
        valueIndex = new HashMap<String,Integer>();
    }

    public HashMap<String, String> getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(HashMap<String, String> nameIndex) {
        this.nameIndex = nameIndex;
    }

    public HashMap<String, Integer> getValueIndex() {
        return valueIndex;
    }

    public void setValueIndex(HashMap<String, Integer> valueIndex) {
        this.valueIndex = valueIndex;
    }

    // for name and value
    protected HashMap<String,String> nameIndex;
    // for value and the count
    protected HashMap<String,Integer> valueIndex;
}
