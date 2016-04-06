import java.util.Map;

/**
 * Created by pkyasaram on 10/19/15.
 */

public abstract class DataBase {
    public void setKeyValue(String key, String value){
        String oldValue = getNameIndex().get(key);
        if(oldValue == null) {
            getNameIndex().put(key, value);
            setValue(value);
        } else if(!oldValue.equals(value)){
            // remove oldValue
            removeValue(oldValue);
            // add newValue
            getNameIndex().put(key, value);
            setValue(value);
        }
        // if oldValue and value are equal don't need to set it in the valueIndex
    }
    public void setValue(String value){
        Integer count;
        if(getValueIndex().get(value) == null){
            count = new Integer(1);
            getValueIndex().put(value, count);
        } else {
            count = getValueIndex().get(value);
            getValueIndex().put(value, count.intValue() + 1);
        }
    }


    public String getKey(String key){
        String value = getNameIndex().get(key);
        return value;
    }

    public void unsetKey(String key){
        String value = getNameIndex().get(key);
        if(value!= null){
            removeValue(value);
            getNameIndex().remove(key);
        }
    }
    public void removeValue(String value){
        if(value != null){
            Integer count = getValueIndex().get(value);
            if(count!= null) {
                count = count - 1;
                if (count <= 0) {
                    getValueIndex().remove(value);
                } else{
                    getValueIndex().put(value, count);
                }
            }
        }
    }

    public void numEqualTo(String value){
        Integer count = getValueIndex().get(value);
        if(count != null){
            System.out.println(count);
        } else{
            System.out.println("0");
        }
    }
    public abstract Map<String, String> getNameIndex();
    public abstract Map<String, Integer> getValueIndex();
}
