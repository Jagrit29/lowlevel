package lowleveldesign.systems.backupservice;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

enum Type {
    AZURE, AWS, GCP
}

// abstract to an interface to support strategy
enum Schedule {
    ONDEMAND, TENMIN, HOURLY, DAILY
}

enum State {
    INITIAL, STARTED, SUSPENDED, COMPLETED, CANCELED, FAILED
}

class Partition {
    String id;
    String name;
    String size;

    public Partition(String id, String name, String size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    // getters/setters
}

class Folder {
    String id;
    String name;
    Type type;

    public Folder(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}


class Backup {
    String id;
    String name;
    String partId;
    int partPointer;
    String folderId;
    Schedule schedule;
    private State state;

    public Backup(String id, String name, String partId, String folderId, Schedule schedule) {
        this.id = id;
        this.name = name;
        this.partId = partId;
        this.folderId = folderId;
        this.schedule = schedule;
    }

    // do the backup
    public void doBackup() {
        // To get the read the data from partId and put it folderId
        // compute
        //
        // based on the schedule
        // AWS - AWS
        // AZURE - AZURE;
        // If the state is initial
        int size = 100; //
        for(int i=partPointer;i<size;i++) {
            if(state.equals(State.SUSPENDED)) return;
            System.out.println("Backup Started");
        }
    }

    public void cancelBackup() {
        if(state.equals(State.STARTED)) {
           setState(State.CANCELED);
        }
    }


    public void suspendBackup(){
        if(state.equals(State.STARTED)) {
            setState(State.SUSPENDED);
        }
    }

    public void restartBackup() {
        if(state.equals(State.SUSPENDED)) {
            setState(State.STARTED);
            doBackup();
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

public class BackupService {

    // Queue of Backups
    // Consumer of Backup
    Map<String, Backup> backups;
    Map<String, Partition> parts;
    Map<String, Folder> folders;
    // Singleton;
    public BackupService() {
        backups = new ConcurrentHashMap<>();
        parts = new ConcurrentHashMap<>();
        folders = new ConcurrentHashMap<>();
    }

    public void addBackup(Backup backup) {
//        backQueue.add(backup);
        backups.put(backup.id, backup);
    }

    public void startBackup(String backupId) {
        Backup backup = backups.get(backupId);
        backup.doBackup();
    }

    public void restartBackup(String backupId) {
        Backup backup = backups.get(backupId);
        backup.restartBackup();
    }

    public void suspendBackup(String backupId) {
        Backup backup = backups.get(backupId);
        backup.suspendBackup();
    }
}

//public class BackupQueue {
//    Queue<Backup> backQueue;
//}
//
//public class BackupWorker {
//
//}

class Demo {
    public static void main(String args[]) {

    }
}



/*
Notes/Rough entities
1. Partition (Source) - (
    id,
    name,
    AdGroup,
    size,
}

-. Folder - (id, name, type(AZURE, FILE, S3)
- BackUp - To get data from Partition to Folder Period
    - id
    - name
    - Source Partition
    - Destination Folder
    - schedule - (ONETIME, 10-MINUTE, HOURLY, DAILY)

   doBackup()
        --
        --
        --

- BackUpService
    Backups[]
    Singletone

    createBackup()
    getBackUps()
-

AdGroup -> (id, name, User[])
 */