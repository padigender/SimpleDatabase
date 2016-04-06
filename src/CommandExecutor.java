/**
 * Created by pkyasaram on 10/19/15.
 */
import java.util.*;
public class CommandExecutor {
    public Stack<String> getCommandStack() {
        return commandStack;
    }

    public void setCommandStack(Stack<String> commandStack) {
        this.commandStack = commandStack;
    }

    protected Stack<String> commandStack;

    public DataBase getDataBase() {
        return dataBase;
    }

    protected DataBase dataBase;

    CommandExecutor(DataBase dataBase){
        commandStack = new Stack<String>();
        this.dataBase = dataBase;
    }
            /*


    SET name value – Set the variable name to the value value. Neither variable names nor values will contain spaces.

    GET name – Print out the value of the variable name, or NULL if that variable is not set.

    UNSET name – Unset the variable name, making it just like that variable was never set.

    NUMEQUALTO value – Print out the number of variables that are currently set to value. If no variables equal that value, print 0.

    END – Exit the program. Your program will always receive this as its last command.


    BEGIN – Open a new transaction block. Transaction blocks can be nested; a BEGIN can be issued inside of an existing block.

    ROLLBACK – Undo all of the commands issued in the most recent transaction block, and close the block. Print nothing if successful, or print NO TRANSACTION if no transaction is in progress.

    COMMIT – Close all open transaction blocks, permanently applying the changes made in them. Print nothing if successful, or print NO TRANSACTION if no transaction is in progress.


         */

    protected void runCommand(String command){

        String[] commandDesc = command.split(" ");
        switch (commandDesc[0]) {
            case "SET":
                runSetCommand(commandDesc[1],commandDesc[2]);
                break;
            case "GET":
                runGetCommand(commandDesc[1]);
                break;
            case "UNSET":
                runUnSetCommand(commandDesc[1]);
                break;
            case "NUMEQUALTO":
                runNumEqualToCommand(commandDesc[1]);
                break;
            case "BEGIN":
                runBeginCommand();
                break;
            case "ROLLBACK":
                runRollbackCommand();
                break;
            case "COMMIT":
                runCommitCommand();
                break;
            default:
                System.out.println("INVALID COMMAND");

        }
    }
    protected void runSetCommand(String name, String value){
        // add key to the DB and add command to the command Stack
        String oldValue = this.getDataBase().getKey(name);
        this.getDataBase().setKeyValue(name,value);
        // command<space>key<space>newvalue<space>oldvalue
        // oldValue can be null
        if(!this.getCommandStack().isEmpty()) {
            if(oldValue == null){
                oldValue = " ";
            }
            this.getCommandStack().add("SET " + name + " " + value + " " + oldValue);
        }

    }
    protected void runGetCommand(String name){
        String value = this.getDataBase().getKey(name);
        if(value != null){
            System.out.println(value);
        } else{
            System.out.println("NULL");
        }
    }
    protected void runUnSetCommand(String name){
        String oldValue = this.getDataBase().getKey(name);
        // oldValue can be null
        this.getDataBase().unsetKey(name);
        // command<space>key<space>oldValue
        if(!this.getCommandStack().isEmpty()) {
            if(oldValue == null){
                oldValue = " ";
            }
            this.getCommandStack().add("UNSET " + name + " " + oldValue);
        }
    }

    protected void rollbackSet(String key,String newValue,String oldValue){
        // remove new Value and set Old Value
        if(oldValue != null){
            this.getDataBase().unsetKey(key);
            this.getDataBase().setKeyValue(key,oldValue);
        } else{
            this.getDataBase().unsetKey(key);
        }

    }
    protected void rollbackUnSet(String key,String oldValue){
        if(oldValue != null){
            this.getDataBase().setKeyValue(key, oldValue);
        }
    }
    protected void runNumEqualToCommand(String value){
        this.getDataBase().numEqualTo(value);
    }
    protected void runBeginCommand(){

        this.getCommandStack().add("BEGIN");
    }
    protected void runRollbackCommand(){

        while(!(this.getCommandStack().isEmpty() || this.getCommandStack().peek().equals("BEGIN"))){
            String command = this.getCommandStack().pop();
            String[] commandDesc = command.split(" ");
            if(commandDesc[0].equals("SET")){
                if(commandDesc.length == 3){
                    rollbackSet(commandDesc[1],commandDesc[2],null);
                } else{
                    rollbackSet(commandDesc[1],commandDesc[2],commandDesc[3]);
                }

            } else if(commandDesc[0].equals("UNSET")){
                if(commandDesc.length == 2){
                    rollbackUnSet(commandDesc[1], null);
                } else{
                    rollbackUnSet(commandDesc[1],commandDesc[2]);
                }
            }
        }
        if(this.getCommandStack().isEmpty()){
            System.out.println("NO TRANSACTION");
        } else if(this.getCommandStack().peek().equals("BEGIN")){
            this.getCommandStack().pop();
        }
    }
    protected void runCommitCommand(){
        // remove commands from command stack until we hit BEGIN or command stack is empty

        if(this.getCommandStack().isEmpty()){
            System.out.println("NO TRANSACTION");
        }
        while(!(this.getCommandStack().isEmpty())){
            this.getCommandStack().pop();
        }
    }
}
