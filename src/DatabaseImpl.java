/**
 * Created by pkyasaram on 10/19/15.
 */
import java.util.*;
import java.io.*;
public class DatabaseImpl {

    public static void main(String[] args) throws IOException {

        DataBase database = new HashDatabase();

        //DataBase database = new TreeDatabase();
        CommandExecutor commandExecutor = new CommandExecutor(database);
        if(args == null || args.length == 0){
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if(command == null){
                return;
            }
            while(!command.equals("END")){
                commandExecutor.runCommand(command);
                command = scanner.nextLine();
            }
        } else{
            File file = new File(args[0]);
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String command;
            while ((command = buffer.readLine()) != null) {
                if(command.equals("END")){
                    return;
                }
                command = command.trim();
                // Process line of input Here
                commandExecutor.runCommand(command);
            }

        }
    }
}
