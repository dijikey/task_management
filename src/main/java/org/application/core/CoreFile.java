package org.application.core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

import static org.application.core.GlobalVar.DATA_BASE;

public class CoreFile {
    private void writeJSON(JSONArray taskList, File jsFile){
        try (FileWriter file = new FileWriter(jsFile.getPath())) {
            file.write(taskList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveTaskSystem(File file){
        JSONArray taskList = new JSONArray();
        for (Task task : DATA_BASE.getTasks()){
            JSONObject taskDetails = new JSONObject();
            taskDetails.put("id", task.getId());
            taskDetails.put("title", task.getTitle());
            taskDetails.put("description", task.getDescription());
            taskDetails.put("isComplete", task.isComplete());
            taskDetails.put("createdAt", task.getCreatedAt());

            JSONObject taskObject = new JSONObject();
            taskObject.put("task", taskDetails);
            taskList.add(taskObject);
        }

        writeJSON(taskList, file);
    }

    public void loadTask(JSONObject task){
        JSONObject object = (JSONObject) task.get("task");
        DATA_BASE.createTask(
                Integer.parseInt(object.get("id").toString()),
                (String) object.get("title"),
                (String) object.get("description"),
                (boolean) object.get("isComplete"),
                (String) object.get("createdAt")

        );
    }

    public void loadTaskSystem(File file) {
        JSONParser jsonParser = new JSONParser();
        DATA_BASE.clearTaskList();

        try (FileReader reader = new FileReader(file.getPath())) {
            Object obj = jsonParser.parse(reader);
            JSONArray array = (JSONArray) obj;

            array.forEach( var -> loadTask((JSONObject) var));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
