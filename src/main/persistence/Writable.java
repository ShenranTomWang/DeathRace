package main.persistence;

import org.json.*;

//This interface represents an object that can be written into a .json file by JsonWriter
//CITATION: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {

    //EFFECTS: returns this as a JSON Object
    JSONObject toJson() throws JSONException;
}
