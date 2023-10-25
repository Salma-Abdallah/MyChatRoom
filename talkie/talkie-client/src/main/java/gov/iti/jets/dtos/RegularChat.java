package gov.iti.jets.dtos;


public class RegularChat extends Chat{
    private static final long serialVersionUID = 5887637195618767821L;
    
    private User firstParticipant;
    private User secondParticipant;

    public RegularChat(String chatId, User firstParticipantId, User secondParticipantId) {
        this.chatId = chatId;
        this.firstParticipant = firstParticipantId;
        this.secondParticipant = secondParticipantId;
    }

    public RegularChat(String chatId, User firstParticipantId) {
        this.chatId = chatId;
        this.firstParticipant = firstParticipantId;
    } 

    public void setFirstParticipant(User firstParticipantId) {
        this.firstParticipant = firstParticipantId;
    }

    public void setSecondParticipant(User secondParticipantId) {
        this.secondParticipant = secondParticipantId;
    }

    public User getFirstParticipant() {
        return firstParticipant;
    }

    public User getSecondParticipant() {
        return secondParticipant;
    }

    public void resetChatOrder(){
        if(this.firstParticipant.getPhoneNumber().equals(CurrentUser.getInstance().getUser().getPhoneNumber())){
            User temp = firstParticipant;
            firstParticipant = secondParticipant;
            secondParticipant = temp;
        }
    }

    @Override
    public String toString() {
        return "RegularChat{" +
                "chatId='" + chatId + '\'' +
                ", firstParticipantId=" + firstParticipant +
                ", secondParticipantId=" + secondParticipant +
                '}';
    }
}