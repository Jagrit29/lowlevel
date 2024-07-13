package lowleveldesign;

import java.time.LocalDateTime;
import java.util.List;

class BookDetails {
    private String id;
    private String author;
    private LocalDateTime publicationDate;

    public BookDetails(String id, String author, LocalDateTime publicationDate) {
        this.id = id;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }
}

class BookItem {

    // bookIteamId represents the barcode of the single instance of the book.
    private String bookItemId;
    private BookDetails bookDetails;

    public BookItem(String bookItemId, BookDetails bookDetails) {
        this.bookItemId = bookItemId;
        this.bookDetails = bookDetails;
    }

    public String getBookItemId() {
        return bookItemId;
    }

    public BookDetails getBookDetails() {
        return bookDetails;
    }
}

// lowleveldesign.UserType defined the allowed values for Users
enum UserType {
    Librarian, Member
}


class User {
    private String userId;
    private String userName;
    private UserType userType;

    public User(String userId, String userName, UserType userType) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public UserType getUserType() {
        return userType;
    }
}

interface UserRepo {
    User getUserById(String id);
    void addUser(User user);
    void removeUser(User user);
}

interface BookCatalog {
    void addBook(BookItem bookItem);
    void removeBook(BookItem bookItem);

    // now as bookcatalog is kind of db, wee can have certain search here too;
    List<BookDetails> searchBookByTitle(String title);
    // similar search functions
}

interface LendingService {
    int getOverdueFineAmount(User user);
    List<BookItem> getCheckedOutBooks(User user);
    List<User> getLendingUsers(String bookID);
    BookItem checkout(User user, String bookID);
    boolean renew(User user, BookItem bookItem);
    boolean returnBook(User user, BookItem bookItem);
}


public class LibrarySystem {

}
