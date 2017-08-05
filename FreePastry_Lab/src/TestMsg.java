import rice.p2p.commonapi.Id;
import rice.p2p.commonapi.Message;
import rice.p2p.scribe.ScribeContent;

public class TestMsg implements Message {

  Id from;
  Id to;
  String owner;
  long time;

  public TestMsg(Id from, Id to, String owner, long time) {
    this.from = from;
    this.to = to;
    this.owner = owner;
    this.time = time;
  }

  public int getPriority() {
    return Message.LOW_PRIORITY;
  }

  public String toString() {
    return "Message (owned by " + owner + ") was sent from " + from + " to " + to + " at " + time;
  }
}

class TestScribeContent implements ScribeContent {
	
	Id from;
	String owner;
	long time;
	
	public TestScribeContent(Id from, String owner, long time){
		this.from = from;
	    this.owner = owner;
	    this.time = time;
	}
	
	public String toString() {
	    return "Scribe message (owned by " + owner + ") was sent from " + from + " at " + time;
	}	
}