package lowleveldesign.systems.googledoc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/*
** Requirements
- User can create/edit/collaborate on document at real-time
- Handle concurrent updates to the same document without conflicts
- Version history of the document
- Users can view and track changes

** Core Classes, Interfaces and Enums
* User - userId, name, editingDocuments[]
* Document - docId, name, editingUsers[], Edit[]
* Edit/Change - id, userId, change, timestamp;
* Brute force way would be to lock the whole document while ediitng but we can create sections there
* Section - sectionId, content, Lock;
 */

class User {
    String userId;
    String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}

class Edit {
    User user;
    String change;
    String timestamp;

    public Edit(User user, String change, String timestamp) {
        this.user = user;
        this.change = change;
        this.timestamp = timestamp;
    }
}

class Section {
    String sectionId;
    StringBuffer content;
    ReentrantLock lock;

    public Section(String sectionId) {
        this.sectionId = sectionId;
        this.content = new StringBuffer();
        this.lock = new ReentrantLock();
    }

    public void editSection(String newContent) {
        lock.lock();
        try {
            content.append(newContent);
        } finally {
            lock.unlock();
        }
    }

    public String getContent() {
        return content.toString();
    }

    public String getId() {
        return sectionId;
    }
}

class Version {
    String versionId;
    String content;
    String timestamp;

    public Version(String versionId, String content, String timestamp) {
        this.versionId = versionId;
        this.content = content;
        this.timestamp = timestamp;
    }
}

class Document {
    String docId;
    String title;
    StringBuffer content;
    List<Edit> editHistory;
    Map<String, Section> sections;
    Map<String, Version> versions;
    AtomicInteger versionCounter;

    public Document(String docId, String title) {
        this.docId = docId;
        this.title = title;
        this.content = new StringBuffer();
        this.editHistory = new ArrayList<>();
        this.sections = new ConcurrentHashMap<>();

        for(int i=0;i<10;i++) {
            Section section = new Section(Integer.toString(i));
            sections.put(section.getId(), section);
        }
    }

    public void editDocument(User user, String sectionId, String newChange) {
        Section section = sections.get(sectionId);

        if(section != null) {
            section.editSection(newChange);
            Edit edit = new Edit(user, newChange, "time");
            editHistory.add(edit);
        } else {
            throw new IllegalArgumentException("Invalid Section ID");
        }
    }

    public void saveDocument() {
        versionCounter.getAndIncrement();
        Version version = new Version(Integer.toString(versionCounter.get()), content.toString(), "current_time");
        versions.put(Integer.toString(versionCounter.get()), version);
    }

    public String getDocumentContent() {
        StringBuffer fullContent = new StringBuffer();
        for(Section section: sections.values()) {
            fullContent.append(section.getContent());
        }

        return fullContent.toString();
    }

    public List<Edit> getEditHistory() {
        return editHistory;
    }

    // rollback to this version
    // get particular version;
}


public class DocumentService {
    public static void main(String[] args) {
        User alice = new User("1", "Alice");
        User bob = new User("2", "Bob");

        Document doc = new Document("doc1", "Fighting Doc");

        Thread t1 = new Thread(() -> doc.editDocument(alice, "0", "This is alice "));
        Thread t2 = new Thread(() -> doc.editDocument(bob, "0", "This is bob"));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(doc.getDocumentContent());
        for(Edit edit: doc.getEditHistory()) {
            // to be made private;
            System.out.println(edit.change+" "+edit.user.name+" ");
        }
    }
}

// Microsoft
