import java.util.Date;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Suggestion {

    Date date = new Date();
    String food = "";
    String resturant = "";
    ArrayList<String> participants = new ArrayList<String>();

    public Date getDate() { return date; }

    public void setDate(Date d) { date = d; }
    
    public String getFood() { return food; }

    public void setFood(String f) { food = f; }

    public String getResturant() { return resturant; }

    public void setResturant(String r) { resturant = r; }

    public List<String> getParticipants() { return participants; }

    public void addParticipant(String p) { participants.add(p); }

    public void removeParticipant(String p) { participants.remove(p); }

    @Override
    public String toString() {
	return date.toString() + "\n" +
	    food + "\n" +
	    resturant + "\n" +
	    Arrays.toString(participants.toArray());
    }
}
