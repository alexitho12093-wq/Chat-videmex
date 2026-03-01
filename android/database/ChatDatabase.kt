import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

// Define the entity and DAO classes
@Database(entities = {Message.class}, version = 1)
public abstract class ChatDatabase extends RoomDatabase {
    public abstract MessageDao messageDao();

    private static ChatDatabase INSTANCE;

    public static synchronized ChatDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ChatDatabase.class, "chat_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
} 

// Example entity class for messages
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "messages")
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private String sender;
    private long timestamp;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}

// Example DAO interface for messages
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface MessageDao {
    @Insert
    void insert(Message message);

    @Query("SELECT * FROM messages ORDER BY timestamp DESC")
    List<Message> getAllMessages();
}